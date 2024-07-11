package cst8288.project.fwrp.utils;


import java.util.Map;
import java.util.Properties;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PropertiesLoader {
	private static Logger logger = Logger.getLogger();
	private static Properties properties = new Properties();

	private PropertiesLoader() {}

	public static Properties load() {

		try  {
		Path file = Paths.get("src/main/resources/application.properties");

		System.out.println(new File(".").getAbsolutePath());
		var f = PropertiesLoader.class.getResourceAsStream("/application.properties");
		properties.load(f);
		System.out.println(properties);
		} catch (IOException e) {
			// logger
			logger.warn(e.getLocalizedMessage());
		}
//		try {
//			if (!Files.isRegularFile(file)) {
//				Files.createFile(file);
//			}
//			try (var reader = Files.newBufferedReader(file); var lines = reader.lines();) {
//				lines.forEach(line -> {
//					var kv = line.split("=");
//					properties.putIfAbsent(kv[0].trim(), kv[1].trim());
//				});
//			}
//		} catch (IOException e) {
//			// logger
//
//		}
		return properties;
	}
}
