package fr.sg.mowitmow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sg.mowitmow.bean.Command;
import fr.sg.mowitmow.bean.GardenLimit;
import fr.sg.mowitmow.bean.Mower;
import fr.sg.mowitmow.bean.Position;
import fr.sg.mowitmow.exception.FileFormatInvalidException;
import fr.sg.mowitmow.exception.InitialPositionMowerInvalidException;
import fr.sg.mowitmow.utils.ConvertorCommandLine;
import fr.sg.mowitmow.utils.ConvertorGardenSizeLine;
import fr.sg.mowitmow.utils.ConvertorInitialPositionLine;

public class MowerControl {

	private static final Logger logger = LoggerFactory.getLogger(MowerControl.class);

	private final List<Mower> mowers = new ArrayList<Mower>();

	public MowerControl(final List<String> file)
			throws FileFormatInvalidException, InitialPositionMowerInvalidException {

		if (file == null || file.size() == 0) {
			logger.error("File is empty !");
			throw new FileFormatInvalidException();
		}

		Iterator<String> iterator = file.iterator();

		String gardenLimitLine = iterator.next();
		GardenLimit gardenLimit = new ConvertorGardenSizeLine(gardenLimitLine).convert();

		while (iterator.hasNext()) {
			String positionMowerLine = iterator.next();
			Position position = new ConvertorInitialPositionLine(positionMowerLine).convert();

			if (!iterator.hasNext()) {
				logger.error("File format is invalid !");
				throw new FileFormatInvalidException();
			}

			String mowerCommands = iterator.next();
			List<Command> commands = new ConvertorCommandLine(mowerCommands).convert();

			Mower mower = new Mower(gardenLimit, position, commands);
			mowers.add(mower);
		}
	}

	public List<Position> process() {
		List<Position> position = new ArrayList<Position>(mowers.size());
		for (Mower mower : mowers) {
			position.add(mower.process());
		}
		return position;
	}
}