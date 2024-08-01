package cst8288.project.fwrp.utils;


import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;

public class PropertiesLoader {
	private static Logger log = Logger.getLogger();
	private static Properties properties = new Properties();

	private PropertiesLoader() {}

	public static Properties load() {

		try  {
			// inputStream 
		InputStream in = PropertiesLoader.class.getClassLoader().getResourceAsStream("/application.properties");
		log.info(in + " loaded successfully");
		properties.load(in);
		} catch (IOException e) {
			// logger
			log.warn(e.getLocalizedMessage());
		}
		return properties;
	}
}
