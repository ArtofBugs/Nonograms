/*
   Finds "goal" key in given .non file; prints representation of puzzle solution
   to standard error. Asterisks (*) represent filled squares; periods (.)
   represent unfilled squares.
   ArtofBugs | August 2021
*/

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class GoalParser {
    
    static String path;
    
    public static void main(String[] args) {
        path = args[0];
        
        try {
           File file = new File(path);
           Scanner scanner = new Scanner(file);
           int h = NonParser.findHeight(file);
           int w = NonParser.findWidth(file);
           scanner.close();
           String goalStr = findGoal(file);
           int n = goalStr.length();
           String goal = "";
           for (int i = 0; i < n; i++) {
              if (goalStr.charAt(i) == '0') {
                  goal += ".";
              }
              else {
                  goal += "*";
              }
           }
           
           System.err.println("~~~~~~~~~~~~~~");
           System.err.println("GOAL");
           System.err.println("~~~~~~~~~~~~~~");
           for (int r = 0; r < h; r++) {
              for (int c = 0; c < w; c++) {
                 System.err.print(goal.charAt(r*w+c) + " ");
              }
              System.err.println("");
           }
           System.err.println("~~~~~~~~~~~~~~");
           
        
        } catch (IOException e) {
           System.err.println("File not found.");
           System.exit(1);
        }
        
    }
    
    public static String findGoal(File file) {
		
		String currLine = "";
		
		try {
			Scanner scanner = new Scanner(file);
			while (true) {
				try {
					currLine = scanner.nextLine();
					if (currLine.contains("goal")) {
					    String goalStr = "";
						for (int i = 0; i < currLine.length(); i++) {
							if (currLine.charAt(i) <= '9' && currLine.charAt(i) >= '0') {
								goalStr += currLine.charAt(i);
							}
						}
						if (!goalStr.isEmpty()) {
							return goalStr;
						}
					}
				
				}
				catch (NoSuchElementException nsee) {
					System.err.println("No goal found");
					scanner.close();
					System.exit(1);
					return "";					
				}
			}		
		}
		catch (IOException ex) {
			System.err.println("File not found.");
			System.exit(1);
			return "";
		}
	}
}
