package gameofthrone;

import java.util.Scanner;

public class GameOfThrone {
	private String[][] board;
	private Dragon[] dragons;
	private Soldier[] soldiers;
	private boolean gameOver;
	private int lastSoldier = 0;
	private int lastDragon = 0;
	private static int size;
	
	public GameOfThrone(int size) {
		board = new String[size][size];
		this.setSize(size);
	}
	public void setDragons (int maxNumber) {
		dragons = new Dragon[maxNumber];
	}
	public void setSoldiers (int maxNumber) {
		soldiers = new Soldier[maxNumber];
	}
	public void createArmy(int armySize, String kind, String team) {
		Scanner scn2 = new Scanner(System.in);
		for (int x = 0; x < armySize; x++) {
			System.out.println(team+" Where do yo want to place your " + (x + 1) + " " + kind + " ?");
			System.out.println("Enter X Value:");
			int xCordinate = scn2.nextInt();
			System.out.println("Enter Y Value:");
			int yCordinate = scn2.nextInt();
			String tempName = (team +" "+ kind + " "+(x + 1));
			if (kind.equals("Sol")) {
				addSoldier(xCordinate, yCordinate, team, true, tempName);
			} else {
				addDragon(xCordinate, yCordinate, team, true, tempName);
			}
		}
		
	}


	public String[][] getBoard() {
		return board;					
	}
	public int isOccupied(int cellX, int cellY, String team) {
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
			if (d.isDirection() == false) {
				continue;
			}
			else if (d.getX() == cellX && d.getY() == cellY) {
					if (d.getTeam() == team) {
						return byTeam;
					} else {
						return byEnemyDragon;
					}
			}
		}
		for (Soldier s : soldiers) {
			if (s.isDirection() == false) {
				continue;
			}
			if (s.getX() == cellX && s.getY() == cellY) {
				if (s.getTeam() == team) {
					return byTeam;
				} else {
					return byEnemySoldier;
				}
			}
		}
		return free;

	}
	
	
	
	public void addSoldier(int x, int y, String team, boolean direction, String name ) {
		Soldier newSoldier = new Soldier(x, y, team, name, true);
		soldiers[lastSoldier] = newSoldier;
		lastSoldier++;
		board[x][y] = name;
	}

	public void addDragon(int x, int y, String team, boolean direction, String name ) {
		Dragon newDragon = new Dragon(x, y, team, name, true);
		dragons[lastDragon] = newDragon;
		lastDragon++;
		board[x][y] = name;
	}
	
	
	public void advenceStep() {
		int counter = 1;
		while (isGameOver() == false) {
	    	dragonMove("RED");
	    	updateBoard();
	    	if(isGameOver()) {
	    		break;
	    	}
		    dragonMove("BLU");
		    updateBoard();
	    	if(isGameOver()) {
	    		break;
	    	}		    
	    	soldierMove("RED");
	    	updateBoard();
	    	if(isGameOver()) {
	    		break;
	    	}	    	
	    	checkForWinner();
	    	if(isGameOver()) {
	    		break;
	    	}	    	
	    	soldierMove("BLU");
	    	System.out.println("Step "+counter);
	    	updateBoard();
	    	printStringBoard(board);
	    	checkForWinner();
	    	counter++;
		}
		System.out.println("This is the final step:");
		printStringBoard(board);
	}
	
	
	public void printBoard(int count) {
		
	}
	public void moveSoldier (Soldier s) {
		int status = isOccupied(s.canMove(s.getTeam()), s.getY(), s.getTeam());
		if (status == 0) { // free
			s.move();
			updateBoard();
		}
		if (status == 1) { // cought by team mate
			
		}
		if (status == 2) { // cought by enemy soldier
			s.move();
			killSoildierBy(s);
			s.setDirection(false);
			updateBoard();
		}
		if (status == 3) { // cought by enemy dragon
			s.setDirection(false);
			updateBoard();
		}
	}
	public void killSoildierBy (Soldier s) {
		
		for (Soldier s2 : soldiers) {
			if (s2.getX() == s.getX() && s2.getY() == s.getY() && s2.getTeam() != s.getTeam()) {
				s2.setDirection(false);
				updateBoard();
			}
		}
	}
	public void killByDrg (Dragon d) {

		for (Soldier s2 : soldiers) {
			if (s2.getX() == d.getX() && s2.getY() == d.getY() && s2.getTeam() != d.getTeam()) {
				s2.setDirection(false);
				updateBoard();
			}
		}
		for (Dragon d2 : dragons) {
			if (d2.getX() == d.getX() && d2.getY() == d.getY() && d2.getName() != d.getName()) {
				d2.setDirection(false);
				d.setDirection(false);
				updateBoard();
			}
		}
	}
	public int moveDragon (int x, int y, String team, Dragon d) {
		int status = isOccupied(x,y, team);
		if (status == 0) {
			d.move();
			updateBoard();
			return 0;
		}
		if (status == 1) {
			updateBoard();
			return 1;
		}
		if (status == 2) {
			d.move();
			killByDrg(d);
			updateBoard();
			return 1;
		}
		if (status == 3) {
			d.move();
			killByDrg(d);
			updateBoard();
			return 1;
		}
		return 2;
	}
	public void soldierMove (String team) {
		for (Soldier s : soldiers) {
			if (s.isDirection()== false || s.getTeam() != team) {
				continue;
			}else {
			moveSoldier(s);
		}
		}
	}
	public void dragonMove (String team) {
		for (Dragon d : dragons) {
			if (d.isDirection() == false || d.getTeam() != team) {
				continue;
			}else {
			int ret = moveDragon(d.canMoveForward(team), d.getY(), team, d);
			if (ret == 0) {
				ret = moveDragon(d.getX(), d.canMoveAside(team), team, d);
				if (ret == 0) {
					moveDragon(d.canMoveForward(team), d.getY(), team, d);
				}else {
					continue;
				}
			}
				
			}
		}
	}
	public void printStringBoard (String[][] someBoard) {
		for (int i = 0; i < someBoard.length; i++) {
			for (int j = 0; j < someBoard[i].length; j++) {
				if (someBoard[i][j] == null) {
					System.out.print("    *  ");
				}else {
					System.out.print(someBoard[i][j]);
				}
				
			}System.out.println("\n");
		}
	}
    public void checkForWinner () {
    	boolean tie = checkForTie();
		if (tie == false) {
	    	for (Soldier s : soldiers) {
				
				if (s.getTeam().equals("RED") && s.getX() >= size) {
					setGameOver(true);
					System.out.println("Red won! GAME OVER!");
					break;
					
				} else if (s.getTeam().equals("BLU") && s.getX() == 0) {
					setGameOver(true);
					System.out.println("Blue won! GAME OVER!");
					break;
				} else {
					setGameOver(false);
				}
			}
	    }else {
	    	setGameOver(true);
	    }
    }
    public void updateBoard () {
    	String[][]  tempBoard = new String[size][size];
    	for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board.length; c++) {
				tempBoard[r][c] = figureByCoordinate(r, c);
				board[r][c] = tempBoard[r][c];
			}
		}
    }
    public String figureByCoordinate(int row, int col) {
		for (Dragon d : dragons) {
			
				if (row == d.getX() && col == d.getY() && d.isDirection() == true) {
					return d.getName();
				}
			}
		
		for (Soldier s : soldiers) {
			
				if (row == s.getX() && col == s.getY() && s.isDirection() == true) {
					return s.getName();
				}
			}
		
		return "      *  ";

	}
    public boolean checkForTie () {
    	int soldiersCounter =0;
    	for (Soldier s : soldiers) {
			if (s.isDirection()) {
				soldiersCounter++;
			}
			
		}
    	if (soldiersCounter == 0) {
			
			System.out.println("No soldiers left! the game is OVER!");
			return true;
		}else {
		    return false;
		}
    }
	public Dragon[] getDragons() {
		return dragons;
	}

	public Soldier[] getSoldiers() {
		return soldiers;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public int getLastSoldier() {
		return lastSoldier;
	}

	public void setLastSoldier(int lastSoldier) {
		this.lastSoldier = lastSoldier;
	}

	public int getLastDragon() {
		return lastDragon;
	}

	public void setLastDragon(int lastDragon) {
		this.lastDragon = lastDragon;
	}
	public static int getSize() {
		return size;
	}
	public void setSize(int size) {
		GameOfThrone.size = size;
	}

}
