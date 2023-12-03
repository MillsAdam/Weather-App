import React, { useContext } from "react";
import { Link, useNavigate } from "react-router-dom";
import { AuthContext } from '../context/AuthContext';

function NavigationBar() {
  const { authToken } = useContext(AuthContext);
  const navigate = useNavigate();

  const handleLogoutClick = () => {
    navigate('/logout');
  }

  return (
    <nav>
      {authToken && (
          <>
            <Link to="/" className="App-link">Home</Link>{" | "}
            <Link to="/weather" className="App-link">Weather</Link>{" | "}
            <span onClick={handleLogoutClick} className="App-link" style={{ cursor: 'pointer' }}>Logout</span>
          </>
      )}
      {!authToken && (
          <>
            <Link to="/login" className="App-link">Login</Link>{" | "}
            <Link to="/register" className="App-link">Register</Link>
          </>
      )}
    </nav>
  );
}

export default NavigationBar;
