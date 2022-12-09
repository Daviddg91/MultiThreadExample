package utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import org.springframework.boot.logging.java.SimpleFormatter;

public class CustomLogger {

	public void logInFile(String msg) {
		Logger logger = Logger.getLogger("MyLog");
		FileHandler fh;

		try {
			String filePath = new File("").getAbsolutePath() + "\\";

			fh = new FileHandler(filePath + "customLogFile.log", true);
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);

			logger.info(msg);
			fh.close();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException s) {
			s.printStackTrace();
		}

	}

}
