/*
Uses backtracking algorithm to solve a nonogram.
Author: ArtofBugs | Date: Summer 2021
*/

import java.io.File;
public class Backtracking {
	
	static String path;
	Board info;
	int r;
	int c;
	static final boolean WHITE = false;
	static final boolean BLACK = true;
	Timer timer = new Timer();
	
	public Backtracking (){
		info = NonParser.NonParser(new File(path));
		r = info.getRows();
		c = info.getCols();
		timer.start();
		recurse(new Board(r, c), 0, 0);	
	}
	
	public void recurse (Board currBoard, int currRow, int currCol) {
		
		if (currCol < c-1) {
			Board newBoard1 = currBoard.clone();
			recurse(newBoard1, currRow, currCol+1);
			Board newBoard2 = currBoard.clone();
			newBoard2.set(currRow, currCol, BLACK);
			recurse(newBoard2, currRow, currCol+1);
		}
		else if (currRow < r-1) {
			Board newBoard3 = currBoard.clone();
			if (!checkRow(newBoard3, currRow)) {
				return;
			}
			else {
				recurse(newBoard3, currRow+1, 0);
				System.err.println("success: " + currRow);
			}
			Board newBoard4 = currBoard.clone();
			newBoard4.set(currRow, currCol, BLACK);
			if (!checkRow(newBoard4, currRow)) {
				return;
			}
			else {
				recurse(newBoard4, currRow+1, 0);
				System.err.println("success: " + currRow);
			}
		}
		else {
			Board newBoard5 = new Board(r, c);
			newBoard5 = currBoard.clone();
			if (newBoard5.checkRowsSolution(info.getRowClues(false))
				&& newBoard5.checkColsSolution(info.getColClues(false))) {
				timer.stop();
				System.out.println("SOLUTION");
				newBoard5.printBoard();
			}
			Board newBoard6 = new Board(r, c);
			newBoard6 = currBoard.clone();
			newBoard6.set(currRow, currCol, BLACK);
			if (newBoard6.checkRowsSolution(info.getRowClues(false))
				&& newBoard6.checkColsSolution(info.getColClues(false))) {
				timer.stop();
				System.out.println("SOLUTION");
				newBoard6.printBoard();
			}
			return;
		}
		
		/*if (currCol < c-1) {
			boolean[] newLine = new boolean[c];
			
			Board newBoard2 = currBoard.clone();
			newBoard2.set(currRow, currCol, BLACK);
			recurse (newBoard2, currRow, currCol+1);
		}
		else {
		}
		*/
		/*generate*/
		
		// generate all possible combos for this row
			// for each row, if the generated clue would match the
			// given clue for that row, continue to the next step
			// otherwise return
			
			//(at this moment, it will generate combos without
			//thinking about which ones are impossible
	}
	
	public static void main(String[] args) {
		path = args[0];
		new Backtracking();
	}
	
	public boolean checkRow(Board currBoard, int currRow) {
		if (currBoard.getRowClues(true).get(currRow).length !=
			info.getRowClues(false).get(currRow).length) {
			System.err.println("failure - length mismatch: " + currRow);
			currBoard.printBoard();
			return false;
		}
		if (currBoard.getRowClues(false).get(currRow).length != 0) {
			for (int col = 0; col <
			currBoard.getRowClues(false).get(currRow).length; col++) {
				if (currBoard.getRowClues(false).get(currRow)[col] !=
					info.getRowClues(false).get(currRow)[col]) {
					System.err.println("failure - content mismatch: " + currRow);
					currBoard.printBoard();
					return false;
				}
			}
		}
		return true;
	}
}
