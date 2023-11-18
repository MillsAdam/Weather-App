import React, { useState, useContext } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import AuthService from '../services/AuthService';
import { AuthContext } from '../context/AuthContext';
import '../styles/Login.css';

function Login() {
    const [user, setUser] = useState({ username: '', password: '' });
    const [invalidCredentials, setInvalidCredentials] = useState(false);
    const navigate = useNavigate();
    const { setAuthToken, setCurrentUser } = useContext(AuthContext);

    const handleInputChange = (e) => {
        setUser({ ...user, [e.target.name]: e.target.value });
    };

    const login = async (e) => {
        e.preventDefault();
        try {
            const response = await AuthService.login(user);
            if (response.status === 200) {
                setAuthToken(response.data.token);
                setCurrentUser(response.data.user);
                navigate('/');
            }
        } catch (error) {
            if (error.response && error.response.status === 401) {
                setInvalidCredentials(true);
            }
        }
    };

    return (
        <div className="login">
            <h1>Login</h1>
            <form onSubmit={login}>
                <div className="form-input-group">
                    <label>Username: </label>
                    <input className="form" type="text" name="username" value={user.username} onChange={handleInputChange} />
                </div>
                <div className="form-input-group">
                    <label>Password: </label>
                    <input className="form" type="password" name="password" value={user.password} onChange={handleInputChange} />
                </div>
                <input type="submit" className="form" value="Login" />
            </form>
            {invalidCredentials && <div className="invalid-credentials">Invalid Credentials</div>}
            <div className="link-section">
                Don't have an account? <Link to="/register">Register</Link>
            </div>
        </div>
    );
}

export default Login;