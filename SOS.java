/* IAKOVIDIS MARIOS AM 4063
NAKOS TILEMACHOS AM 4125
PETSIOS THEOFILOS-GEORGRIOS AM 4158*/

import java.util.Scanner;
import java.lang.Math;

public class SOS
{
	private char optimalMove;

	public Boolean checkForDraw(char[][] board)
	{
		for(int i = 0;i<3;i++)
		{
			for(int j = 0;j<3;j++)
			{
				if(board[i][j] == '-')
				{
					return false;
				}
			}
		}
		return true;
	}

	public int checkForWinner(char[][] board,boolean lastMoveIsMax)
	{
		for(int i = 0;i<3;i++)
		{
			if(board[i][0] == 'S' && board[i][1] == 'O' && board[i][2] == 'S')
			{
				if(lastMoveIsMax == true)
				{
					return 1;
				}else{
					return -1;
				}
			}
		}

		for(int j = 0;j<3;j++)
		{
			if(board[0][j] == 'S' && board[1][j] == 'O' && board[2][j] == 'S')
			{
				if(lastMoveIsMax == true)
				{
					return 1;
				}else{
					return -1;
				}
			}
		}

		if(board[0][0] == 'S' && board[1][1] =='O' && board[2][2] == 'S')
		{
			if(lastMoveIsMax = true)
			{
				return 1;
			}else{
				return -1;
			}
		}

		if(board[0][2] == 'S' && board[1][1] == 'O' && board[2][0] == 'S')
		{
			if(lastMoveIsMax == true)
			{
				return 1;
			}else{
				return -1;
			}
		}
		return 0; 
	}

	public int minimax(char[][]board,int iterations,boolean maxTurn)
	{
		int value = this.checkForWinner(board,!maxTurn);
		if(value == 1)
		{
			return value;
		}else if(value == -1)
		{
			return value;
		}

		boolean draw = this.checkForDraw(board);
		
		if(draw == true)
		{
			return 0;
		}

		if(maxTurn == true)
		{
			int optimalValue = -10;
			int valueForS = -10;
			int valueForO = -10
;			for(int i = 0;i<3;i++)
			{
				for(int j = 0;j<3;j++)
				{
					if(board[i][j] == '-')
					{
						board[i][j] = 'S';
						valueForS = Math.max(optimalValue,this.minimax(board,iterations + 1,!maxTurn));
						board[i][j] = 'O';
						valueForO = Math.max(optimalValue,this.minimax(board,iterations + 1,!maxTurn));
						if(valueForS >= valueForO)
						{
							optimalValue = valueForS;
							this.optimalMove = 'S';
						}else{
							optimalValue = valueForO;
							this.optimalMove = 'O';
						}
						board[i][j] = '-';
					}
				}
			}
			return optimalValue;
		}else{
			int optimalValue = 10;
			int valueForS = 10;
			int valueForO = 10;
			for(int i = 0;i<3;i++)
			{
				for(int j = 0;j<3;j++)
				{
					if(board[i][j] == '-')
					{
						board[i][j] = 'S';
						valueForS = Math.min(optimalValue,this.minimax(board,iterations + 1,!maxTurn));
						board[i][j] = 'O';
						valueForO = Math.min(optimalValue,this.minimax(board,iterations + 1,!maxTurn));
						if(valueForS <= valueForO)
						{
							optimalValue = valueForS;
							this.optimalMove = 'S';
						}else{
							optimalValue = valueForO;
							this.optimalMove = 'O';
						}
						board[i][j] = '-';
					}
				}
			}
			return optimalValue;
		}
	}

	public char[][] moveMax(char[][]board)
	{
		char[][] returnBoard = board;
		int optimalValue = -10;
		int row = -1;
		int column = -1;
		int valueForS;
		int valueForO;
		char optimalMove = 'S';
		for(int i = 0;i<3;i++)
		{
			for(int j = 0;j<3;j++)
			{
				if(returnBoard[i][j] == '-')
				{
					returnBoard[i][j] = 'S';
					valueForS = this.minimax(returnBoard,0,false);
					returnBoard[i][j] = 'O';
					valueForO = this.minimax(returnBoard,0,false);
					returnBoard[i][j] = '-';
					if(valueForS > valueForO)
					{
						if(valueForS >= optimalValue)
						{
							optimalValue = valueForS;
							optimalMove = 'S';
							row = i;
							column = j;
						}
					}else{
						if(valueForO >= optimalValue)
						{
							optimalValue = valueForO;
							optimalMove = 'O';
							row = i;
							column = j;
						}
					}
				}
			}
		}
		returnBoard[row][column] = optimalMove;
		return returnBoard;
	}

	public char[][] moveMin(char[][] board)
	{
		Scanner input = new Scanner(System.in);
		int row;
		int column;
		char move;
		char[][] returnBoard = board;
		while(true)
		{
			System.out.print("Enter row number 0 for top 1 for middle and 2 for bottom:");
			row = input.nextInt();
			System.out.println();
			System.out.print("Enter column number 0 for left 1 for middle and 2 for right:");
			column = input.nextInt();
			System.out.println();
			System.out.print("Enter S or O:");
			move = input.next().charAt(0);
			System.out.println();
			if(returnBoard[row][column] == '-')
			{
				break;
			}
			System.out.println("Enter different position.");
		}
		returnBoard[row][column] = move;
		return returnBoard;
	}

	public void printBoard(char[][]board)
	{
		for(int i = 0;i<3;i++)
		{
			for(int j = 0;j<3;j++)
			{
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) 
	{
		char[][] board = {{'-','-','-'},{'-','-','O'},{'-','-','-'}};
		SOS game = new SOS();
		int winner = 0;
		boolean draw = false;
		System.out.println("SOS GAME");
		game.printBoard(board);
		while(true)
		{
			System.out.println("CPU move");
			board = game.moveMax(board);
			winner = game.checkForWinner(board,true);
			if(winner == 1)
			{
				game.printBoard(board);
				System.out.println("CPU wins!!");
				break;
			}
			draw = game.checkForDraw(board);
			if(draw == true)
			{
				game.printBoard(board);
				System.out.println("Draw.");
				break;
			}
			game.printBoard(board);
			System.out.println();
			System.out.println("Player move.");
			board = game.moveMin(board);
			winner = game.checkForWinner(board,false);
			if(winner == -1)
			{
				game.printBoard(board);
				System.out.println("You win!!");
				break;
			}
			draw = game.checkForDraw(board);
			if(draw == true)
			{
				game.printBoard(board);
				System.out.println("Draw.");
				break;
			}
			game.printBoard(board);
		}
	}
}