import java.util.Scanner;

public class GameOfThrone {
	public static int size;
	public static String[][] board;
	public static Dragon[] dragons;
	public static Soldier[] soldiers;
	private static boolean gameOver = false;
	public static int lastSoldier = 0;
	public static int lastDragon = 0;

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.println("Please choose the board size:");
		size = scn.nextInt();
		String[][] firstBoard = gameOfThrone(size);
		printBoard(firstBoard);
		board = new String[size][size];
		dragons = new Dragon[size * 4];
		soldiers = new Soldier[size * 4];
		// CREATING ARMY BULLSHIT STARTS HERE
		System.out.println("This is your board, Lets fill it with soldiers and dragons!");
		System.out.println("Red, please choose the number of Dragons you want:");
		int redDragons = scn.nextInt();
		createArmy(redDragons, "dragon", "red");
		System.out.println("Red, please choose the number of Soldiers you want:");
		int redSoildiers = scn.nextInt();
		createArmy(redSoildiers, "soldier", "red");
		System.out.println("Blue it's your turn, choose the number of Dragons you want:");
		int blueDragons = scn.nextInt();
		createArmy(blueDragons, "dragon", "blue");
		System.out.println("Blue, please choose the number of Soldiers you want:");
		int blueSoildiers = scn.nextInt();
		createArmy(blueSoildiers, "soldier", "blue");
		System.out.println("PERFECT, this is your new board:");
		printBoard(board);
		// ARMY CREATION ENDS HERE^^^^
		advenceStep();
		// need to create a method that takes the objects and prints a board of them
		// (the problem with the current one is
		// that it works with strings, i need it to work with objects (a bit more
		// difficult but we will get over it ;)
	}

	public static String[][] gameOfThrone(int size) {
		String[][] firstBoard = new String[size][size];
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				firstBoard[x][y] = "     *     ";
			}
		}
		return firstBoard;

	}

	public String[][] getBoard(String[][] oldBoard) { // copy of the board given and returns new one
		String[][] newBoard = new String[oldBoard.length][oldBoard.length];
		for (int x = 0; x < oldBoard.length; x++) {
			for (int y = 0; y < oldBoard.length; y++) {
				newBoard[x][y] = oldBoard[x][y];
			}
		}
		return newBoard;
	}

	public static String figureByCoordinate(int row, int col) {
		for (Dragon d : dragons) {
			if (d != null) {
				if (row == d.getX() && col == d.getY()) {
					return d.getName();
				}
			}
		}
		for (Soldier s : soldiers) {
			if (s != null) {
				if (row == s.getX() && col == s.getY()) {
					return s.getName();
				}
			}
		}
		return "      *      ";

	}
	
	public static int stepForwardDragon (int x, int y, String team,Dragon d) {
		
		int status = isOccupied(x, y, team);
		switch (status) {
		case 0: 
			d.moveForward(team);
			return 0;
		case 1: 
			return (-1);
		case 2:
			d.moveForward(team);
			return (-1);
		case 3:
			int killByDragon = dragonsArrayIndex(d);
			dragons[killByDragon] = null;
			killEnemyDragon(x, y, team);
			return (-1);
		}
		return 0;
	}
	public static void stepSoldier  (int x, int y, String team, Soldier s) {
		int status = isOccupied(x, y, team);
		switch (status) {
		case 0: 
			s.move();
			
		case 1: 
			s.dontMove();
		case 2:
		    int killBySoldier = soldiersArrayIndex(s);
		    soldiers[killBySoldier] = null;
		    killEnemySoldier(x, y, team);
			
		case 3:
			int killByDragon = soldiersArrayIndex(s);
			soldiers[killByDragon] = null;

			
		}
		
	}
	public static int stepAsideDragon (int x, int y, String team, Dragon d) {
		int getSide = d.willMoveAside(team);
		int ret = stepForwardDragon(x, getSide, team, d);
		return ret;
	}
	public static void soldierMove (String team) {
		for (Soldier s : soldiers) {
			if (s == null) {
				continue;
			}
			stepSoldier(s.whereToMove(team), s.getY(), team, s);
		}
	}
	public static void dragonMove (String team) {
		for (Dragon d : dragons) {
			if (d == null) {
				continue;
			}
			int ret = stepForwardDragon(d.willMoveForward(team), d.getY(), team, d);
			if (ret == 0) {
				ret = stepAsideDragon(d.getX(), d.getY(), team, d);
				if (ret == 0) {
					stepForwardDragon(d.willMoveForward(team), d.getY(), team, d);
				}
			}
		}
	}
	public static int soldiersArrayIndex (Soldier s) {
		int indexCounter =-1;
		for (Soldier s2 : soldiers) {
			if (s2 != null && s2.getName() == s.getName()) {
				indexCounter++;
				return indexCounter;
			}else {
				indexCounter++;
			}
		}
		return indexCounter;
	}
	public static int dragonsArrayIndex (Dragon d) {

		int indexCounter =-1;
		for (Dragon d2 : dragons) {
			if (d2 != null && d2.getName() == d.getName() ) {
				indexCounter++;
				return indexCounter;
			}else {
				indexCounter++;
			}
		}
		return indexCounter;
	}
    
	public static void killEnemySoldier (int x, int y, String team) {
		for (Soldier s2: soldiers) {
			
			if (s2!= null && s2.getX() == x && s2.getY() == y && s2.getTeam() != team) {
				int kill = soldiersArrayIndex(s2);
				soldiers[kill] = null;
			}
		}
	}
	public static void killEnemyDragon (int x, int y, String team) {
		for (Dragon d2: dragons) {
			
			if (d2 != null && d2.getX() == x && d2.getY() == y && d2.getTeam() != team) {
				int kill = dragonsArrayIndex(d2);
				soldiers[kill] = null;
			}
		}
	}
	public static void printObjectBoard() {

		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				System.out.print(figureByCoordinate(r, c));
			}
			System.out.println("\n");

		}
	}

	public static void addSoldier(int x, int y, String team, boolean alive, String name) {// adds a soldier
		Soldier soldier = new Soldier(x, y, team, alive, name);
		soldiers[lastSoldier] = soldier;
		lastSoldier++;
		board[x][y] = name;
	}

	public static void addDragon(int x, int y, String team, boolean alive, String name) {// adds a dragon
		Dragon newDragon = new Dragon(x, y, team, alive, name);
		dragons[lastDragon] = newDragon;
		lastDragon++;
		board[x][y] = name;
	}

	public static void advenceStep() { // first red dragons, then blue dragons, then red soldiers, then blue soldiers
		int counter = 0;
		while (isGameOver() == false) {

			checkForWinner();
			dragonMove("red");
			checkForWinner();
			dragonMove("blue");
			checkForWinner();
			soldierMove("red");
			checkForWinner();
			soldierMove("blue");
			checkForWinner();
			counter++;
			System.out.println("Step " + counter);
			printObjectBoard();
		}
	}

	public static void printBoard(String[][] someBoard) { // prints the board to the console
		for (int x = 0; x < someBoard.length; x++) {
			for (int y = 0; y < someBoard.length; y++) {
				if (someBoard[x][y] == null) {
					someBoard[x][y] = "     *     ";
				}
				System.out.print(someBoard[x][y]);
			}
			System.out.println("\n");
		}
	}

	public static void createArmy(int armySize, String kind, String team) {
		Scanner scn2 = new Scanner(System.in);
		for (int x = 0; x < armySize; x++) {
			System.out.println("Where do yo want to place your " + (x + 1) + " " + kind + " ?");
			System.out.println("Enter X Value:");
			int xCordinate = scn2.nextInt();
			System.out.println("Enter Y Value:");
			int yCordinate = scn2.nextInt();
			if (kind == "soldier") {
				String sName = (team + "_Sol_" + (x + 1) + " ");
				addSoldier(xCordinate, yCordinate, team, true, sName);
			} else if (kind == "dragon") {
				String dName = (team + "_Drg_" + (x + 1) + " ");
				addDragon(xCordinate, yCordinate, team, true, dName);
			}
		}
	}

	public static int isOccupied(int cellX, int cellY, String team) {
		/*
		 * The method takes x,y as an array of integers, the x will be the [0] spot, and
		 * the y will be the [1] spot Then it runs a loop to check if there is another
		 * dragon or soldier in this coordinates. Finally if the coordinate is occupied
		 * by a team mate the method returns "1", if it occupied by enemy dragon, it
		 * returns "3" and if occupied by enemy soldier, it returns "2", if the spot is
		 * free it returns "0"
		 */
		int free = 0;
		int byTeam = 1;
		int byEnemySoldier = 2;
		int byEnemyDragon = 3;
		// --------------------------
		for (Dragon d : dragons) {
			if (d == null || d.isAlive() == false) {
				return free;
			}
			if (d.getX() == cellX && d.getY() == cellY) {
				if (d.getTeam().equals(team)) {
					return byTeam;
				} else {
					return byEnemyDragon;
				}
			}
		}
		for (Soldier s : soldiers) {
			if (s == null || s.isAlive() == false) {
				return free;
			}
			if (s.getX() == cellX && s.getY() == cellY) {
				if (s.getTeam().equals(team)) {
					return byTeam;
				} else {
					return byEnemySoldier;
				}
			}
		}
		return free;

	}

	public static boolean isGameOver() {
		return gameOver;
	}

	public static void setGameOver(boolean gameOver) {
		GameOfThrone.gameOver = gameOver;
	}

	public static void checkForWinner() {
		boolean tie = checkForTied();
		if (tie == false) {
			
		
		for (Soldier s : soldiers) {
			if (s == null) {
				continue;
			} else if (s.getTeam().equals("red") && s.getX() >= size) {
				System.out.println("Red won! GAME OVER!");
				setGameOver(true);
			} else if (s.getTeam().equals("blue") && s.getX() == 0) {
				System.out.println("Blue won! GAME OVER!");
				setGameOver(true);
			} else {
				setGameOver(false);
			}
		}
		}
		
	}
	public static boolean checkForTied() {
		int counter =0;
		for (Soldier s : soldiers) {
			if (s != null) {
				counter ++;
			}
		}
		if (counter ==0) {
			
			setGameOver(true);
			System.out.println("It is a TIE because no soldiers left alive!");
			return true;
		}
		return false;
	}

}