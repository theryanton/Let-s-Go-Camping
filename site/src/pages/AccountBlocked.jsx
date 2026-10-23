import React, {useEffect, useState} from 'react';
import { useNavigate } from 'react-router-dom';

function AccountBlocked() {
    const navigate = useNavigate();
    const [waitTime,setWaitTime] = useState(60);
    useEffect(() => {
        const interval = setInterval(() => {
            setWaitTime((prevTime) => prevTime - 1);
            }
            ,1000
        );

        const finish = setTimeout( () => {
                navigate('/login');
            }
            , 60000
        );

        return () => {
            clearInterval(interval);
            clearTimeout(finish);
        }

    }, []);


    return (
        <div className="AccountBlockedMessage">
            <title>Account Blocked</title>
            <h1>You have made too many failed login attempts. Please wait before trying again.</h1>
            <h2 style={{color:'red'}}>Wait for: {waitTime} seconds</h2>
        </div>
    );
}

export default AccountBlocked;