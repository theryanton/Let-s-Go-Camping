import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const [successMessage, setSuccessMessage] = useState('');
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post('/login', {
        username: username,
        password: password
      });

      const { message, user_id } = response.data;
      setSuccessMessage(message);

      document.cookie = `user_id=${user_id}; path=/; max-age=86400`

      setErrorMessage("");
      navigate("/SearchPage");
      // Handle successful login, e.g., redirect user to dashboard
    } catch (error) {

      if (error.response && error.response.status === 401) { //redirect to account blocked when conditions are met
        navigate('/AccountBlocked');
      }

      setErrorMessage(error.response.data.message);
    }
  };

  const handleRegister = () => {
    navigate('/UserRegistration');
  };

  return (
      <div className="login-section">
        <h1 className="title-header">Let's Go Camping!</h1>
            <div className ="login-container">
              <h2 className="login-header">Login</h2>
              {errorMessage && <p id="errorMessage" style={{ color: 'red' }}>{errorMessage}</p>}
              {successMessage && <p id="successMessage" style={{ color: 'green'}}>{successMessage}</p>}
              <form onSubmit={handleLogin}>
                <div>
                  <label>Username:</label>
                  <input
                    id="username"
                    type="text"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    required
                  />
                </div>
                <div>
                  <label>Password:</label>
                  <input
                    id="password"
                    type="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                  />
                </div>
                <div className="login-btn-container">
                    <button className="login-btn" type="submit">Login</button>
                    <button className="register-btn" onClick={handleRegister}>Register</button>
                </div>
              </form>
            </div>
        </div>
  );
}

export default Login;
