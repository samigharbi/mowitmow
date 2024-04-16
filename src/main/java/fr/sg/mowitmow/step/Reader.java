package fr.sg.mowitmow.step;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import fr.sg.mowitmow.utils.FileReader;

@Component
public class Reader implements ItemReader<List<String>> {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${file.input}")
	private String fileName;

	@Override
	public List<String> read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		List<String> fileLine = FileReader.getInstance()
				.read(fileName);

		return fileLine;
	}
}
