public class AIPlayer_mnp78 implements Player {

	// Put your fields here:

	char[][] boardA;
	char[][] boardB;

	int x; // rows coordinate
	int y; // columns coordinate
	char direction; // direction for ship placement

	Coordinate lastMove; // global variable to store last move

	// Implement your methods here:

	public AIPlayer_mnp78() {

		this.boardA = new char[10][10];
		this.boardB = new char[10][10];

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				this.boardA[i][j] = '~';
				this.boardB[i][j] = '~';
			}
		}
	}

	/**
	 * This player is being fired upon. Given a coordinate, updates board(s)
	 * accordingly.
	 * 
	 * @param x
	 *            - the coordinate that is being fired upon
	 * @return M for miss, otherwise the ship's char representation
	 */
	public char fireUpon(Coordinate x) {
		char temp;
		if (boardA[x.x - 1][x.y - 1] == 'A' || boardA[x.x - 1][x.y - 1] == 'B'
				|| boardA[x.x - 1][x.y - 1] == 'S'
				|| boardA[x.x - 1][x.y - 1] == 'D'
				|| boardA[x.x - 1][x.y - 1] == 'P') {
			temp = boardA[x.x - 1][x.y - 1];
			boardA[x.x - 1][x.y - 1] = 'H';
			return temp;
		} else {
			boardA[x.x - 1][x.y - 1] = 'M';
			return 'M';
		}
	}

	public boolean isValid(Coordinate c) {
		if (c.x > 0 && c.x < 11 && c.y > 0 && c.y < 11 && boardB[c.x-1][c.y-1] == '~') {
			return true;
		} else {
			return false;
		}
	}

	public Coordinate findValidCoord() {

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (boardB[i][j] == 'H') {
					int x = i + 1;
					int y = j + 1;
					if (isValid(new Coordinate(x-1,y))) { //up
						return new Coordinate(x-1,y);
					}
					else if (isValid(new Coordinate(x,y-1)) ) { //left
						return new Coordinate(x,y-1);
					}
					else if (isValid(new Coordinate(x+1,y))) { //down
						return new Coordinate(x+1,y);
					}
					else if (isValid(new Coordinate(x,y+1)) ) { //right
						return new Coordinate(x,y+1);
					}
				}
			}
		}
		return null;
	}

	/**
	 * Returns a coordinate that this player wished to guess.
	 * 
	 * @return A coordinate object
	 */
	/* firing strategy:
	 * the AI will guess randomly if there hadn't been a previous move
	 * (meaning the first ever guess) or if the previous move was a miss. 
	 * otherwise, if the previous move was a hit
	 * the AI will guess coordinates up, left, down, and to the right of the hit-coordinate (in that order, if within bounds)
	 * will use the findValidCoord helper method (which will in turn use the isValid helper method)
	 * to return one of the four coordinates around the hit-coordinate that is within range and hasn't been fired upon already
	 * (those four coordinates are the up, left, down, and right coordinates around the hit-coordinate)
	 * */
	public Coordinate fire() {

		Coordinate c = findValidCoord();

		if (c == null) { // part of the passive
			do {
				x = (int) (Math.ceil(Math.random()*10));
				y = (int) (Math.ceil(Math.random()*10));
				c = new Coordinate(x, y);
			} while (!isValid(c));
			lastMove = c;
			return c;
		}
		else { 			// the aggressive
			lastMove = c;
			return c;
		}	
	}

	/**
	 * Callback method to notify player whether last fire() attempt was
	 * successful or not.
	 * 
	 * @param result 'M' if the last fire() resulted in a miss, otherwise the character code of the ship
	 */
	public void fireResult(char result) {

		if (result == 'M') {
			boardB[lastMove.x-1][lastMove.y-1] = 'M';		
		} else {
			boardB[lastMove.x-1][lastMove.y-1] = 'H';	
		}

	}

	/**
	 * Places all the ships for this player
	 */
	/* ship placement strategy: 
	 * the fact that the AI will place ships randomly will mean that
	 * sometimes the ships will be touching, and sometimes they won't.
	 * the fact that it will place ships in a completely different way each time 
	 * is "the strategy."
	 * */
	public void placeShips() {

		while (true) {

			x = (int) (Math.ceil(Math.random()*10));
			y = (int) (Math.ceil(Math.random()*10));
			int i = (int) (Math.random() * 4);
			char[] dir = new char[4];
			dir[0] = 'w';
			dir[1] = 's';
			dir[2] = 'a';
			dir[3] = 'd';
			direction = dir[i];

			if (direction == 'w') {
				if (x > 4 && x < 11 && y > 0 && y < 11) {
					if (boardA[x - 1][y - 1] == '~' && boardA[x - 2][y - 1] == '~'
							&& boardA[x - 3][y - 1] == '~'
							&& boardA[x - 4][y - 1] == '~'
							&& boardA[x - 5][y - 1] == '~') {
						for (int j = 0; j < 5; j++) {
							boardA[x - j - 1][y - 1] = 'A';
						}
					} else {
						continue;
					}
				} else {
					continue;
				}
			} else if (direction == 's') {
				if (x < 7 && x > 0 && y > 0 && y < 11) {
					if (boardA[x - 1][y - 1] == '~' && boardA[x][y - 1] == '~'
							&& boardA[x + 1][y - 1] == '~'
							&& boardA[x + 2][y - 1] == '~'
							&& boardA[x + 3][y - 1] == '~') {
						for (int j = 0; j < 5; j++) {
							boardA[x + j - 1][y - 1] = 'A';
						}
					} else {
						continue;
					}
				} else {
					continue;
				}
			} else if (direction == 'd') {
				if (y < 7 && y > 0 && x > 0 && x < 11) {
					if (boardA[x - 1][y - 1] == '~' && boardA[x - 1][y] == '~'
							&& boardA[x - 1][y + 1] == '~'
							&& boardA[x - 1][y + 2] == '~'
							&& boardA[x - 1][y + 3] == '~') {
						for (int j = 0; j < 5; j++) {
							boardA[x - 1][y + j - 1] = 'A';
						}
					} else {
						continue;
					}
				} else {
					continue;
				}
			} else if (direction == 'a') {
				if (y > 4 && y < 11 && x > 0 && x < 11) {
					if (boardA[x - 1][y - 1] == '~' && boardA[x - 1][y - 2] == '~'
							&& boardA[x - 1][y - 3] == '~'
							&& boardA[x - 1][y - 4] == '~'
							&& boardA[x - 1][y - 5] == '~') {
						for (int j = 0; j < 5; j++) {
							boardA[x - 1][y - j - 1] = 'A';
						}
					} else {
						continue;
					}
				} else {
					continue;
				}
			} else {
				continue;
			}

			break;
		}

		while (true) {

			x = (int) (Math.ceil(Math.random()*10));
			y = (int) (Math.ceil(Math.random()*10));
			int i = (int) (Math.random() * 4);
			char[] dir = new char[4];
			dir[0] = 'w';
			dir[1] = 's';
			dir[2] = 'a';
			dir[3] = 'd';
			direction = dir[i];

			if (direction == 'w') {
				if (x > 3 && x < 11 && y > 0 && y < 11) {
					if (boardA[x - 1][y - 1] == '~' && boardA[x - 2][y - 1] == '~'
							&& boardA[x - 3][y - 1] == '~'
							&& boardA[x - 4][y - 1] == '~') {
						for (int j = 0; j < 4; j++) {
							boardA[x - j - 1][y - 1] = 'B';
						}
					} else {
						continue;
					}
				} else {
					continue;
				}
			} else if (direction == 's') {
				if (x < 8 && x > 0 && y > 0 && y < 11) {
					if (boardA[x - 1][y - 1] == '~' && boardA[x][y - 1] == '~'
							&& boardA[x + 1][y - 1] == '~'
							&& boardA[x + 2][y - 1] == '~') {
						for (int j = 0; j < 4; j++) {
							boardA[x + j - 1][y - 1] = 'B';
						}
					} else {
						continue;
					}
				} else {
					continue;
				}
			} else if (direction == 'd') {
				if (y < 8 && y > 0 && x > 0 && x < 11) {
					if (boardA[x - 1][y - 1] == '~' && boardA[x - 1][y] == '~'
							&& boardA[x - 1][y + 1] == '~'
							&& boardA[x - 1][y + 2] == '~') {
						for (int j = 0; j < 4; j++) {
							boardA[x - 1][y + j - 1] = 'B';
						}
					} else {
						continue;
					}
				} else {
					continue;
				}
			} else if (direction == 'a') {
				if (y > 3 && y < 11 && x > 0 && x < 11) {
					if (boardA[x - 1][y - 1] == '~' && boardA[x - 1][y - 2] == '~'
							&& boardA[x - 1][y - 3] == '~'
							&& boardA[x - 1][y - 4] == '~') {
						for (int j = 0; j < 4; j++) {
							boardA[x - 1][y - j - 1] = 'B';
						}
					} else {
						continue;
					}
				} else {
					continue;
				}
			} else {
				continue;
			}

			break;
		}

		while (true) {

			x = (int) (Math.ceil(Math.random()*10));
			y = (int) (Math.ceil(Math.random()*10));
			int i = (int) (Math.random() * 4);
			char[] dir = new char[4];
			dir[0] = 'w';
			dir[1] = 's';
			dir[2] = 'a';
			dir[3] = 'd';
			direction = dir[i];

			if (direction == 'w') {
				if (x > 2 && x < 11 && y > 0 && y < 11) {
					if (boardA[x - 1][y - 1] == '~' && boardA[x - 2][y - 1] == '~'
							&& boardA[x - 3][y - 1] == '~') {
						for (int j = 0; j < 3; j++) {
							boardA[x - j - 1][y - 1] = 'S';
						}
					} else {
						continue;
					}
				} else {
					continue;
				}
			} else if (direction == 's') {
				if (x < 9 && x > 0 && y > 0 && y < 11) {
					if (boardA[x - 1][y - 1] == '~' && boardA[x][y - 1] == '~'
							&& boardA[x + 1][y - 1] == '~') {
						for (int j = 0; j < 3; j++) {
							boardA[x + j - 1][y - 1] = 'S';
						}
					} else {
						continue;
					}
				} else {
					continue;
				}
			} else if (direction == 'd') {
				if (y < 9 && y > 0 && x > 0 && x < 11) {
					if (boardA[x - 1][y - 1] == '~' && boardA[x - 1][y] == '~'
							&& boardA[x - 1][y + 1] == '~') {
						for (int j = 0; j < 3; j++) {
							boardA[x - 1][y + j - 1] = 'S';
						}
					} else {
						continue;
					}
				} else {
					continue;
				}
			} else if (direction == 'a') {
				if (y > 2 && y < 11 && x > 0 && x < 11) {
					if (boardA[x - 1][y - 1] == '~' && boardA[x - 1][y - 2] == '~'
							&& boardA[x - 1][y - 3] == '~') {
						for (int j = 0; j < 3; j++) {
							boardA[x - 1][y - j - 1] = 'S';
						}
					} else {
						continue;
					}
				} else {
					continue;
				}
			} else {
				continue;
			}

			break;
		}

		while (true) {

			x = (int) (Math.ceil(Math.random()*10));
			y = (int) (Math.ceil(Math.random()*10));
			int i = (int) (Math.random() * 4);
			char[] dir = new char[4];
			dir[0] = 'w';
			dir[1] = 's';
			dir[2] = 'a';
			dir[3] = 'd';
			direction = dir[i];

			if (direction == 'w') {
				if (x > 2 && x < 11 && y > 0 && y < 11) {
					if (boardA[x - 1][y - 1] == '~' && boardA[x - 2][y - 1] == '~'
							&& boardA[x - 3][y - 1] == '~') {
						for (int j = 0; j < 3; j++) {
							boardA[x - j - 1][y - 1] = 'D';
						}
					} else {
						continue;
					}
				} else {
					continue;
				}
			} else if (direction == 's') {
				if (x < 9 && x > 0 && y > 0 && y < 11) {
					if (boardA[x - 1][y - 1] == '~' && boardA[x][y - 1] == '~'
							&& boardA[x + 1][y - 1] == '~') {
						for (int j = 0; j < 3; j++) {
							boardA[x + j - 1][y - 1] = 'D';
						}
					} else {
						continue;
					}
				} else {
					continue;
				}
			} else if (direction == 'd') {
				if (y < 9 && y > 0 && x > 0 && x < 11) {
					if (boardA[x - 1][y - 1] == '~' && boardA[x - 1][y] == '~'
							&& boardA[x - 1][y + 1] == '~') {
						for (int j = 0; j < 3; j++) {
							boardA[x - 1][y + j - 1] = 'D';
						}
					} else {
						continue;
					}
				} else {
					continue;
				}
			} else if (direction == 'a') {
				if (y > 2 && y < 11 && x > 0 && x < 11) {
					if (boardA[x - 1][y - 1] == '~' && boardA[x - 1][y - 2] == '~'
							&& boardA[x - 1][y - 3] == '~') {
						for (int j = 0; j < 3; j++) {
							boardA[x - 1][y - j - 1] = 'D';
						}
					} else {
						continue;
					}
				} else {
					continue;
				}
			} else {
				continue;
			}

			break;
		}

		while (true) {

			x = (int) (Math.ceil(Math.random()*10));
			y = (int) (Math.ceil(Math.random()*10));
			int i = (int) (Math.random() * 4);
			char[] dir = new char[4];
			dir[0] = 'w';
			dir[1] = 's';
			dir[2] = 'a';
			dir[3] = 'd';
			direction = dir[i];

			if (direction == 'w') {
				if (x > 1 && x < 11 && y > 0 && y < 11) {
					if (boardA[x - 1][y - 1] == '~' && boardA[x - 2][y - 1] == '~') {
						for (int j = 0; j < 2; j++) {
							boardA[x - j - 1][y - 1] = 'P';
						}
					} else {
						continue;
					}
				} else {
					continue;
				}
			} else if (direction == 's') {
				if (x < 10 && x > 0 && y > 0 && y < 11) {
					if (boardA[x - 1][y - 1] == '~' && boardA[x][y - 1] == '~') {
						for (int j = 0; j < 2; j++) {
							boardA[x + j - 1][y - 1] = 'P';
						}
					} else {
						continue;
					}
				} else {
					continue;
				}
			} else if (direction == 'd') {
				if (y < 10 && y > 0 && x > 0 && x < 11) {
					if (boardA[x - 1][y - 1] == '~' && boardA[x - 1][y] == '~') {
						for (int j = 0; j < 2; j++) {
							boardA[x - 1][y + j - 1] = 'P';
						}
					} else {
						continue;
					}
				} else {
					continue;
				}
			} else if (direction == 'a') {
				if (y > 1 && y < 11 && x > 0 && x < 11) {
					if (boardA[x - 1][y - 1] == '~' && boardA[x - 1][y - 2] == '~') {
						for (int j = 0; j < 2; j++) {
							boardA[x - 1][y - j - 1] = 'P';
						}
					} else {
						continue;
					}
				} else {
					continue;
				}
			} else {
				continue;
			}

			printBoardA();
			break;
		}

	}

	/**
	 * Returns if this player has lost
	 * 
	 * @return true if this player has lost, false otherwise
	 */
	public boolean lost() {

		int loseCount = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (boardA[i][j] == 'H') {
					loseCount++;
				}
			}
		}
		if (loseCount == 17) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Prints this player's Board B.
	 */
	public void printBoard() {
		System.out.print("0 1 2 3 4 5 6 7 8 9 10\n");
		for (int k = 0; k < 10; k++) {
			System.out.print(k + 1);
			for (int j = 0; j < 10; j++) {
				System.out.print(" " + boardB[k][j]);
			}
			System.out.println();
		}
	}

	/**
	 * Prints this player's Board A (useful when placing ships)
	 */
	public void printBoardA() {
		System.out.print("0 1 2 3 4 5 6 7 8 9 10\n");
		for (int k = 0; k < 10; k++) {
			System.out.print(k + 1);
			for (int j = 0; j < 10; j++) {
				System.out.print(" " + boardA[k][j]);
			}
			System.out.println();
		}
	}
}
