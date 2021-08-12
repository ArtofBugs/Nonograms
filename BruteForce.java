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
		// Scanner sysScanner = new Scanner(System.in);
		// System.out.println("Enter file path");
		// String path = sysScanner.nextLine();
		info = NonParser.NonParser(new File(path));
		r = info.getRows();
		c = info.getCols();
		timer.start();
		recurse(new Board(r, c), 0, 0);
		// sysScanner.close();
	}
	public static void main (String [] args) {
		path = args[0];
		new BruteForce();
	}
	
	// Recursively generate board possibilities.
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
			if (newBoard5.checkRowsSolution(info.getRowClues(false))
				&& newBoard5.checkColsSolution(info.getColClues(false))) {
				timer.stop();
				System.out.println("SOLUTION");
				printBoard(newBoard5);
			}
			Board newBoard6 = new Board(r, c);
			newBoard6 = currBoard.clone();
			newBoard6.set(currRow, currCol, BLACK);
			newBoard6.updateRowClues();
			newBoard6.updateColClues();
			if (newBoard6.checkRowsSolution(info.getRowClues(false))
				&& newBoard6.checkColsSolution(info.getColClues(false))) {
				timer.stop();
				System.out.println("SOLUTION");
				printBoard(newBoard6);
			}
			return;
		}
	}
	
	// Print boards to standard output.
	public void printBoard(Board board) {
		System.out.println("-------------------");
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (board.getSquares()[i][j]) {
					System.out.print("* ");
				}
				else {
					System.out.print(". ");
				}
			}
			System.out.println();
		}
	}
}
