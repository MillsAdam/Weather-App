import React, { createContext, useState } from 'react';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [authToken, setAuthToken] = useState(null);
    const [currentUser, setCurrentUser] = useState(null);

    return (
        <AuthContext.Provider value={{ authToken, setAuthToken, currentUser, setCurrentUser }}>
            {children}
        </AuthContext.Provider>
    );
};

