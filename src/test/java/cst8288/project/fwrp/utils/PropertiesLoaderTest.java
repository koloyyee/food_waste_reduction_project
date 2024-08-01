package cst8288.project.fwrp.utils;

import cst8288.project.fwrp.db.DBConnection;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class PropertiesLoaderTest {

	@Test
	void load() {

//		InputStream in = PropertiesLoader.class.getClassLoader().getResourceAsStream("application.properties");
//		 var p = PropertiesLoader.load("application.properties");
//		System.out.println(p);
		var c = DBConnection.getInstance().getConnection();
		assertNotNull(c);
	}
}