package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

/**
 * LoggerFactory: Factory to create Java Util Logger, also create a .log for
 * logging errors
 */
public class LoggerFactory {


	public static void main(String[] args) {
	System.out.print(System.getProperty("user.dir"));	
		Logger l1 = LoggerFactory.getLogger();
		Logger l2 = LoggerFactory.getLogger("application.log");
		l1.info("Hello from Log 1");
		l2.info("Hello from Log 2", true);
		l1.warn("Warning from Log 1");
		l2.warn("Warning from Log 2", true);
	}

	public static Logger getLogger() {
		return Logger.getLogger();
	}
	public static Logger getLogger(String filename) {
		Logger logger = Logger.getLogger();
		logger.setFile(filename);
		return logger;
	}

}
