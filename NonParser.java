import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.ArrayList;

public class NonParser {
	
	static Scanner sysScanner;
	
	public static Board NonParser(File file) {

		// sysScanner = new Scanner(System.in);
		// System.err.println("Enter file path");
		// String path = sysScanner.nextLine();
		Board info = new Board (findHeight(file), findWidth(file));
		info.setRowClues(findRowClues(file));
		info.setColClues(findColClues(file));
		System.err.println("Clues copied: " +
			info.checkRowsSolution(findRowClues(file))
			+ info.checkColsSolution(findColClues(file)));
		System.err.println("Searching for:");
		info.printRowClues();
		System.err.println("START");
		// sysScanner.close();
		return info;

	}
	
	public static void main (String [] args) {
		new NonParser();
	}
	
	//Look for the "height" key in the file and return the height it points to
	public static int findHeight(File file) {
		
		int height = 0;
		String currLine = "";
		
		try {
			Scanner fileScanner = new Scanner(file);
			while (true) {
				try {
				
					currLine = fileScanner.nextLine();
					System.err.println(currLine);
					if (currLine.contains("height")) {
						String heightStr = "";
						for (int i = 0; i < currLine.length(); i++) {
							if (currLine.charAt(i) <= '9' && currLine.charAt(i) >= '0') {
								heightStr += currLine.charAt(i);
							}
						}
						if (!heightStr.isEmpty()) {
							height = Integer.parseInt(heightStr);
							//System.err.println("height: " + height);
							return height;
						}
					}
				
				}
				catch (NoSuchElementException nsee) { //no more lines; height still not found
					System.err.println("No height found");
					fileScanner.close();
					// sysScanner.close();
					System.exit(1);
					return -1;					
				}
			}		
		}
		catch (IOException ex) {
			// ex.printStackTrace();
			System.err.println("File not found.");
			System.exit(1);
			return -1;
		}
	}
	
	//Look for the "width" key in the file and return the width it points to
	public static int findWidth(File file) {
		int width = 0;
		String currLine = "";
		
		try {
			Scanner fileScanner = new Scanner(file);
			while (true) {
				try {
					currLine = fileScanner.nextLine();
					System.err.println(currLine);
					if (currLine.contains("width")) {
						String widthStr = "";
						for (int i = 0; i < currLine.length(); i++) {
							if (currLine.charAt(i) <= '9' && currLine.charAt(i) >= '0') {
								widthStr += currLine.charAt(i);
							}
						}
						if (!widthStr.isEmpty()) {
							width = Integer.parseInt(widthStr);
							// System.err.println("width: " + width);
							return width;
						}
					}
				
				}
				catch (NoSuchElementException nsee) {
					System.err.println("No width found");
					fileScanner.close();
					// sysScanner.close();
					System.exit(1);
					return -1;					
				}
			}		
		}
		catch (IOException ex) {
			//ex.printStackTrace();
			System.err.println("File not found.");
			System.exit(1);
			return -1;
		}
	}
	
	//Layout: rowClues is an ArrayList of int arrays. The int arrays contain the clues in each row;
	//the outer ArrayList represents a list of rows.
	/*For example:
	.........ARRAYLIST.........
	.                         .	
	    A	0) 1 | 2
	    R	1) 3
	    R	2) 7 | 1
	    A	3) 6 | 2 | 3
	    Y	4) 1 | 2 | 1
	    S	5) 3 | 1
	.                         .
	...........................
	
	(I might actually add what it represents on an actual nonogram later :P )
	
	*/
	public static ArrayList<int[]> findRowClues (File file) {
		ArrayList<int[]> rowClues = new ArrayList<int[]>();
		String currLine = "";
		try {
			Scanner fileScanner = new Scanner(file);
			while (true) {
				try {
					currLine = fileScanner.nextLine();
					System.err.println(currLine);
					if (currLine.contains("rows")) {
						int lineNum = 0;
						while (true) {
							try {
								lineNum++;
								currLine = fileScanner.nextLine();
								System.err.println("Clues: " + lineNum + ":" + currLine);
								if (currLine.isEmpty()) {
									break;
								}
								String[] clueStrings = currLine.split(",");
								int[] clueInts = new int[clueStrings.length];
								if (clueStrings.length == 0) {
									break;
								}
								for (int i = 0; i < clueStrings.length; i++) {
								   clueInts[i] = Integer.parseInt(clueStrings[i]);
								}
								rowClues.add(clueInts);
							}
							catch (NoSuchElementException nsee) {
								break;
							}
						}
						break;
					}
				}
				catch (NoSuchElementException nsee) {
					if (rowClues.size() == 0) {
						System.err.println("No rows found");
						fileScanner.close();
						// sysScanner.close();
						System.exit(1);
					}				
				}
			}		
		}
		catch (IOException ex) {
			// ex.printStackTrace();
			System.err.println("File not found.");
			System.exit(1);
		}
		System.err.println("rowClues returns here");
		return rowClues;
	}
	
	public static ArrayList<int[]> findColClues (File file) {
		ArrayList<int[]> colClues = new ArrayList<int[]>();
		String currLine = "";
		try {
			Scanner fileScanner = new Scanner(file);
			while (true) {
				try {
					currLine = fileScanner.nextLine();
					System.err.println(currLine);
					if (currLine.contains("columns")) {
						int lineNum = 0;
						while (true) {
							try {
								lineNum++;
								currLine = fileScanner.nextLine();
								System.err.println("Clues: " + lineNum + ":" + currLine);
								if (currLine.isEmpty()) {
									break;
								}
								String[] clueStrings = currLine.split(",");
								int[] clueInts = new int[clueStrings.length];
								if (clueStrings.length == 0) {
									break;
								}
								for (int i = 0; i < clueStrings.length; i++) {
								   clueInts[i] = Integer.parseInt(clueStrings[i]);
								}
								colClues.add(clueInts);
							}
							catch (NoSuchElementException nsee) {
								break;
							}
						}
						break;
					}
				}
				catch (NoSuchElementException nsee) {
					if (colClues.size() == 0) {
						System.err.println("No rows found");
						fileScanner.close();
						// sysScanner.close();
						System.exit(1);
					}				
				}
			}		
		}
		catch (IOException ex) {
			// ex.printStackTrace();
			System.err.println("File not found.");
			System.exit(1);
		}
		System.err.println("colClues returns here");
		return colClues;
	}
}
