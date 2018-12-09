import java.util.Scanner;

public class GameOfThrone {
	
	private static String[][] board;
	private static Dragon[] dragons;
	private static Soldier[] soldiers;
	private boolean gameOver;
	private static int lastSoldier = 0;
	private static int lastDragon = 0;
	
	public static void main (String[] args) {
		Scanner scn = new Scanner (System.in);
		System.out.println("Please choose the board size:");
		int size = scn.nextInt();
		String[][] firstBoard = GameOfThrone(size);
		printBoard(firstBoard);
		System.out.println("This is your board, Lets fill it with soldiers and dragons!");
		System.out.println("Red, please choose the number of Dragons you want:");
		int redDragons = scn.nextInt();
		createArmy(redDragons, "dragon", "red");
		System.out.println("Red, please choose the number of Soldiers you want:");
		int redSoildiers = scn.nextInt();
		createArmy(redSoildiers, "soldier", "red");
	}
	public static String[][] GameOfThrone(int size) {
		String[][] firstBoard = new String[size][size];
		for (int x=0; x<size; x++) {
			for(int y=0; y<size; y++) {
				firstBoard[x][y] = "____ ";
			}
		}
		return firstBoard;
		
	}


	public String[][] getBoard(String[][] oldBoard) { // copy of the board given and returns new one
		String[][] newBoard = new String[oldBoard.length][oldBoard.length];
		for(int x=0; x<oldBoard.length;x++) {
			for(int y=0; y<oldBoard.length;y++) {
				newBoard[x][y] = oldBoard[x][y];
			}
		}
		return newBoard;					
	}
	
	
	
	public static void addSoldier(int x, int y, String team, boolean alive, String name) {// adds a soldier
		Soldier soldier = new Soldier(x,y,team,alive,name);
		soldiers[lastSoldier] = soldier;
		lastSoldier++;
		board[x][y] = name;
	}

	public static void addDragon(int x, int y, String team, boolean alive, String name ) {// adds a dragon
		Dragon dragon = new Dragon(x,y,team,alive,name);
		dragons[lastDragon] = dragon;
		lastDragon++;
		board[x][y] = name;
	}
	
	
	public void advenceStep() { // Supposed to be some loop of movement
		/*
		 * add code here
		 */
	}
	
	
	public static void printBoard(String[][] someBoard) { // prints the board to the console
		for(int x=0; x<someBoard.length; x++) {
			for(int y=0; y<someBoard.length; y++) {
				System.out.print(someBoard[x][y]);
			}System.out.println("\n");
		}
	}
	public static void createArmy (int armySize, String kind, String team) {
		Scanner scn2 = new Scanner(System.in);
		for(int x=0; x<armySize; x++) {
			System.out.println("Where do yo want to place your " + x + " " + kind + " ?");
			System.out.println("Enter X Value:");
			int xCordinate = scn2.nextInt();
			System.out.println("Enter Y Value:");
			int yCordinate = scn2.nextInt();
			if(kind == "soldier") {
				addSoldier(xCordinate, yCordinate, team, true, team+"_Sol_"+x);
			}else if (kind == "dragon") {
				addDragon(xCordinate, yCordinate, team, true, team+"_Drg_"+x);
			}
		}
	}

}