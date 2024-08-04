package cst8288.project.fwrp.utils;



/**
 * LoggerFactory: Factory to create Java Util Logger, also create a .log for
 * logging errors
 */
public class LoggerFactory {

	/**
	 * Factory produce a logger
	 * @return Logger
	 */
	public static Logger getLogger() {
		return Logger.getLogger();
	}
	/**
	 * Get a logger with a specific filename
	 *
	 * @param filename
	 * @return Logger
	 */
	public static Logger getLogger(String filename) {
		Logger logger = Logger.getLogger();
		logger.setFile(filename);
		return logger;
	}

}
