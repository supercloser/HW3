package gameofthrone;

import java.util.Random;

public class Dragon {
	private int x;
	private int y;
	private String team;
	private String name;
	private boolean direction;
	private int nextX;
	private int nextY;
	//========================= CONSTRUCTOR =============================
	public Dragon(int x, int y, String team, String name, boolean direction) {
		super();
		this.x = x;
		this.y = y;
		this.team = team;
		this.name = name;
		this.direction = direction;
	}
	//===================================================================
	public int canMoveForward (String team) {
		if (getTeam().equals("RED")) {
			int currentX = getX();
			nextX = currentX +1;
			fixBounds();
			return nextX;
		}else {
			int currentX = getX();
			nextX = currentX -1;
			fixBounds();
			return nextX;
		}
	}
	public int canMoveAside (String team) {
		Random rand = new Random();
		// 1 - means turn left, 0 - means turn right
		if (getTeam().equals("RED")) {
			int getSide = rand.nextInt(2);
			if (getSide == 1) {
				int currentY = getY();
				nextY = currentY +1; // red's left
			}else {
				int currentY = getY();
				nextY = currentY -1; // red's right
				fixBounds();
			} 
			return nextY;
		}else {
			int getSide = rand.nextInt(2);
			if (getSide == 1) {
				int currentY = getY();
				nextY = currentY -1; //blue's left
			}else {
				int currentY = getY();
				nextY = currentY +1; //blue's right
				fixBounds();
		}
	}
		return nextY;
		
	}
	public void fixBounds () { // makes sure the dragon can jump to the other sides of the board
		if (nextX > GameOfThrone.getSize()) {
	    	nextX = nextX - GameOfThrone.getSize();
	    }
	    if(nextX<0) {
	    	nextX = GameOfThrone.getSize() + nextX;
	    }
	    if (nextY>GameOfThrone.getSize()) {
			nextY = nextY - GameOfThrone.getSize();
		}if (nextY<0) {
			nextY = GameOfThrone.getSize() + nextY;
		}
	    
	}
	public void move () {
		setX(nextX);
		setY(nextY);
	}
	
	
	
	
	
	
	
	
	
	
	//===================================================================
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isDirection() {
		return direction;
	}
	public void setDirection(boolean direction) {
		this.direction = direction;
	}

}
