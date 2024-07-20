package cst8288.project.fwrp.utils;


import java.util.Map;
import java.util.Properties;

import jakarta.servlet.ServletContext;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PropertiesLoader {
	private static Logger log = Logger.getLogger();
	private static Properties properties = new Properties();

	private PropertiesLoader() {}

	public static Properties load() {

		try  {
			// inputStream 
		InputStream in = PropertiesLoader.class.getClassLoader().getResourceAsStream("/application.properties");
		properties.load(in);
		} catch (IOException e) {
			// logger
			log.warn(e.getLocalizedMessage());
		}
		return properties;
	}
}
