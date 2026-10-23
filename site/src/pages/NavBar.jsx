import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/index.css';
import {getCookie} from "../components/Helpers";

function NavBar() {
    const navigate = useNavigate(); // Get the navigate function

    return (
        <div>
            <nav className="navbar">
                <ul>
                    <li><a onClick={() => navigate('/Login')}>Login</a></li>
                    <li><a id='searchLink' onClick={() => navigate('/SearchPage')}>Search</a></li>
                    <li><a onClick={() => navigate('/UserRegistration')}>Register</a></li>
                </ul>
                <div style={{ textAlign: 'right', color: 'white', padding: '14px 16px' }}>
                    <p>Team 36: Let's Go Camping!</p>
                </div>
            </nav>
        </div>
    );

    let session_id = getCookie('user_id');

    if (session_id === null) {
        return (
            <div>
                <nav className="navbar">
                    <ul>
                        <li><a onClick={() => navigate('/SearchPage')}>Search</a></li>
                        <li><a onClick={() => navigate('/Login')}>Login</a></li>
                        <li><a onClick={() => navigate('/UserRegistration')}>Register</a></li>
                    </ul>
                    <div style={{textAlign: 'right', color: 'white', padding: '14px 16px' }}>
                        <p>Team 36: Let's Go Camping!</p>
                    </div>
                </nav>
            </div>
        );
    }

    else {
        return (
            <div>
                <nav className="navbar">
                    <ul>
                        <li><a onClick={() => navigate('/SearchPage')}>Search</a></li>
                        <li><a onClick={() => navigate('/Favorites')}>My Favorites</a></li>
                    </ul>
                    <div style={{ textAlign: 'right', color: 'white', padding: '14px 16px' }}>
                        <p>Team 36: Let's Go Camping!</p>
                    </div>
                </nav>
            </div>
        );
    }

}

export default NavBar;