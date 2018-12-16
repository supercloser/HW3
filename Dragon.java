import java.util.Random;

public class Dragon {
	
	private int x;
	private int y;
	private String team;
	private boolean alive;
	private String name;
	private int strength = 5;
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
		int nextX;
		if (team.equals("red")) {
		    nextX = this.x + 1;
		
	   
		}else {
			nextX = this.x - 1;
		}
	    
		int willForward = nextX;
		return willForward;
	}//================================================
	public int willMoveAside (String team) {
		Random rand = new Random();
		int nextY = 0;
		if (team.equals("red")) {
		    boolean nextYbool = rand.nextBoolean();
		    if(nextYbool) {
		    	nextY = this.y +1; //move left
		    }else {
		    	nextY = this.y -1; //move right
		    }
		
	   
		}else {
			 boolean nextYbool = rand.nextBoolean();
			    if(nextYbool) {
			    	nextY = this.y -1; //move left
			    }else {
			    	nextY = this.y +1; //move right
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
	public void moveAside (String team, int nextY) {
		this.y =nextY;
		
	}
	
	
	
	
	
	
	
	
	
	
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
