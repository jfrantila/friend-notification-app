package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ReadInput {
	private static Logger LOGGER = Logger.getLogger(ReadInput.class.getName());
	private static final List<String> input = new ArrayList<String>();
	
	public static List<String> readFile(String fileName) {
		try {
			File file = new File(fileName);
			Scanner reader = new Scanner(file);
			while (reader.hasNextLine()) {
				input.add(reader.nextLine());
			}
			reader.close();
		} catch (FileNotFoundException e) {
			LOGGER.log(Level.WARNING, "Did not find the file: " + e.getMessage());
		}
		return input;
	}

}
