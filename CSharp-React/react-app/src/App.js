import './App.css';
import React, { useContext } from "react";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import { AuthContext } from './context/AuthContext';
import Home from './views/Home';
import Weather from './views/Weather';
import Logout from './views/Logout';
import Login from './views/Login';
import ProtectedRoute from './components/ProtectedRoute';

function App() {
  const { authToken } = useContext(AuthContext);

  return (
    <Router>
      <div className="App">
        <header className="App-header">
          <nav>
            {authToken && (
              <>
                <Link to="/" className="App-link">Home</Link>{" | "}
                <Link to="/weather" className="App-link">Weather</Link>{" | "}
                <Link to="/logout" className="App-link">Logout</Link>
              </>
            )}
            {!authToken && <Link to="/login" className="App-link">Login</Link>}
          </nav>
        </header>
        <Routes>
          <Route path="/" element={<ProtectedRoute><Home /></ProtectedRoute>} />
          <Route path="/weather" element={<ProtectedRoute><Weather /></ProtectedRoute>} />
          <Route path="/logout" element={<ProtectedRoute><Logout /></ProtectedRoute>} />
          <Route path="/login" element={<Login />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
