package gameofthrone;

import java.util.Scanner;

public class HW3_DavidZherdenovsky {
 
	public static void main(String[] args) {
		// The game made by David Zherdenovsky
		// I used instead of the word "blue" - "BLU" to make the board symmetric!!! 
		// "Drg" means "Dragon" and "Sol" means "Soldier"!!!
		// The movement rules wern't well explained so:
		// The movement goes like ---> 1. Red Dragons
		//                             2. Blue Dragons
		//                             3. Red Soldiers
		//							   4. Blue Soldiers
		Scanner scan = new Scanner(System.in);
		System.out.println("Please choose the board size X * X:");
		int size = scan.nextInt();
		GameOfThrone game = new GameOfThrone(size);
		System.out.println("Red, please choose the number of Dragons you want:");
		int redDragons = scan.nextInt();
		System.out.println("Red, please choose the number of Soldiers you want:");
		int redSoildiers = scan.nextInt();
		System.out.println("Blue it's your turn, choose the number of Dragons you want:");
		int blueDragons = scan.nextInt();
		System.out.println("Blue, please choose the number of Soldiers you want:");
		int blueSoildiers = scan.nextInt();
		//Creating the army
		game.setDragons(redDragons+blueDragons);
		game.setSoldiers(redSoildiers+blueSoildiers);
		game.createArmy(redDragons, "Drg", "RED");
		game.createArmy(redSoildiers, "Sol", "RED");
		game.createArmy(blueDragons, "Drg", "BLU");
		game.createArmy(blueSoildiers, "Sol", "BLU");
		System.out.println("PERFECT, this is your new board:");
		scan.close();
		String[][] firstBoard = game.getBoard();
		game.printStringBoard(firstBoard);
		game.advenceStep();

	}
	

}
