import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Interpreter {
	

	public static info[] Interpreter() {

		Scanner sysScanner = new Scanner(System.in);
		System.out.println("Enter file path");
		String path = sysScanner.nextLine();
		
		try {
			Scanner fileScanner = new Scanner(new File (path));		
		}
		catch (IOException ex) {
			System.out.println("File not found.");
			sysScanner.close();
			System.exit(1);
		}
			
		split

		return new info[] {3,2};


	}
	
	public static void main (String [] args) {
		//new Interpreter();
	}
	
	public static void findRows() {
	}
}
