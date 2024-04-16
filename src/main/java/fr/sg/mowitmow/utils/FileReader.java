package fr.sg.mowitmow.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileReader {
	
	static final Logger logger = LoggerFactory.getLogger(FileReader.class);
	
	private static FileReader instance;
	
	private FileReader() {};
	
	public static FileReader getInstance() {
		if(instance == null) {
			instance = new FileReader();
		}
		return instance;
	}
	
	public List<String> read(final String fileName) throws FileNotFoundException {
		List<String> fileLine = new ArrayList<String>();
		   
        File file = new File(fileName);
 
        try {
        	Scanner scanner = new Scanner(file);
 
        	while (scanner.hasNextLine()) {
        		String line = scanner.nextLine();
        		fileLine.add(line);
        	}
        	scanner.close();
        } catch (FileNotFoundException e) {
        	logger.error("Le fichier est introuvable !");
        	throw e;
        }
		return fileLine;
	}
}
