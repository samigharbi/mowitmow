package fr.sg.mowitmow.utils;

import fr.sg.mowitmow.bean.GardenLimit;
import io.micrometer.common.util.StringUtils;

public class ConvertorGardenSizeLine extends AbstractConvertorLine<GardenLimit>{

	public ConvertorGardenSizeLine(final String line) {
		super(line);
	}

	@Override
	protected boolean isValidLine() {
		return StringUtils.isNotBlank(line) && line.matches("^[0-9] [0-9]$");
	}

	@Override
	protected GardenLimit process() {
		String[] pos = line.split(" ");
		return new GardenLimit(Integer.valueOf(pos[0]), Integer.valueOf(pos[1]));
	}
}
