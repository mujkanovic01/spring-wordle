import { useState } from 'react';

const useApi = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);
    const authToken = localStorage.getItem('token');
    const apiUrl = 'http://localhost:8080/api';

    const makeRequest = async (url: RequestInfo | URL, options: RequestInit | undefined) => {
        setIsLoading(true);
        setError(null);
        try {
            const response = await fetch(url, options);
            const data = await response.json();
            if (!response.ok) {
                setError(data.message || 'An error occurred');
                return null;
            }
            return data;
        } catch (error) {
            let errorMessage = "Failed to do something exceptional";
            if (error instanceof Error) {
                errorMessage = error.message;
            }
            console.log(errorMessage);
        } finally {
            setIsLoading(false);
        }
    };

    const makeAGuess = async (userGameId: string, guess: string) => {
        const url = `${apiUrl}/userGames/makeAGuess/${userGameId}`;
        const options = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${authToken}` },
            body: JSON.stringify({ guess: guess }),
        };
        return makeRequest(url, options);
    };

    const startNewGame = async () => {
        const url = `${apiUrl}/userGames/startGame`;
        const options = {
            method: 'POST',
            headers: { 'Authorization': `Bearer ${authToken}` },
        };
        return makeRequest(url, options);
    };

    const startDailyGame = async () => {
        const url = `${apiUrl}/userGames/startDailyGame`;
        const options = {
            method: 'POST',
            headers: { 'Authorization': `Bearer ${authToken}` },
        };
        return makeRequest(url, options);
    };

    const getSolution = async (userGameId: string) => {
        const url = `${apiUrl}/games/${userGameId}`;
        const options = {
            method: 'GET',
            headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${authToken}`  },
        };
        return makeRequest(url, options);
    };

    const registerUser = async (username: string, password: string, confirmPassword: string) => {
        const url = `${apiUrl}/auth/register`;
        const options = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username, password, confirmPassword }),
        };
        return makeRequest(url, options);
    };

    const loginUser = async (username: string, password: string) => {
        const url = `${apiUrl}/auth/login`;
        const options = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username, password }),
        };
        return makeRequest(url, options);
    };

    return { makeAGuess, startNewGame, startDailyGame, getSolution, registerUser, loginUser, isLoading, error };
};

export default useApi;
