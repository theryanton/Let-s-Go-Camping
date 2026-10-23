import React, { useState } from 'react';
import axios from 'axios';
import {useNavigate} from "react-router-dom";

function UserRegistration() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [confirm_password, setConfirmPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const navigate = useNavigate();
  const [showModal, setShowModal] = useState(false);

  const checkPassword = (password) => {
    const REQUIREMENTS = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$/
    return REQUIREMENTS.test(password);
  }

  const handleRegistration = async (e) => {
    e.preventDefault();

    if (password !== confirm_password) {
      setErrorMessage("Passwords do not match.");
    }
    else if (!checkPassword(password)) {
      setErrorMessage("Password needs 1 uppercase, 1 lowercase, 1 number.");
    }
    else {
      try {
        const response = await axios.post('/register', {
          username: username,
          password: password
        });
        console.log(response.data);
        // Handle successful registration, e.g., redirect user to login page
        navigate('/Login')

      } catch (error) {
        setErrorMessage("That username has already been taken.");
      }
    }
  };

  const handleCancel = function() {
    setShowModal(true);
  }

  const returnToRegistration = function() {
    setShowModal(false);
  }

// issue #50 was resolved when we resolved another issue pertaining to the navbar. Im writing this comment
// because we cannot have blank commits.

  return (
      <div className="registration-section">
        <h1 className="title-header">Let's Go Camping!</h1>
        <div className="registration-container">
          <h2 className="registration-header">User Registration</h2>
          {errorMessage && <p id="errorMessage" style={{color: 'red'}}>{errorMessage}</p>}
          <form onSubmit={handleRegistration}>
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
            <div>
              <label>Confirm Password:</label>
              <input
                  id="confirm_password"
                  type="password"
                  value={confirm_password}
                  onChange={(e) => setConfirmPassword(e.target.value)}
                  required
              />
            </div>
            <button className="register-btn" type="submit">Register</button>
            <button type="button" className="cancel-btn" onClick={handleCancel}>Cancel</button>
          </form>
          {showModal && (
              <div className="modal">
              <p>Are you sure you want to exit registration?</p>
                <button type="button" className="confirm-cancel-btn" onClick={() => navigate('/')}>Yes</button>
                <button type="button" className="continue-btn" onClick={returnToRegistration}>Never mind!</button>
              </div>
          )}
          </div>
        </div>
  );
}

export default UserRegistration;