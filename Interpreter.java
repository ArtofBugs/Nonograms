import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class Interpreter {
	
	static Scanner sysScanner;
	
	public static int[] Interpreter() {

		sysScanner = new Scanner(System.in);
		System.out.println("Enter file path");
		String path = sysScanner.nextLine();
		int[] info = {0, 0};
		info[0] = findHeight(path);
		info[1] = findWidth(path);
		sysScanner.close();
		return info;


	}
	
	public static void main (String [] args) {
		new Interpreter();
	}
	
	public static int findHeight(String path) {
		
		//int lines = 0;
		int height = 0;
		String currLine = "";
		
		try {
			Scanner fileScanner = new Scanner(new File (path));
			while (true) {
				try {
					currLine = fileScanner.nextLine();
					//System.out.println(currLine);
					if (currLine.contains("height")) {
						String heightStr = "";
						for (int i = 0; i < currLine.length(); i++) {
							if (currLine.charAt(i) <= '9' && currLine.charAt(i) >= '0') {
								heightStr += currLine.charAt(i);
							}
						}
						if (!heightStr.isEmpty()) {
							height = Integer.parseInt(heightStr);
							System.out.println("height: " + height);
							return height;
						}
					}
					//lines++;
					//System.out.println(lines);
					//try{Thread.sleep(100);}catch(InterruptedException e){System.out.println("e");};
				
				}
				catch (NoSuchElementException nsee) { //no more lines; height still not found
					System.out.println("No height found");
					fileScanner.close();
					sysScanner.close();
					System.exit(1);
					return -1;					
				}
			}		
		}
		catch (IOException ex) {
			ex.printStackTrace();
			System.out.println("File not found.");
			System.exit(1);
			return -1;
		}
	}
	
	public static int findWidth(String path) {
		
		//int lines = 0;
		int width = 0;
		String currLine = "";
		
		try {
			Scanner fileScanner = new Scanner(new File (path));
			while (true) {
				try {
					currLine = fileScanner.nextLine();
					//System.out.println(currLine);
					if (currLine.contains("width")) {
						String widthStr = "";
						for (int i = 0; i < currLine.length(); i++) {
							if (currLine.charAt(i) <= '9' && currLine.charAt(i) >= '0') {
								widthStr += currLine.charAt(i);
							}
						}
						if (!widthStr.isEmpty()) {
							width = Integer.parseInt(widthStr);
							System.out.println("width: " + width);
							return width;
						}
					}
					//lines++;
					//System.out.println(lines);
					//try{Thread.sleep(100);}catch(InterruptedException e){System.out.println("e");};
				
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
			ex.printStackTrace();
			System.out.println("File not found.");
			System.exit(1);
			return -1;
		}
	}
}
