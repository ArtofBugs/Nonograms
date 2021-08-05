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
			//boolean[][] newBoard1 = new boolean[r][c];
			//newBoard1 = currBoard;
			System.out.println(currRow + "," + currCol);
			System.out.println("unfilled \n");
			printBoard(currBoard);
			recurse(currBoard, currRow, currCol+1); //currCell left white
			currBoard[currRow][currCol] = BLACK; //currCell made black
			System.out.println(currRow + "," + currCol);
			System.out.println("filled \n");
			printBoard(currBoard);
			//boolean[][] newBoard2 = new boolean[r][c];
			//newBoard2 = currBoard;
			recurse(currBoard, currRow, currCol+1);	
		}
		else if (currRow == r-1) { //and of course currCol == c-1
			//end of board
			
			System.out.println(currRow + "," + currCol);
			System.out.println("end of board");
			boolean[][] newAddBoard1 = new boolean[r][c];
			copyBoard(currBoard, newAddBoard1); //TODO write this method
			boards.add(newAddBoard1);
			printBoard(newAddBoard1);
			System.out.println("Board Size: " + boards.size());
			currBoard[currRow][currCol] = BLACK;
			System.out.println(currRow + "," + currCol);
			System.out.println("end of board");	
			boolean[][] newAddBoard2 = new boolean[r][c];
			copyBoard(currBoard, newAddBoard2);
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
			//boolean[][] newBoard3 = new boolean[r][c];
			//newBoard3 = currBoard;
			System.out.println(currRow + "," + currCol);
			System.out.println("next line (unfilled)");
			printBoard(currBoard);
			recurse(currBoard, currRow+1, 0);
			currBoard[currRow][currCol] = BLACK;
			//boolean[][] newBoard4 = new boolean[r][c];
			//newBoard4 = currBoard;
			System.out.println(currRow + "," + currCol);
			System.out.println("next line (filled)");
			printBoard(currBoard);
			recurse(currBoard, currRow+1, 0);
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
