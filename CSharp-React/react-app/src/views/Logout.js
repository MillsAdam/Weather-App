import React, { useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { AuthContext } from '../context/AuthContext';

const Logout = () => {
    const navigate = useNavigate();
    const { setAuthToken, setCurrentUser } = useContext(AuthContext);

    const handleLogout = () => {
        localStorage.removeItem('authToken');

        setAuthToken(null);
        setCurrentUser(null);

        navigate('/login');
    };

    return (
        <div>
            <button onClick={handleLogout}>Logout</button>
        </div>
    );
};

export default Logout;