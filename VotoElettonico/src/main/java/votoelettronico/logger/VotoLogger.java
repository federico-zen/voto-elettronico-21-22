package votoelettronico.logger;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class VotoLogger {
	
	private static Logger logger = Logger.getLogger("logger");
	
	public static void init() {
		FileHandler fh;
		try {
			fh = new FileHandler("log/logfile.log",true);
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			
			logger.info("Logger Inizializzato");
			
		}catch(Exception e) {
			logger.log(Level.WARNING,"Exception : ",e);
		}
		
	}
	
	public static void writeToLog(String msg) {
		logger.info(msg);
	}
	
	public static void writeToLog(String msg,Level level,Exception e) {
		logger.log(level,msg,e);
	}
	
}	