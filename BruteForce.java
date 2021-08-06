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
	ArrayList <Board> boards = new ArrayList <Board>();
	
	public BruteForce() {
		//generate all possible r by c boards
		System.out.println("r = " + r);
		recurse(new Board(r, c), 0, 0);
		System.out.println(boards.size());
		for (int n = 0; n < boards.size(); n++) {
			printBoard(boards.get(n));
			System.out.println("n = " + n);
		}
	}
	public static void main (String [] args) {
		new BruteForce();
	}
	
	//Recursively generate board possibilites
	public void recurse(Board currBoard, int currRow, int currCol) {
                if (currCol >= c) {
                        currCol = 0;
                        currRow += 1;
                }
                if (currRow >= r) {
                        boards.add(currBoard.clone());
                        return;
                }
                currBoard.set(currRow, currCol, true);
                recurse(currBoard, currRow, currCol+1);
                currBoard.set(currRow, currCol, false);
                recurse(currBoard, currRow, currCol+1);
	}
	
	//Print boards to standard output 
	public void printBoard(Board grid) {
		System.out.println("-------------------");
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				System.out.print(grid.get(i, j) + "||");
			}
			System.out.println();
		}
	}
	
	public boolean checker(boolean[][] grid, boolean[][] game) {
		return false;
	}
}
