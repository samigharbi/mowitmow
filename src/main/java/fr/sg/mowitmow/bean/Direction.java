package fr.sg.mowitmow.bean;

public enum Direction {
	North("N", "W", "E"),
	South("S", "E", "W"),
	West("W", "S", "N"),
	East("E", "N", "S");
	
	private final String code;
	private final String gauche;
	private final String droite;
	
	private Direction(final String code,final String codeGauche,final String codeDroite) {
		this.code = code;
		this.gauche = codeGauche;
		this.droite = codeDroite;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public Direction getLeftDirection() {
		return getDirectionFromCode(gauche);
	}
	
	public Direction getRightDirection() {
		return getDirectionFromCode(droite);
	}
	
	public static Direction getDirectionFromCode(final String code) {
		for (Direction direction : Direction.values()) {
			if (direction.getCode().equals(code)) {
				return direction;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		return this.code;
	}
}