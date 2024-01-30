import React, { useState } from 'react';
import useApi from '../hooks/useApi';

interface UserInfo {
    username: string;
    password: string;
    confirmPassword: string;
}

const SignUpForm: React.FC = () => {
    const [userInfo, setUserInfo] = useState<UserInfo>({
        username: '',
        password: '',
        confirmPassword: '',
    });
    const { registerUser, isLoading, error } = useApi();

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setUserInfo({ ...userInfo, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            // Assuming the registerUser API expects username, email, and password
            const response = await registerUser(userInfo.username, userInfo.password, userInfo.confirmPassword);
            console.log('Registration Successful:', response);
            // Handle post-registration logic (e.g., redirecting the user)
        } catch (err) {
            console.error('Registration Failed:', err);
            // Handle errors (e.g., show error message to the user)
        }
    };

  return (
      <form onSubmit={handleSubmit}>
          <h2>Sign Up</h2>
          <input
              type="text"
              name="username"
              placeholder="Username"
              value={userInfo.username}
              onChange={handleChange}
          />
          <input
              type="password"
              name="password"
              placeholder="Password"
              value={userInfo.password}
              onChange={handleChange}
          />
          <input
              type="password"
              name="confirmPassword"
              placeholder="Confirm password"
              value={userInfo.confirmPassword}
              onChange={handleChange}
          />
          <button type="submit" disabled={isLoading}>Sign Up</button>
          {error && <p style={{color: 'red'}}>{error}</p>}
      </form>
  );
}

export default SignUpForm;
