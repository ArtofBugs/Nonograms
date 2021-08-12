/*
Uses brute force to generate all possible solutions to a given nonogram.
Author: ArtofBugs | Date: Summer 2021
*/

import java.io.File;
import java.util.Scanner;

public class BruteForce {
	
	static String path;
	Board info;
	int r;
	int c;
	static final boolean WHITE = false;
	static final boolean BLACK = true;
	Timer timer = new Timer();
	
	public BruteForce() {
		info = NonParser.NonParser(new File(path));
		r = info.getRows();
		c = info.getCols();
		timer.start();
		recurse(new Board(r, c), 0, 0);
	}
	public static void main (String [] args) {
		path = args[0];
		new BruteForce();
	}
	
	// Recursively generate board possibilities.
	public void recurse (Board currBoard, int currRow, int currCol) {

		// Base case
		if (currRow >= r) {
			boolean rowsOk =
                          currBoard.checkRowsSolution(info.getRowClues(false));
                        boolean ok =
                          rowsOk &&
			  currBoard.checkColsSolution(info.getColClues(false));
			if (ok) {
				timer.stop();
				System.out.println("SOLUTION");
				currBoard.printBoard();
			}
			return;
		}

		// Adjust the row and column indices to move to the
		// next position after this.
		int nextCol = currCol + 1;
		int nextRow = currRow;
		if (nextCol >= c) {
			nextCol = 0;
			nextRow = currRow + 1;
		}

		
		Board newBoard = currBoard.clone();
		newBoard.set(currRow, currCol, WHITE);
		recurse(newBoard, nextRow, nextCol);
		newBoard.set(currRow, currCol, BLACK);
		recurse(newBoard, nextRow, nextCol);
	}
}
