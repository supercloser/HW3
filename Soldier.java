package gameofthrone;

public class Soldier {
	private int x;
	private int y;
	private String team;
	private String name;
	private boolean direction;
	//=======================================================================
	public int canMove (String team) {
		if (getTeam().equals("RED")) {
			int currentX = getX();
			int nextX = currentX +1;
			return nextX;
		}else {
			int currentX = getX();
			int nextX = currentX -1;
			return nextX;
		}
	}
	public void move () {
		if (getTeam().equals("RED")) {
			int currentX = getX();
			int nextX = currentX +1;
			setX(nextX);
		}else {
			int currentX = getX();
			int nextX = currentX -1;
			setX(nextX);
		}
	}
	
	
	
	
	
	
	
	
	//====================== CONSTRUCTOR ===================================
	public Soldier(int x, int y, String team, String name, boolean direction) {
		super();
		this.x = x;
		this.y = y;
		this.team = team;
		this.name = name;
		this.direction = direction;
	}
	//=======================================================================
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
