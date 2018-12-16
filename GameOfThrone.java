import java.util.Scanner;

public class GameOfThrone {
	public static int size;
	public static String[][] board;
	public static Dragon[] dragons;
	public static Soldier[] soldiers;
	private static boolean gameOver = false;
	public static int lastSoldier = 0;
	public static int lastDragon = 0;
	
	public static void main (String[] args) {
		Scanner scn = new Scanner (System.in);
		System.out.println("Please choose the board size:");
		size = scn.nextInt();
		String[][] firstBoard = GameOfThrone(size);
		printBoard(firstBoard);
		board = new String [size][size];
		dragons = new Dragon [size*4];
		soldiers = new Soldier[size*4];
		//CREATING ARMY BULLSHIT STARTS HERE
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
		//ARMY CREATION ENDS HERE^^^^
		advenceStep();
		
	}
	public static String[][] GameOfThrone(int size) {
		String[][] firstBoard = new String[size][size];
		for (int x=0; x<size; x++) {
			for(int y=0; y<size; y++) {
				firstBoard[x][y] = "     *     ";
			}
		}
		return firstBoard;
		
	}
	public static void copy (String[][] original, String[][] paste){
		for(int x=0; x<original.length;x++) {
			for(int y=0; y<original.length;y++) {
				paste[x][y] = original[x][y];
			}
		}
		
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
		Dragon newDragon = new Dragon(x,y,team,alive,name);
		dragons[lastDragon] = newDragon;
		lastDragon++;
		board[x][y] = name;
	}
	
	public static void moveDragon(String team) {
		for (Dragon d : dragons) {
			if (d.getTeam().equals(team)) {
			int status = isOccupied(d.getX(), d.willMoveForward(team), dragons, soldiers, team);
			if (status == 0) {
				d.moveForward(team);
				int nextY = d.willMoveAside(team);
				if (nextY>size) {
					nextY = nextY - size;
				}if (nextY<0) {
					nextY = size + nextY;
				}
				 status = isOccupied(d.getX(), nextY, dragons, soldiers, team);
				 if (status == 0) {
					 d.moveAside(team, nextY);
					 status = isOccupied(d.getX(), d.willMoveForward(team), dragons, soldiers, team);
					 if (status == 0) {
						 d.moveForward(team);
						 continue;
					 }else if(status == 1) {
						 continue;
					 }else if(status == 2) {
						 d.moveForward(team);
						 for (Soldier s : soldiers) {
							 if (s.getX() == d.getX() && s.getY() == d.getY()) {
								 s.setAlive(false);
							 }
						 }
					 }else if(status == 3) {
						 for (Dragon d2 : dragons) {
							 if(d2.getX() == d.getX() && d2.getY() == d.getY()) {
								 d2.setAlive(false);
								 d.setAlive(false);
							 }
						 }
					 }
				 }else if(status == 1) {
					 continue;
				 }else if (status == 2) {
					 d.moveAside(team, nextY);
					 for (Soldier s : soldiers) {
						 if (s.getX() == d.getX() && s.getY() == d.getY()) {
							 s.setAlive(false);
						 }
					 }
				 }else if(status == 3) {
					 for (Dragon d2 : dragons) {
						 if(d2.getX() == d.getX() && d2.getY() == d.getY()) {
							 d2.setAlive(false);
							 d.setAlive(false);
						 }
					 }
				 }
			}
			if (status == 1) {
				continue;
			}
			if (status == 2) {
				 d.moveForward(team);
				 for (Soldier s : soldiers) {
					 if (s.getX() == d.getX() && s.getY() == d.getY()) {
						 s.setAlive(false);
					 }
				 }
			}
			if (status == 3) {
				for (Dragon d2 : dragons) {
					 if(d2.getX() == d.getX() && d2.getY() == d.getY()) {
						 d2.setAlive(false);
						 d.setAlive(false);
					 }
				 }
			}
		}
	}
	}
	public static void moveSoldier (String team) {
		for (Soldier s : soldiers) {
			if (s.getTeam().equals(team)) {
				int status = isOccupied(s.whereToMove(team), s.getY(), dragons, soldiers, team);
				if (status == 0) {
					s.move();
				}else if (status == 1) {
					continue;
				}else if (status == 2) {
					for (Soldier s2 : soldiers) {
						 if (s2.getX() == s.getX() && s2.getY() == s.getY()) {
							 s.setAlive(false);
							 s2.setAlive(false);
						 }
					}
				}else if (status == 3) {
					s.setAlive(false);
				}
			}
		}
	}
	public static void advenceStep() { // first red dragons, then blue dragons, then red soldiers, then blue soldiers
		int counter =0;
		while (gameOver = false) {
			
			checkForWinner();
			moveDragon("red");
			moveDragon("blue");
			moveSoldier("red");
			moveSoldier("blue");
			counter++;
			System.out.println("Step "+counter);
			printBoard(board);
	}
	}
	
	
	
	public static void printBoard(String[][] someBoard) { // prints the board to the console
		for(int x=0; x<someBoard.length; x++) {
			for(int y=0; y<someBoard.length; y++) {
				if (someBoard[x][y] == null) {
					someBoard[x][y] = "     *     ";
				}
				System.out.print(someBoard[x][y]);
			}System.out.println("\n");
		}
	}
	public static void createArmy (int armySize, String kind, String team) {
		Scanner scn2 = new Scanner(System.in);
		for(int x=0; x<armySize; x++) {
			System.out.println("Where do yo want to place your " + (x+1) + " " + kind + " ?");
			System.out.println("Enter X Value:");
			int xCordinate = scn2.nextInt();
			System.out.println("Enter Y Value:");
			int yCordinate = scn2.nextInt();
			if(kind == "soldier") {
				String sName = (team+"_Sol_"+(x+1)+" ");
				addSoldier(xCordinate, yCordinate, team, true, sName);
			}else if (kind == "dragon") {
				String dName = (team+"_Drg_"+(x+1)+" ");
				addDragon(xCordinate, yCordinate, team, true, dName);
			}
		}
	}
	
	public static int isOccupied (int cellX, int cellY, Dragon[] dragons, Soldier[] soldiers, String team) {
		/*The method takes x,y as an array of integers, the x will be the [0] spot, and the y will be the [1] spot
		Then it runs a loop to check if there is another dragon or soldier in this coordinates.
		Finally if the coordinate is occupied by a team mate the method returns "1", 
		if it occupied by enemy dragon, it returns "3"
		and if occupied by enemy soldier, it returns "2", if the spot is free it returns "0"*/
		int free = 0;
		int byTeam = 1;
		int byEnemySoldier = 2;
		int byEnemyDragon = 3;
		//--------------------------
		for (Dragon d: dragons) {
			if (d.getX() == cellX && d.getY() == cellY) {
				if (d.getTeam().equals(team)) {
					return byTeam;
				}else {
					return byEnemyDragon;
				}
			}
		}
		for (Soldier s: soldiers) {
			if (s.getX() == cellX && s.getY() == cellY) {
				if (s.getTeam().equals(team)) {
					return byTeam;
				}else {
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
	public static void checkForWinner () {
		for (Soldier s: soldiers) {
			if (s.getTeam().equals("red") && s.getX() == (size-1)) {
				System.out.println("Red won! GAME OVER!");
				setGameOver(true);
			}else if(s.getTeam().equals("blue") && s.getX() == 0) {
				System.out.println("Blue won! GAME OVER!");
				setGameOver(true);
			}else {
				setGameOver(false);
			}
		}
	}	
	}