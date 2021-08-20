/*
    Timer that prints time between start and stop to standard output.
    ArtofBugs | August 2021
*/

public class Timer {
	
	long startTime;	
	
	public Timer() {
		
	}
	public static void main(String[] args) {
		new Timer();
	}
	
	public void start() {
		startTime = System.currentTimeMillis();
	}
	
	public void stop() {
		System.out.println("Time taken: " +
			(System.currentTimeMillis() - startTime) + " ms");
	}
}
