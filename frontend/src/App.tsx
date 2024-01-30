import './App.css';
import React from 'react';
import { BrowserRouter as Router, Route, Switch, Redirect } from 'react-router-dom';
import { AuthProvider } from './AuthContext';
import SignInForm from './components/SignInForm';
import SignUpForm from './components/SignUpForm';
import GameContainer from './components/GameContainer';
import PrivateRoute from './components/PrivateRoute';

const App: React.FC = () => {
    return (
        <AuthProvider>
            <Router>
                <Switch>
                    <Route path="/signin">
                        <SignInForm />
                    </Route>
                    <Route path="/signup">
                        <SignUpForm />
                    </Route>
                    <PrivateRoute path="/game">
                        <GameContainer />
                    </PrivateRoute>
                    <Route path="/">
                        <Redirect to="/signin" />
                    </Route>
                </Switch>
            </Router>
        </AuthProvider>
    );
};

export default App;
