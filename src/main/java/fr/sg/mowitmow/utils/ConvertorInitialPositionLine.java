package fr.sg.mowitmow.utils;


import fr.sg.mowitmow.bean.Direction;
import fr.sg.mowitmow.bean.Position;
import io.micrometer.common.util.StringUtils;

public class ConvertorInitialPositionLine extends AbstractConvertorLine<Position>{

	public ConvertorInitialPositionLine(final String line) {
		super(line);
	}

	@Override
	protected boolean isValidLine() {
		return StringUtils.isNotBlank(line) && line.matches("^[0-9] [0-9] (N|S|W|E)$");
	}

	@Override
	protected Position process() {
		String[] split = line.split(" ");
		Direction direction = Direction.getDirectionFromCode(split[2]);
		return new Position(Integer.valueOf(split[0]), Integer.valueOf(split[1]), direction);
	}
}
