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
		
	}
	public static void main(String[] args) {
		path = args[0];
		new Backtracking();
	}
}
