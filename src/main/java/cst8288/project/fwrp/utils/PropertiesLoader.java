package cst8288.project.fwrp.utils;


import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;

/**
 * PropertiesLoader class is a utility class to load properties from a file.
 */
public class PropertiesLoader {
	private static Logger log = Logger.getLogger();
	private static Properties properties = new Properties();

	private PropertiesLoader() {}
	
	public static Properties load() {
		return load("application.properties");
	}

	/**
	 * Load properties from a file.
	 * @param path the path to the file
	 * @return the properties
	 */
	public static Properties load(String path) {

		try  {
			// inputStream
		InputStream in = PropertiesLoader.class.getClassLoader().getResourceAsStream(path);
		properties.load(in);
		} catch (IOException e) {
			// logger
			log.warn(e.getLocalizedMessage());
		}
		return properties;
	}
}
