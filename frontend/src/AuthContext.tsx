import React, { createContext, useContext, ReactNode, useState, useEffect } from 'react';

interface AuthContextType {
  user: any;
  signIn: (token: string, onSignedIn: () => void) => void;
  signOut: (onSignedOut: () => void) => void;
}

export const AuthContext = createContext<AuthContextType | null>(null);

export const useAuth = () => useContext(AuthContext);

export const AuthProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
  const [user, setUser] = useState(null);

  useEffect(() => {
    const token = localStorage.getItem('token');
    if (token) {
      // Set user state based on token
      // setUser(decodeToken(token));
      // Redirection will be handled in the component that checks for authentication
    }
  }, []);

  const signIn = (token: string, onSignedIn: () => void) => {
    localStorage.setItem('token', token);
    // setUser(userData);
    onSignedIn();
  };

  const signOut = (onSignedOut: () => void) => {
    localStorage.removeItem('token');
    setUser(null);
    onSignedOut();
  };

  return (
      <AuthContext.Provider value={{ user, signIn, signOut }}>
        {children}
      </AuthContext.Provider>
  );
};
