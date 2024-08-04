package cst8288.project.fwrp.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.logging.Level;

/**
 * Logger class is a singleton class that logs messages to console and file.
 */
public class Logger {

	private static Logger instance;
	private Path file;

	private Logger() {}
	/***
	 * Logs the message to console and file.
	 * @param message the message to log
	 * @return the logged message
	 */
	public String info(String message) {
		return info(message, false);

	}

	/**
	 * Logs the object to console and file.
	 * @param object the object to log
	 * @return the logged message
	 */
	public String info(Object object) {
		return info(object.toString(), false);

	}
	/**
	 * Logs the message to console and file.
	 * @param message the message to log
	 * @param saveToFile whether to save the message to file
	 * @return the logged message
	 */
	public String info(String message, boolean saveToFile) {
		String content = formatter(Level.INFO, message);
		if (saveToFile) {
			filewriter(content);
		}
		System.out.print(content);
		return content;
	}
	/**
	 * Logs the message to console and file.
	 * @param message the message to log
	 * @return the logged message
	 */
	public String warn(String message) {
		return warn(message, false);
	}
	/**
	 * Logs the message to console and file.
	 * @param message the message to log
	 * @param saveToFile whether to save the message to file
	 * @return the logged message
	 */
	public String warn(String message, boolean saveToFile) {
		String content = formatter(Level.WARNING, message);
		if (saveToFile) {
			filewriter(content);
		}
		System.out.print(content);
		return content;
	}

	/**
	 * Logs the message to console and file.
	 * @param message the message to log
	 * @return the logged message
	 */
	public void setFile(String file) {
		this.file = Path.of("src/main/resources/log/" + file);
		if (!Files.isRegularFile(this.file)) {
			try {
				Files.createFile(this.file);
			} catch (IOException e) {
				System.err.println(e.getLocalizedMessage());
			}
		}
	}
	/**
	 * Logs the message to console and file.
	 * @param message the message to log
	 * @param saveToFile whether to save the message to file
	 * @return the logged message
	 */
	private void filewriter(String message) {
		try {
			PrintWriter writer = new PrintWriter(new FileWriter(this.file.toString(), true));
			writer.print(message);
			writer.close();
		} catch (IOException e) {
			System.err.println(e.getLocalizedMessage());
		}
	}
	/**
	 * Formats the message with the current time and level.
	 * @param level the level of the message
	 * @param message the message to format
	 * @return the formatted message
	 */
	private String formatter(Level level, String message) {
		return "[%s]<<%s>>:%s%n".formatted(LocalDateTime.now(), level, message);
	}
	/**
	 * Gets the instance of the Logger.
	 * @return the instance of the Logger
	 */
	public static Logger getLogger() {
		if (instance == null) {
			instance = new Logger();
		}
		return instance;
	}

}
