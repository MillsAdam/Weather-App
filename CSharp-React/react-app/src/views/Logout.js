import { useEffect, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { AuthContext } from '../context/AuthContext';

function Logout() {
    const navigate = useNavigate();
    const { setAuthToken, setCurrentUser } = useContext(AuthContext);

    useEffect(() => {
        localStorage.removeItem('authToken');
        localStorage.removeItem('currentUser');

        setAuthToken(null);
        setCurrentUser(null);

        navigate('/login');
    }, [setAuthToken, setCurrentUser, navigate]);

    return null;
}

export default Logout;