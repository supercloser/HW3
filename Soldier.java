
public class Soldier {
	
	private int x;
	private int y;
	private String team;
	private boolean alive;
	private String name;
	private int strength = 1;
	public int getStrength() {
		return strength;
	}
	public void setStrength(int strength) {
		this.strength = strength;
	}
	public Soldier(int x, int y, String team, boolean alive, String name) {
		this.x = x;
		this.y = y;
		this.team = team;
		this.alive = alive;
		this.name = name;
		
	}
	//=========== WhereToMove========================
	public int whereToMove (String team) {
		int nextX;
		if (team.equals("red")) {
		    nextX = this.x + 1;
		
	   
		}else {
			nextX = this.x - 1;
		}
	    
		
		return nextX;
	}
	//^^^^^^^^^^^^ End of WhereToMove ^^^^^^^^^^^^^^^^
	
	//============ Move ===============================
	public void move () {
		if (team.equals("red"))
		    this.x = x+1;
		else {
			this.x = x-1;
		}
	}
	//^^^^^^^^^^^^^^^^^^ End of Move ^^^^^^^^^^^^^^^^^^^^^^
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
	//==========================================================================================================
	

}
