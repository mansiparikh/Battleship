
public class Battleship {
	/**
	 * @param args
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		char[][] boardAP1 = new char[10][10];
		char[][] boardBP1 = new char[10][10];
		char[][] boardAP2 = new char[10][10];
		char[][] boardBP2 = new char[10][10];
		
		int x;
		int y;

		initBoard(boardAP1);
		initBoard(boardBP1);
		initBoard(boardAP2);
		initBoard(boardBP2);

		printBoard(boardAP1);
		placeShips(boardAP1);

		printBoard(boardAP2);
		placeShips(boardAP2);
		
		while (true) {
		
		System.out.print("Enter row coordinate of hit: ");
		x = IO.readInt();
		System.out.print("Enter column coordinate of hit: ");
		y = IO.readInt();
		
		fire(boardAP1, boardBP2, x, y);
		fire(boardAP2, boardBP1, x, y);
		
		verify(boardAP1, boardBP2);
		verify(boardAP2, boardBP1);
		
		}
	}

	public static void initBoard(char[][] board) {

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				board[i][j] = '~'; 
			}
		}

	}

	public static void printBoard(char[][] board) {

		System.out.print("0 1 2 3 4 5 6 7 8 9 10\n");
		for (int k = 0; k < 10; k++) {
			System.out.print(k+1); 
			for (int j = 0; j < 10; j++) {
				System.out.print(" " + board[k][j]);
			}
			System.out.println();
		}

	}	

	public static void placeShips(char[][] board) {

		int x;
		int y;
		char direction; 

		while (true) {

			System.out.print("Enter row coordinate of placement of aircraft carrier: ");
			x = IO.readInt();
			System.out.print("Enter column coordinate of placement of aircraft carrier: ");
			y = IO.readInt();
			System.out.print("Enter direction of placement of aircraft carrier ('w' for up, 'a' for left', 's' for down, 'd' for right): ");
			direction = IO.readChar();

			if (direction == 'w') {
				if (x > 4 && x < 11 && y > 0 && y < 11) {
					if (board[x-1][y-1] == '~' && board[x-2][y-1] == '~' && board[x-3][y-1] == '~' && board[x-4][y-1] == '~' && board[x-5][y-1] == '~') {
						for (int j = 0; j < 5; j++) {
							board[x-j-1][y-1] = 'A';	
						}
					} else {
						continue;
					}
				} else {
					System.out.println("User entered an invalid input based on ship size and direction of placement.");
					continue;
				}
			}
			if (direction == 's') {
				if (x < 7 && x > 0 && y > 0 && y < 11) {
					if (board[x-1][y-1] == '~' && board[x][y-1] == '~' && board[x+1][y-1] == '~' && board[x+2][y-1] == '~' && board[x+3][y-1] == '~') {
						for (int j = 0; j < 5; j++) {
							board[x+j-1][y-1] = 'A';
						}
					} else {
						continue;
					}
				} else {
					System.out.println("User entered an invalid input based on ship size and direction of placement.");
					continue;
				}
			}
			if (direction == 'd') {
				if (y < 7 && y > 0 && x > 0 && x < 11) {
					if (board[x-1][y-1] == '~' && board[x-1][y] == '~' && board[x-1][y+1] == '~' && board[x-1][y+2] == '~' && board[x-1][y+3] == '~') {
						for (int j = 0; j < 5; j++) {
							board[x-1][y+j-1] = 'A';
						}
					} else {
						continue;
					}
				} else {
					System.out.println("User entered an invalid input based on ship size and direction of placement.");
					continue;
				}
			}
			if (direction == 'a') {
				if (y > 4 && y < 11 && x > 0 && x < 11) {
					if (board[x-1][y-1] == '~' && board[x-1][y-2] == '~' && board[x-1][y-3] == '~' && board[x-1][y-4] == '~' && board[x-1][y-5] == '~') {
						for (int j = 0; j < 5; j++) {
							board[x-1][y-j-1] = 'A';
						}
					} else {
						continue;
					}
				} else {
					System.out.println("User entered an invalid input based on ship size and direction of placement.");
					continue;
				}
			}

			printBoard(board);
			break;
		}

		while (true) {

			System.out.print("Enter row coordinate of placement of battleship: ");
			x = IO.readInt();
			System.out.print("Enter column coordinate of placement of battleship: ");
			y = IO.readInt();
			System.out.print("Enter direction of placement of battleship ('w' for up, 'a' for left', 's' for down, 'd' for right): ");
			direction = IO.readChar();

			if (direction == 'w') {
				if (x > 3 && x < 11 && y > 0 && y < 11) {
					if (board[x-1][y-1] == '~' && board[x-2][y-1] == '~' && board[x-3][y-1] == '~' && board[x-4][y-1] == '~') {
						for (int j = 0; j < 4; j++) {
							board[x-j-1][y-1] = 'B';	
						}
					} else {
						continue;
					}
				} else {
					System.out.println("User entered an invalid input based on ship size and direction of placement.");
					continue;
				}
			}
			if (direction == 's') {
				if (x < 8 && x > 0 && y > 0 && y < 11) {
					if (board[x-1][y-1] == '~' && board[x][y-1] == '~' && board[x+1][y-1] == '~' && board[x+2][y-1] == '~') {
						for (int j = 0; j < 4; j++) {
							board[x+j-1][y-1] = 'B';
						}
					} else {
						continue;
					}
				} else {
					System.out.println("User entered an invalid input based on ship size and direction of placement.");
					continue;
				}
			}
			if (direction == 'd') {
				if (y < 8 && y > 0 && x > 0 && x < 11) {
					if (board[x-1][y-1] == '~' && board[x-1][y] == '~' && board[x-1][y+1] == '~' && board[x-1][y+2] == '~') {
						for (int j = 0; j < 4; j++) {
							board[x-1][y+j-1] = 'B';
						}
					} else {
						continue;
					}
				} else {
					System.out.println("User entered an invalid input based on ship size and direction of placement.");
					continue;
				}
			}
			if (direction == 'a') {
				if (y > 3 && y < 11 && x > 0 && x < 11) {
					if (board[x-1][y-1] == '~' && board[x-1][y-2] == '~' && board[x-1][y-3] == '~' && board[x-1][y-4] == '~') {
						for (int j = 0; j < 4; j++) {
							board[x-1][y-j-1] = 'B';
						}
					} else {
						continue;
					}
				} else {
					System.out.println("User entered an invalid input based on ship size and direction of placement.");
					continue;
				}
			}
			printBoard(board);
			break;
		}

		while (true) {

			System.out.print("Enter row coordinate of placement of submarine: ");
			x = IO.readInt();
			System.out.print("Enter column coordinate of placement of submarine: ");
			y = IO.readInt();
			System.out.print("Enter direction of placement of submarine ('w' for up, 'a' for left', 's' for down, 'd' for right): ");
			direction = IO.readChar();

			if (direction == 'w') {
				if (x > 2 && x < 11 && y > 0 && y < 11) {
					if (board[x-1][y-1] == '~' && board[x-2][y-1] == '~' && board[x-3][y-1] == '~') {
						for (int j = 0; j < 3; j++) {
							board[x-j-1][y-1] = 'S';	
						}
					} else {
						continue;
					}
				} else {
					System.out.println("User entered an invalid input based on ship size and direction of placement.");
					continue;
				}
			}
			if (direction == 's') {
				if (x < 9 && x > 0 && y > 0 && y < 11) {
					if (board[x-1][y-1] == '~' && board[x][y-1] == '~' && board[x+1][y-1] == '~') {
						for (int j = 0; j < 3; j++) {
							board[x+j-1][y-1] = 'S';
						}
					} else {
						continue;
					}
				} else {
					System.out.println("User entered an invalid input based on ship size and direction of placement.");
					continue;
				}
			}
			if (direction == 'd') {
				if (y < 9 && y > 0 && x > 0 && x < 11) {
					if (board[x-1][y-1] == '~' && board[x-1][y] == '~' && board[x-1][y+1] == '~') {
						for (int j = 0; j < 3; j++) {
							board[x-1][y+j-1] = 'S';
						}
					} else {
						continue;
					}
				} else {
					System.out.println("User entered an invalid input based on ship size and direction of placement.");
					continue;
				}
			}
			if (direction == 'a') {
				if (y > 2 && y < 11 && x > 0 && x < 11) {
					if (board[x-1][y-1] == '~' && board[x-1][y-2] == '~' && board[x-1][y-3] == '~') {
						for (int j = 0; j < 3; j++) {
							board[x-1][y-j-1] = 'S';
						}
					} else {
						continue;
					}
				} else {
					System.out.println("User entered an invalid input based on ship size and direction of placement.");
					continue;
				}
			}
			printBoard(board);
			break;
		}

		while (true) {

			System.out.print("Enter row coordinate of placement of destroyer: ");
			x = IO.readInt();
			System.out.print("Enter column coordinate of placement of destroyer: ");
			y = IO.readInt();
			System.out.print("Enter direction of placement of destroyer ('w' for up, 'a' for left', 's' for down, 'd' for right): ");
			direction = IO.readChar();

			if (direction == 'w') {
				if (x > 2 && x < 11 && y > 0 && y < 11) {
					if (board[x-1][y-1] == '~' && board[x-2][y-1] == '~' && board[x-3][y-1] == '~') {
						for (int j = 0; j < 3; j++) {
							board[x-j-1][y-1] = 'D';	
						}
					} else {
						continue;
					}
				} else {
					System.out.println("User entered an invalid input based on ship size and direction of placement.");
					continue;
				}
			}
			if (direction == 's') {
				if (x < 9 && x > 0 && y > 0 && y < 11) {
					if (board[x-1][y-1] == '~' && board[x][y-1] == '~' && board[x+1][y-1] == '~') {
						for (int j = 0; j < 3; j++) {
							board[x+j-1][y-1] = 'D';
						}
					} else {
						continue;
					}
				} else {
					System.out.println("User entered an invalid input based on ship size and direction of placement.");
					continue;
				}
			}
			if (direction == 'd') {
				if (y < 9 && y > 0 && x > 0 && x < 11) {
					if (board[x-1][y-1] == '~' && board[x-1][y] == '~' && board[x-1][y+1] == '~') {
						for (int j = 0; j < 3; j++) {
							board[x-1][y+j-1] = 'D';
						}
					} else {
						continue;
					}
				} else {
					System.out.println("User entered an invalid input based on ship size and direction of placement.");
					continue;
				}
			}
			if (direction == 'a') {
				if (y > 2 && y < 11 && x > 0 && x < 11) {
					if (board[x-1][y-1] == '~' && board[x-1][y-2] == '~' && board[x-1][y-3] == '~') {
						for (int j = 0; j < 3; j++) {
							board[x-1][y-j-1] = 'D';
						}
					} else {
						continue;
					}
				} else {
					System.out.println("User entered an invalid input based on ship size and direction of placement.");
					continue;
				}
			}
			printBoard(board);
			break;
		}

		while (true) {

			System.out.print("Enter row coordinate of placement of patrol boat: ");
			x = IO.readInt();
			System.out.print("Enter column coordinate of placement of patrol boat: ");
			y = IO.readInt();
			System.out.print("Enter direction of placement of patrol boat ('w' for up, 'a' for left', 's' for down, 'd' for right): ");
			direction = IO.readChar();

			if (direction == 'w') {
				if (x > 1 && x < 11 && y > 0 && y < 11) {
					if (board[x-1][y-1] == '~' && board[x-2][y-1] == '~') {
						for (int j = 0; j < 2; j++) {
							board[x-j-1][y-1] = 'P';	
						}
					} else {
						continue;
					}
				} else {
					System.out.println("User entered an invalid input based on ship size and direction of placement.");
					continue;
				}
			}
			if (direction == 's') {
				if (x < 10 && x > 0 && y > 0 && y < 11) {
					if (board[x-1][y-1] == '~' && board[x][y-1] == '~') {
						for (int j = 0; j < 2; j++) {
							board[x+j-1][y-1] = 'P';
						}
					} else {
						continue;
					}
				} else {
					System.out.println("User entered an invalid input based on ship size and direction of placement.");
					continue;
				}
			}
			if (direction == 'd') {
				if (y < 10 && y > 0 && x > 0 && x < 11) {
					if (board[x-1][y-1] == '~' && board[x-1][y] == '~') {
						for (int j = 0; j < 2; j++) {
							board[x-1][y+j-1] = 'P';
						}
					} else {
						continue;
					}
				} else {
					System.out.println("User entered an invalid input based on ship size and direction of placement.");
					continue;
				}
			}
			if (direction == 'a') {
				if (y > 1 && y < 11 && x > 0 && x < 11) {
					if (board[x-1][y-1] == '~' && board[x-1][y-2] == '~') {
						for (int j = 0; j < 2; j++) {
							board[x-1][y-j-1] = 'P';
						}
					} else {
						continue;
					}
				} else { 
					System.out.println("User entered an invalid input based on ship size and direction of placement.");
					continue;
				}
			}
			printBoard(board);
			break;
		}
	}


	public static char fire(char[][] boardA, char[][] boardB, int x, int y) {

		if (boardA[x-1][y-1] == 'A') {
			boardB[x-1][y-1] = 'H';
			printBoard(boardB);
			return 'A';
		} else if (boardA[x-1][y-1] == 'B') {
			boardB[x-1][y-1] = 'H';
			printBoard(boardB);
			return 'B';
		} else if (boardA[x-1][y-1] == 'S') {
			boardB[x-1][y-1] = 'H';
			printBoard(boardB);
			return 'S';
		} else if (boardA[x-1][y-1] == 'D') {
			boardB[x-1][y-1] = 'H';
			printBoard(boardB);
			return 'D';
		} else if (boardA[x-1][y-1] == 'P') {
			boardB[x-1][y-1] = 'H';
			printBoard(boardB);
			return 'P';
		} else {
			boardB[x-1][y-1] = 'M';
			printBoard(boardB);
			return 'M';
		}

	}

	public static boolean verify(char[][] boardA, char[][] boardB) {

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (Character.isLetter(boardA[i][j]) && (boardB[i][j]) == 'H') {
					return true;
				}
			}

		} return false;

	}

}
