import java.util.Random;

public class Dragon {
	
	private int x;
	private int y;
	private String team;
	private boolean alive;
	private String name;
	private int strength =2;
	public static int nextX =0;
	public static int nextY =0;
	
	public int getStrength() {
		return strength;
	}
	public void setStrength(int strength) {
		this.strength = strength;
	}
	public Dragon(int x, int y, String team, boolean alive, String name) {
		this.x = x;
		this.y = y;
		this.team = team;
		this.alive = alive;
		this.name = name;
	}
	public int willMoveForward (String team) {
		
		if (team.equals("red")) {
		    nextX = this.x + 1;
		    fixBounds();
		
	   
		}else {
			nextX = this.x - 1;
			fixBounds();
		}
	    
		int willForward = nextX;
		return willForward;
	}//================================================
	public int willMoveAside (String team) {
		Random rand = new Random();
		
		if (team.equals("red")) {
		    boolean nextYbool = rand.nextBoolean();
		    if(nextYbool == false) {
		    	nextY = this.y +1; //move left
		    	fixBounds();
		    }else {
		    	nextY = this.y -1; //move right
		    	fixBounds();
		    }
		
	   
		}else {
			 boolean nextYbool = rand.nextBoolean();
			    if(nextYbool == false) {
			    	nextY = this.y -1; //move left
			    	fixBounds();
			    }else {
			    	nextY = this.y +1; //move right
			    	fixBounds();
			    }
		}
	    
		int willAside = nextY;
		return willAside;
	}
	public void moveForward (String team) {
		if (team.equals("red"))
		    this.x = x+1;
		else {
			this.x = x-1;
		}
	}
	public void moveAside () {
		this.y =nextY;
		
	}
	public void fixBounds () {
		if (nextX > GameOfThrone.size) {
	    	nextX = nextX - GameOfThrone.size;
	    }
	    if(nextX<0) {
	    	nextX = GameOfThrone.size + nextX;
	    }
	    if (nextY>GameOfThrone.size) {
			nextY = nextY - GameOfThrone.size;
		}if (nextY<0) {
			nextY = GameOfThrone.size + nextY;
		}
	    
	}
	
	/*public int stepForward (int x, int y, String team) {
		// print ("+stepForward")
		int status = GameOfThrone.isOccupied(x, y, team);
		switch (status) {
		case 0: 
			moveForward(team);
			return 0;
		case 1: 
			return (-1);
		case 2:
			moveForward(team);
			return (-1);
		case 3:
			return (-1);
		}
		// print ("-stepForward")
	}*/
	
	
	
	
	
	
	
	
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
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

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
