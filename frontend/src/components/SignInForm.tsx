import { useState, ChangeEvent, FormEvent } from 'react';
import useApi from "../hooks/useApi.tsx";
import {useAuth} from "../AuthContext.tsx";
import {useHistory} from "react-router-dom";

function SignInForm() {
    const [loginInfo, setLoginInfo] = useState({ username: '', password: '' });
    const { loginUser, isLoading, error } = useApi();
    const auth = useAuth();
    const history = useHistory();

    const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
        setLoginInfo({ ...loginInfo, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        try {
            const response = await loginUser(loginInfo.username, loginInfo.password);
            auth?.signIn(response.jwt, () => {
                history.push('/game');
                console.log('Login Successful:', response);
            });
        } catch (err) {
            console.error('Login Failed:', err);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <h2>Sign In</h2>
            <input
                type="text"
                name="username"
                placeholder="Username"
                value={loginInfo.username}
                onChange={handleChange}
            />
            <input
                type="password"
                name="password"
                placeholder="Password"
                value={loginInfo.password}
                onChange={handleChange}
            />
            <button type="submit" disabled={isLoading}>Sign In</button>
            {error && <p style={{ color: 'red' }}>{error}</p>}
        </form>
    );
}

export default SignInForm;
