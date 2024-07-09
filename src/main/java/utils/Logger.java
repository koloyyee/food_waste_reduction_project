package utils;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.logging.Level;

public class Logger {

	private static Logger instance;
	private Path file;

	public static void main(String[] args) {
		var l = new Logger();
		l.info("Hello");
		l.warn("Warning");
	}

	private Logger() {
	}

	public String info(String message) {
		return info(message, false);

	}

	public String info(String message, boolean saveToFile) {
		String content = formatter(Level.INFO, message);
		if (saveToFile) {
			filewriter(content);
		}
		System.out.print(content);
		return content;
	}

	public String warn(String message) {
		return warn(message, false);
	}

	public String warn(String message, boolean saveToFile) {
		String content = formatter(Level.WARNING, message);
		if (saveToFile) {
			filewriter(content);
		}
		System.out.print(content);
		return content;
	}
	

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

	private void filewriter(String message) {
		try {
			PrintWriter writer = new PrintWriter(new FileWriter(this.file.toString(), true));
			writer.print(message);
			writer.close();
		} catch (IOException e) {
			System.err.println(e.getLocalizedMessage());
		}
	}

	private String formatter(Level level, String message) {
		return "[%s]<<%s>>:%s%n".formatted(LocalDateTime.now(), level, message);
	}

	public static Logger getLogger() {
		if (instance == null) {
			instance = new Logger();
		}
		return instance;
	}

}
