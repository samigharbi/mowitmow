package fr.sg.mowitmow.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sg.mowitmow.exception.FileFormatInvalidException;

public abstract class AbstractConvertorLine<T> {
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractConvertorLine.class);
	
	protected final String line;
	
	protected AbstractConvertorLine(final String line) {
		this.line = line;
	}
	
	protected abstract boolean isValidLine();
	protected abstract T process();
	
	public T convert() throws FileFormatInvalidException {
		if (!isValidLine()) {
			logger.error("File Format is invalid !");
			throw new FileFormatInvalidException();
		}
		return process();
	}
}