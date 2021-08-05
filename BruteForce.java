/*
Uses brute force to generate all possible solutions to a given nonogram
Author: ArtofBugs | Date: Summer 2021
*/

import java.util.ArrayList;

public class BruteForce {
	
	final int[] info = Interpreter.Interpreter();
	final int r = info[0];
	final int c = info[1];
	final boolean WHITE = false;
	final boolean BLACK = true;
	ArrayList <boolean [][]> boards = new ArrayList <boolean [][]>();
	
	public BruteForce() {
		//generate all possible r by c boards
		System.out.println("r = " + r);
		recurse(new boolean[r][c], 0, 0);
		System.out.println(boards.size());
		for (int n = 0; n < boards.size(); n++) {
			printBoard(boards.get(n));
			System.out.println("n = " + n);
		}
	}
	public static void main (String [] args) {
		new BruteForce();
	}
	
	//Writing recursion all on my own...
	public void recurse (boolean[][] currBoard, int currRow, int currCol) {
		if (currCol < c-1) {
			boolean[][] newBoard1 = new boolean[r][c];
			for (int i = 0; i < currBoard.length; i++) {
				for (int j = 0; j < currBoard[0].length; j++) {
					if (currBoard[i][j] == true) {
						newBoard1[i][j] = true;
					}
					else {
						newBoard1[i][j] = false;
					}
				}
			}			
			System.out.println(currRow + "," + currCol);
			System.out.println("unfilled \n");
			printBoard(newBoard1);
			recurse(newBoard1, currRow, currCol+1); //currCell left white
			currBoard[currRow][currCol] = BLACK; //currCell made black
			System.out.println(currRow + "," + currCol);
			System.out.println("filled \n");
			boolean[][] newBoard2 = new boolean[r][c];
			for (int i = 0; i < currBoard.length; i++) {
				for (int j = 0; j < currBoard[0].length; j++) {
					if (currBoard[i][j] == true) {
						newBoard2[i][j] = true;
					}
					else {
						newBoard2[i][j] = false;
					}
				}
			}
			printBoard(newBoard2);
			recurse(newBoard2, currRow, currCol+1);	
		}
		else if (currRow == r-1) { //and of course currCol == c-1
			//end of board
			
			System.out.println(currRow + "," + currCol);
			System.out.println("end of board");
			boolean[][] newAddBoard1 = new boolean[r][c];
			for (int i = 0; i < currBoard.length; i++) {
				for (int j = 0; j < currBoard[0].length; j++) {
					if (currBoard[i][j] == true) {
						newAddBoard1[i][j] = true;
					}
					else {
						newAddBoard1[i][j] = false;
					}
				}
			}
			boards.add(newAddBoard1);
			printBoard(newAddBoard1);
			System.out.println("Boards Size: " + boards.size());
			currBoard[currRow][currCol] = BLACK;
			System.out.println(currRow + "," + currCol);
			System.out.println("end of board");	
			boolean[][] newAddBoard2 = new boolean[r][c];
			for (int i = 0; i < currBoard.length; i++) {
				for (int j = 0; j < currBoard[0].length; j++) {
					if (currBoard[i][j] == true) {
						newAddBoard1[i][j] = true;
					}
					else {
						newAddBoard1[i][j] = false;
					}
				}
			}
			printBoard(newAddBoard2);
			boards.add(newAddBoard2);
			System.out.println("Board Size: " + boards.size());
			System.out.println("end of board");
			//return;
			
			
			
			//operate on the finished board -- check to see if it fits the clues
			//TODO
			
		}
		else { //currCol == c-1 but currRow < r-1
			//move to next row, go to first col
			boolean[][] newBoard3 = new boolean[r][c];
			for (int i = 0; i < currBoard.length; i++) {
				for (int j = 0; j < currBoard[0].length; j++) {
					if (currBoard[i][j] == true) {
						newBoard3[i][j] = true;
					}
					else {
						newBoard3[i][j] = false;
					}
				}
			}
			System.out.println(currRow + "," + currCol);
			System.out.println("next line (unfilled)");
			printBoard(newBoard3);
			recurse(newBoard3, currRow+1, 0);
			currBoard[currRow][currCol] = BLACK;
			boolean[][] newBoard4 = new boolean[r][c];
			for (int i = 0; i < currBoard.length; i++) {
				for (int j = 0; j < currBoard[0].length; j++) {
					if (currBoard[i][j] == true) {
						newBoard4[i][j] = true;
					}
					else {
						newBoard4[i][j] = false;
					}
				}
			}
			System.out.println(currRow + "," + currCol);
			System.out.println("next line (filled)");
			printBoard(newBoard4);
			recurse(newBoard4, currRow+1, 0);
		}

	}
	
	//Print boards to standard output 
	public void printBoard(boolean[][] grid) {
		System.out.println("-------------------");
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				System.out.print(grid[i][j] + "||");
			}
			System.out.println();
		}
	}
	
	public boolean checker(boolean[][] grid, boolean[][] game) {
		return false;
	}
}
