import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.ArrayList;

public class NonParser {
	
	static Scanner sysScanner;
	
	public static Board NonParser(File file) {

		sysScanner = new Scanner(System.in);
		//System.out.println("Enter file path");
		//String path = sysScanner.nextLine();
		Board info = new Board (findHeight(file), findWidth(file));
		info.checkRowsSolution(findRowClues(file));
		//int[] info = {0, 0};
		//info[0] = findHeight(file);
		//info[1] = findWidth(file);
		sysScanner.close();
		return info;


	}
	
	public static void main (String [] args) {
		new NonParser();
	}
	
	//Look for the "height" key in the file and return the height it points to
	public static int findHeight(File file) {
		
		//int lines = 0;
		int height = 0;
		String currLine = "";
		
		try {
			Scanner fileScanner = new Scanner(file);
			while (true) {
				try {
				
					currLine = fileScanner.nextLine();
					System.out.println(currLine);
					if (currLine.contains("height")) {
						String heightStr = "";
						for (int i = 0; i < currLine.length(); i++) {
							if (currLine.charAt(i) <= '9' && currLine.charAt(i) >= '0') {
								heightStr += currLine.charAt(i);
							}
						}
						if (!heightStr.isEmpty()) {
							height = Integer.parseInt(heightStr);
							//System.out.println("height: " + height);
							return height;
						}
					}
					//lines++;
					//System.out.println(lines);
					//try{Thread.sleep(100);}catch(InterruptedException e){System.out.println("e");};
				
				}
				catch (NoSuchElementException nsee) { //no more lines; height still not found
					System.out.println("No height found");
					//fileScanner.close();
					sysScanner.close();
					System.exit(1);
					return -1;					
				}
			}		
		}
		catch (IOException ex) {
			//ex.printStackTrace();
			System.out.println("File not found.");
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
					System.out.println(currLine);
					if (currLine.contains("width")) {
						String widthStr = "";
						for (int i = 0; i < currLine.length(); i++) {
							if (currLine.charAt(i) <= '9' && currLine.charAt(i) >= '0') {
								widthStr += currLine.charAt(i);
							}
						}
						if (!widthStr.isEmpty()) {
							width = Integer.parseInt(widthStr);
							//System.out.println("width: " + width);
							return width;
						}
					}
				
				}
				catch (NoSuchElementException nsee) { //no more lines; width still not found
					System.out.println("No width found");
					fileScanner.close();
					sysScanner.close();
					System.exit(1);
					return -1;					
				}
			}		
		}
		catch (IOException ex) {
			//ex.printStackTrace();
			System.out.println("File not found.");
			System.exit(1);
			return -1;
		}
	}
	
	//Layout: clues is an ArrayList of ArrayLists of Integers. The inner ArrayLists are lists of clues in each row;
	//the outer ArrayList represents a list of rows.
	public static ArrayList<int[]> findRowClues (File file) {
		ArrayList<int[]> rowClues = new ArrayList<int[]>();
		int width = 0;
		String currLine = "";
		try {
			Scanner fileScanner = new Scanner(file);
			while (true) {
				try {
					currLine = fileScanner.nextLine();
					System.out.println(currLine);
					//look through next lines; add their info if they're clues and stop when the row's not about clues
					if (currLine.contains("rows")) {
						int lineNum = 0;
						while (true) {
							try {
								lineNum++;
								currLine = fileScanner.nextLine();
								System.out.println("Clues: " + lineNum + ":" + currLine);
								if (currLine.isEmpty()) {break;}
								String[] clueStrings = currLine.split(",");
								int[] clueInts = new int[clueStrings.length];
								if (clueStrings.length == 0) {break;}
								for (int i = 0; i < clueStrings.length; i++) {
								   clueInts[i] = Integer.parseInt(clueStrings[i]);
								}
								rowClues.add(clueInts);
							}
							catch (NoSuchElementException nsee) { //reached end of clues and there's nothing left
								break;
							}
						}
						break;
					}
				}
				catch (NoSuchElementException nsee) { //no more lines; rows still not found
					if (rowClues.size() == 0) {
						System.out.println("No rows found");
						fileScanner.close();
						sysScanner.close();
						System.exit(1);
					}				
				}
			}		
		}
		catch (IOException ex) {
			//ex.printStackTrace();
			System.out.println("File not found.");
			System.exit(1);
		}
		System.out.println("rowClues returns here");
		return rowClues;
	}
}
