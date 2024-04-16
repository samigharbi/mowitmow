package fr.sg.mowitmow.bean;

public enum Command {
	
	Avance("A"),
	Droite("D"),
	Gauche("G");

	private String code;
	
	private Command(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
	public static Command getCommandeFromCode(final String code) {
		for (Command commande : Command.values()) {
			if (commande.getCode().equals(code)) {
				return commande;
			}
		}
		return null;
	}
}
