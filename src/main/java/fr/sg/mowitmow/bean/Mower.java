package fr.sg.mowitmow.bean;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sg.mowitmow.exception.InitialPositionMowerInvalidException;

public class Mower {

	private static final Logger logger = LoggerFactory.getLogger(Mower.class);

	private final Position position;
	private final GardenLimit limite;
	private final List<Command> cmds;

	public Mower(final GardenLimit limit, final Position position, final List<Command> cmds)
			throws InitialPositionMowerInvalidException {

		if (!position.isValidPosition(limit)) {
			logger.error("Initial mower position is invalid !");
			throw new InitialPositionMowerInvalidException();
		}

		this.position = position;
		this.limite = limit;
		this.cmds = cmds;
	}

	public Position process() {
		for (Command cmd : cmds) {
			processCommand(cmd);
		}
		return position;
	}

	private void processCommand(final Command cmd) {
		switch (cmd) {
		case Avance:
			position.aheadIfPossible(limite);
			break;
		case Droite:
			position.turnRightDirection();
			break;
		case Gauche:
			position.turnLeftDirection();
			break;
		}
	}

	public static Mower createMowerBean(GardenLimit limits, String line1, String line2)
			throws InitialPositionMowerInvalidException {
		String[] T = line1.split(" ");
		int x = Integer.parseInt(T[0]);
		int y = Integer.parseInt(T[1]);
		String direction = T[2];
		return new Mower(limits, new Position(x, y, Direction.valueOf(direction)), getCommands(line2.split(" ")));
	}

	private static List<Command> getCommands(String[] motions) {
		List<Command> commands = new ArrayList<>();
		for (String motion : motions) {
			commands.add(Command.valueOf(motion));
		}
		return commands;
	}
	
	public Position getPosition() {
		return position;
	}
}