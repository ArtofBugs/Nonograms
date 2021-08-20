/*
    Parser for .non file format (more info in FORMAT.md from mikix's repo at
    https://github.com/mikix/nonogram-db).
    ArtofBugs | August 2021
*/

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.ArrayList;

public class NonParser {
	static int h, w;
	
	public static Board NonParser(File file) {
        
        h = findHeight(file);
        w = findWidth(file);
		Board info = new Board (h, w);
		info.rowClues = findRowClues(file);
		info.colClues = findColClues(file);
		return info;

	}
	
	public static void main (String [] args) {
		new NonParser();
	}
	
	// Look for the "height" key in the file and return the height it points to.
	public static int findHeight(File file) {
		
		int height = 0;
		String currLine = "";
		
		try {
			Scanner fileScanner = new Scanner(file);
			while (true) {
				try {
					currLine = fileScanner.nextLine();
					if (currLine.contains("height")) {
						String heightStr = "";
						for (int i = 0; i < currLine.length(); i++) {
							if (currLine.charAt(i) <= '9'
							    && currLine.charAt(i) >= '0') {
								heightStr += currLine.charAt(i);
							}
						}
						if (!heightStr.isEmpty()) {
							height = Integer.parseInt(heightStr);
							return height;
						}
					}
				
				}
				catch (NoSuchElementException nsee) {
					System.err.println("No height found");
					fileScanner.close();
					System.exit(1);
					return -1;					
				}
			}		
		}
		catch (IOException ex) {
			System.err.println("File not found.");
			System.exit(1);
			return -1;
		}
	}
	
	// Look for the "width" key in the file and return the width it points to.
	public static int findWidth(File file) {
		int width = 0;
		String currLine = "";
		
		try {
			Scanner fileScanner = new Scanner(file);
			while (true) {
				try {
					currLine = fileScanner.nextLine();
					if (currLine.contains("width")) {
						String widthStr = "";
						for (int i = 0; i < currLine.length(); i++) {
							if (currLine.charAt(i) <= '9'
							    && currLine.charAt(i) >= '0') {
								widthStr += currLine.charAt(i);
							}
						}
						if (!widthStr.isEmpty()) {
							width = Integer.parseInt(widthStr);
							return width;
						}
					}
				
				}
				catch (NoSuchElementException nsee) {
					System.err.println("No width found");
					fileScanner.close();
					System.exit(1);
					return -1;					
				}
			}		
		}
		catch (IOException ex) {
			System.err.println("File not found.");
			System.exit(1);
			return -1;
		}
	}
	
	//Layout: rowClues is an ArrayList of int arrays. The int arrays contain
	//the clues in each row; the outer ArrayList represents a list of rows.
	/*For example:
    
    .........ARRAYLIST.........    
    .                         .	
      A	0) 2 | 2 | 4 | 3               . * * . . . * * . * * * * . . * * * . .
      R	1) 2 | 2 | 2 | 2               . . * * . * * . . * * . . . * * . . . .
      R	2) 3 | 4 | 3           might   . . . * * * . . . * * * * . * * * . . .
      A	3) 1 | 4 | 3        correspond . . . . * . . . . * * * * . . * * * . .
      Y	4) 1 | 2 | 2            to     . . . . * . . . . * * . . . . . * * . .
      S	5) 1 | 4 | 3                   . . . . * . . . . * * * * . * * * . . .
	.                         .
	...........................
	
	*/
	
	public static ArrayList<int[]> findRowClues (File file) {
		ArrayList<int[]> rowClues = new ArrayList<int[]>();
		String currLine = "";
		try {
			Scanner fileScanner = new Scanner(file);
			while (true) {
				try {
					currLine = fileScanner.nextLine();
					if (currLine.contains("rows")) {
						for (int row = 0; row < h; row++) {
						    currLine = fileScanner.nextLine();
							int[] clueInts = new int[0];
							if (!currLine.isEmpty()) {
							    String[] clueStrings = currLine.split(",");
							    if (clueStrings.length != 0
							        && !clueStrings[0].equals("0")) {
							        clueInts = new int[clueStrings.length];
							        for (int i = 0; i < clueStrings.length;
							            i++) {
							            clueInts[i] =
							            Integer.parseInt(clueStrings[i]);
							        }
							    }
                            }
                            rowClues.add(clueInts);
						}
						break;
					}
				}
				catch (NoSuchElementException nsee) {
					if (rowClues.size() == 0) {
						System.err.println("No rows found");
						fileScanner.close();
						System.exit(1);
					}				
				}
			}		
		}
		catch (IOException ex) {
			System.err.println("File not found.");
			System.exit(1);
		}
		return rowClues;
	}
	
	/* colClues follows a layout similar to rowClues:
	
       ...                                        ...
     A .                                            .
     R .               A R R A Y S                  .
     R . ========================================== .
     A .                       1 1                  .
     Y .                       - -                  .
     L .                       2 2   2 4 1 1        .
     I .                       - -   - - - -        .
     S . 0 1 2 2 4 2 2 1 0 6 6 1 1 0 1 1 4 2 0 0    .
     T .                                            .
     S .                                            .
       ...                                        ...
          
         . * * . . . * * . * * * * . . * * * . .
         . . * * . * * . . * * . . . * * . . . .
         . . . * * * . . . * * * * . * * * . . .
         . . . . * . . . . * * * * . . * * * . .
         . . . . * . . . . * * . . . . . * * . .
         . . . . * . . . . * * * * . * * * . . .
	*/
	public static ArrayList<int[]> findColClues (File file) {
		ArrayList<int[]> colClues = new ArrayList<int[]>();
		String currLine = "";
		try {
			Scanner fileScanner = new Scanner(file);
			while (true) {
				try {
					currLine = fileScanner.nextLine();
					if (currLine.contains("columns")) {
						for (int col = 0; col < w; col++) {
							currLine = fileScanner.nextLine();
							int[] clueInts = new int[0];
							if (!currLine.isEmpty()) {
							    String[] clueStrings = currLine.split(",");
							    if (clueStrings.length != 0
							        && !clueStrings[0].equals("0")) {
							        clueInts = new int[clueStrings.length];
							        for (int i = 0; i < clueStrings.length;
							            i++) {
							            clueInts[i]
							            = Integer.parseInt(clueStrings[i]);
							        }
							    }
                            }
                            colClues.add(clueInts);
						}
						break;
					}
				}
				catch (NoSuchElementException nsee) {
					if (colClues.size() == 0) {
						System.err.println("No cols found");
						fileScanner.close();
						System.exit(1);
					}				
				}
			}		
		}
		catch (IOException ex) {
			System.err.println("File not found.");
			System.exit(1);
		}
		return colClues;
	}
}
