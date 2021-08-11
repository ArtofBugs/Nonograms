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
	static final boolean WHITE = false;
	static final boolean BLACK = true;
	
	public BruteForce() {
		recurse(new Board(r, c), 0, 0);
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
			newBoard5.updateRowClues();
			newBoard5.updateColClues();
			newBoard5.printRowClues();
			if (newBoard5.checkRowsSolution(info.getRowClues(false))
				&& newBoard5.checkColsSolution(info.getColClues(false))) {
				System.err.println("SOLUTION");
				printBoard(newBoard5);
			}
			Board newBoard6 = new Board(r, c);
			newBoard6 = currBoard.clone();
			newBoard6.set(currRow, currCol, BLACK);
			newBoard6.updateRowClues();
			newBoard6.updateColClues();
			newBoard6.printRowClues();
			if (newBoard6.checkRowsSolution(info.getRowClues(false))
				&& newBoard6.checkColsSolution(info.getColClues(false))) {
				System.err.println("SOLUTION");
				printBoard(newBoard6);
			}
			return;
		}
	}
	
	// Print boards to standard error
	public void printBoard(Board board) {
		System.err.println("-------------------");
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				System.err.print(board.getSquares()[i][j] + "||");
			}
			System.err.println();
		}
	}
}
