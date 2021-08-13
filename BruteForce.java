/*
Uses brute force to generate all possible solutions to a given nonogram.
Author: ArtofBugs | Date: Summer 2021
*/

import java.io.File;
import java.util.Scanner;

public class BruteForce {
	
	static final boolean WHITE = false;
	static final boolean BLACK = true;
	static Timer timer = new Timer();
	
	public static void main (String [] args) {
		Board info = NonParser.NonParser(new File(args[0]));
		timer.start();
		recurse(info, 0, 0);
	}

	// Recursively generate board possibilities.
	public static void recurse (Board currBoard, int currRow, int currCol) {

		// Base case
		if (currRow >= currBoard.rows) {
			if (currBoard.solved()) {
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
		if (nextCol >= currBoard.cols) {
			nextCol = 0;
			nextRow = currRow + 1;
		}

		currBoard.set(currRow, currCol, WHITE);
		recurse(currBoard, nextRow, nextCol);
		currBoard.set(currRow, currCol, BLACK);
		recurse(currBoard, nextRow, nextCol);
	}
}
