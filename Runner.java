public class Runner {
	public Runner (){
		Board board1 = new Board(5,7);
		board1.set(0, 1, true);
		board1.set(4,3,true);
		board1.set(2,6,true);
		board1.updateRowClues();
		board1.updateColClues();
		board1.printRowClues();
		board1.printColClues();
		Board board2 = new Board(8,3);
		board2.set(7,1,true);
		board2.set(1,0,true);
		board2.set(5,2,true);
		board2.set(6,2,true);
		board2.set(2,2,true);
		board2.set(2,0,true);
		board2.set(1,2,true);
		board2.updateRowClues();
		board2.updateColClues();
		board2.printRowClues();
		board2.printColClues();
	}

	public static void main(String[] args) {
		new Runner();
	}
}
