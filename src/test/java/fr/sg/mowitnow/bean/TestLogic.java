package fr.sg.mowitnow.bean;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import fr.sg.mowitmow.SpringBatchApplication;
import fr.sg.mowitmow.bean.Command;
import fr.sg.mowitmow.bean.Direction;
import fr.sg.mowitmow.bean.GardenLimit;
import fr.sg.mowitmow.bean.Mower;
import fr.sg.mowitmow.bean.Position;
import fr.sg.mowitmow.exception.InitialPositionMowerInvalidException;

@SpringBootTest(classes = { SpringBatchApplication.class })
public class TestLogic {

	@Test
	public void testProcess() throws InitialPositionMowerInvalidException {
		GardenLimit gardenLimit = new GardenLimit(5, 5);
		Position position = new Position(0, 0, Direction.North);
		List<Command> cmds = new ArrayList<>();
		cmds.add(Command.Avance);
		cmds.add(Command.Droite);
		cmds.add(Command.Droite);
		Mower mower = new Mower(gardenLimit, position, cmds);
		mower.process();
		Position expectedPosition = new Position(0, 1, Direction.South);
		assertEquals(expectedPosition.toString(), mower.getPosition().toString());
	}

	@Test
	public void testProcessOutLimits() throws InitialPositionMowerInvalidException {
		GardenLimit gardenLimit = new GardenLimit(5, 5);
		Position position = new Position(0, 5, Direction.North);
		List<Command> cmds = new ArrayList<>();
		cmds.add(Command.Avance);
		cmds.add(Command.Droite);
		cmds.add(Command.Gauche);
		Mower mower = new Mower(gardenLimit, position, cmds);
		mower.process();
		Position expectedPosition = new Position(0, 5, Direction.North);
		assertEquals(expectedPosition.toString(), mower.getPosition().toString());
	}

}
