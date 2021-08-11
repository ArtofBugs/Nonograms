/*
Uses brute force to generate all possible solutions to a given nonogram.
Author: ArtofBugs | Date: Summer 2021
*/

import java.util.ArrayList;
import java.io.File;

public class BruteForce {
	
	final Board info = NonParser.NonParser(new File("test.non"));
	final int r = info.getRows();
	final int c = info.getCols();
	final boolean WHITE = false;
	final boolean BLACK = true;
	ArrayList<boolean[][]> boards = new ArrayList <boolean[][]>();
	ArrayList<Board> possibilites = new ArrayList<Board>();
	
	public BruteForce() {
		System.out.println("r = " + r);
		System.out.println("c = " + c);
		recurse(new boolean[r][c], 0, 0);
	}
	public static void main (String [] args) {
		new BruteForce();
	}
	
	// Recursively generate board possibilites
	public void recurse (Board currBoard, int currRow, int currCol) {
		// Recursive cases: if and else if
		if (currCol < c-1) {
			Board newBoard1 = currBoard.clone();
			recurse(newBoard1, currRow, currCol+1);
			Board newBoard2 = currBoard.clone();
			newBoard2.set(currRow, currCol, BLACK);
			recurse (newBoard2, currRow, currCol+1);
		}
		else if (currRow < r-1) {
			Board newBoard3 = currBoard.clone();
			recurse(newBoard3, currRow+1, 0);
			Board newBoard4 = currBoard.clone();
			newBoard4.set(currRow, currCol, BLACK);
			recurse(newBoard4, currRow+1, 0);
		}
		// Base case
		else {
			Board newBoard5 = new Board(r, c);
			newBoard5 = currBoard.clone();
			printBoard(newBoard5);
			Board newBoard6 = new Board(r, c);
			newBoard6 = currBoard.clone();
			newBoard6.set(currRow, currCol, BLACK);
			printBoard(newBoard6);
			return;
		}
	}
	
	
	// TO BE DELETED--------------
	
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
			recurse(newBoard1, currRow, currCol+1);
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
			newBoard2[currRow][currCol] = BLACK;
			recurse(newBoard2, currRow, currCol+1);	
		}
		else if (currRow == r-1) {
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
			boolean[][] newAddBoard2 = new boolean[r][c];
			for (int i = 0; i < currBoard.length; i++) {
				for (int j = 0; j < currBoard[0].length; j++) {
					if (currBoard[i][j] == true) {
						newAddBoard2[i][j] = true;
					}
					else {
						newAddBoard2[i][j] = false;
					}
				}
			}
			newAddBoard2[currRow][currCol] = BLACK;
			boards.add(newAddBoard2);
		
			for (int n = 0; n < boards.size(); n++) {
			   printBoard(boards.get(n));
			}
			System.out.println("Boards size: " + boards.size());
			// convert finished board to a Board object; then check to see if it fits the clues
			// TODO
			
		}
		else {
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
			recurse(newBoard3, currRow+1, 0);
			
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
			newBoard4[currRow][currCol] = BLACK;
			recurse(newBoard4, currRow+1, 0);
		}

	}
	
	//---------------------------
	
	// Print boards to standard output 
	public void printBoard(Board board) {
		System.out.println("-------------------");
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				System.out.print(board.getSquares()[i][j] + "||");
			}
			System.out.println();
		}
	}
	
	// Print boards to standard output 
	public void printBoard(boolean[][] board) {
		System.out.println("-------------------");
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				System.out.print(board[i][j] + "||");
			}
			System.out.println();
		}
	}

}
