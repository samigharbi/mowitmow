package fr.sg.mowitmow.bean;

public class Position {
	
	private int x;
	private int y;
	private Direction direction;

	public Position(final int x, final int y, final Direction direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	public void turnRightDirection() {
		direction = direction.getRightDirection();
	}
	
	public void turnLeftDirection() {
		direction = direction.getLeftDirection();
	}
	
	public void aheadIfPossible(final GardenLimit limit) {
		switch(direction) {
			case North :
				if (y < limit.getY()) { y += 1; }
				break;
			case South :
				if (y > 0) { y -= 1; }
				break;
			case East :
				if (x < limit.getX()) { x += 1; }
				break;
			case West :
				if (x > 0) { x -= 1; }
				break;
		}		
	}
	
	public boolean isValidPosition(final GardenLimit limit) {
		return x >=0 && x <= limit.getX() && y >= 0 && y <= limit.getY();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Direction getDirection() {
		return direction;
	}

	@Override
	public String toString() {
		return x + " " + y + " " + direction;
	}
	
	public Position clone() {
		return new Position(this.x, this.y, this.direction);
	}
}