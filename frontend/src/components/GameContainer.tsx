import React, { useState, useEffect } from 'react';
import { v4 as uuidv4 } from 'uuid';
import useApi from '../hooks/useApi';

const GameContainer: React.FC = () => {
	const { makeAGuess, startNewGame, startDailyGame, getSolution } = useApi();
	const [gameType, setGameType] = useState<'daily' | 'new' | null>(null);
	const [guessedLetters, setGuessedLetters] = useState<Array<Array<{ [key: string]: string }>>>(
		Array.from({ length: 6 }, () => Array.from({ length: 5 }, () => ({ '': '' })))
	);

	const [gameId, setGameId] = useState<string>('');
	const [userGameId, setUserGameId] = useState<string>('');
	const [currentRowIndex, setCurrentRowIndex] = useState(0)
	const [userSolution, setUserSolution] = useState<string[]>(Array(6).fill(''))
	const [gameWon, setGameWon] = useState(false)
	const [gameLost, setGameLost] = useState(false)
	const [gameOver, setGameOver] = useState(false)
	const [error, setError] = useState("")

	const startGame = async (type: 'daily' | 'new') => {
		// setGameType(type);
		try {
			const gameData = type === 'daily' ? await startDailyGame() : await startNewGame();
			console.log('gameData', gameData);
			if (gameData === null) {
				setError("You cannot play a daily game more than once.");
				setGameType(null)
			} else {
				setGameId(gameData.id);
				setUserGameId(gameData.gameId);
				setGameType(type);
				setError("")
			}

			setCurrentRowIndex(0); // Reset or set the initial row index based on gameData
		} catch (error: any) {
			setError(error.message)
			console.error(error);
			// Handle error
		}
	};

	const checkSolution = async (userSolution: string[]) => {
		setError('')
		try {
			const response = await makeAGuess(gameId, userSolution.join(''));
			if (response === null) {
				setError('Word not found in dictionary.')
				return
			}
			const { letters, hasWon } = response;

			guessedLetters[currentRowIndex] = letters;

			setGameWon(hasWon);
			setGameOver(hasWon || currentRowIndex === 5);
			if (!hasWon) {
				setCurrentRowIndex(old => old + 1);
			}
		} catch (error) {
			console.error('Error in making a guess:', error);
		}
	};

	useEffect(() => {
		const getSolutionData = async () => {
			try {
				const solutionData = await getSolution(userGameId);
				const solution = solutionData.word.word;
				setError(`The valid word is: ${solution}`)
			} catch (error) {
				console.error('Error in getting solution:', error);
			}
		};
		if (gameType && userGameId) {
			getSolutionData().then();
		}
	}, [gameOver]);

	useEffect(() => {
		const handleKeyDown = async (event: KeyboardEvent) => {
			const { key } = event
			const lettersInCurrentRow = guessedLetters[currentRowIndex].filter(
				letterObj => Object.keys(letterObj)[0] != '',
			).length
			if (key === 'Enter' && lettersInCurrentRow === 5) {
				const newGuessedLetters = JSON.parse(
					JSON.stringify(guessedLetters),
				) as typeof guessedLetters
				const currentRow = newGuessedLetters[currentRowIndex]
				console.log('current row', currentRow.map(letter => Object.keys(letter)[0]))
				await checkSolution(currentRow.map(letter => Object.keys(letter)[0]))
			} else if (key === 'Backspace') {
				// if backspace pressed then delete last letter
				const newGuessedLetters =
					JSON.parse(JSON.stringify(guessedLetters)) as typeof guessedLetters
				const currentRow = newGuessedLetters[currentRowIndex]
				const lastLetterIndex = currentRow.findIndex(
					(letter: Record<string, string>) => Object.keys(letter)[0] === ''
				)
				const indexToDelete = lastLetterIndex === -1 ? 4 : lastLetterIndex - 1
				currentRow[indexToDelete] = {'': ''}
				setGuessedLetters(newGuessedLetters)
			} else if (!key.match(/^[A-Za-z]$/)) {
				// if not valid letter then skip
				return
			} else {
				// if valid letter then insert at the right position
				const newGuessedLetters =
					JSON.parse(JSON.stringify(guessedLetters)) as typeof guessedLetters
				const currentRow = newGuessedLetters[currentRowIndex]
				const lastLetterIndex = currentRow.findIndex(
					(letter: Record<string, string>) => Object.keys(letter)[0] === ''
				)
				if (lastLetterIndex !== -1) {
					currentRow[lastLetterIndex] = {[key[0].toUpperCase()]: ''}
				}

				setGuessedLetters(newGuessedLetters)
			}
		}
		window.addEventListener('keydown', handleKeyDown)

		return () => {
			window.removeEventListener('keydown', handleKeyDown)
		}
	}, [guessedLetters, currentRowIndex, userSolution])

	const resetGame = () => {
		startGame('new')
		setGuessedLetters(Array(6).fill(Array(5).fill({'':''})))
		setCurrentRowIndex(0)
		setUserSolution(Array(6).fill(''))
		setGameWon(false)
		setGameLost(false)
		setGameOver(false)
	}

	if (!gameType) {
		// Game not started, show buttons to choose game type
		return (
			<div style={{display: "flex", flexDirection: "column"}}>
				<h1>Select a game</h1>
				<button onClick={() => startGame('daily')}>Start Daily Game</button>
				<button onClick={() => startGame('new')}>Start New Game</button>
				{error && <p style={{ marginTop: 10 }}>{error}</p>}
			</div>
		);
	}

  return (
    <div className="board">
		<h1>Wordle</h1>
		{guessedLetters.map((row) => {
			return (
				<div className="row" key={uuidv4()}>
					{row.map((letterObj) => {
						const [letter, value] = Object.entries(letterObj)[0];
						const className = value.toLowerCase();
						return (
							<div className={`letter ${className}`} key={uuidv4()}>
								{letter.toUpperCase()}
							</div>
						);
					})}
				</div>
			);
		})}
		{error && <p>{error}</p>}
		{gameLost && <p style={{ marginTop: 10 }}>Oops! You lost!</p>}
		{gameWon && <p style={{ marginTop: 10 }}>Congratulations! You Won!</p>}
		{gameOver && (
			<div>
				<button onClick={resetGame} style={{marginTop: 0}}>
					Play Again
				</button>
			</div>

		)}
	</div>
  );
};

export default GameContainer;
