package edu.german.tools;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.File;

import edu.german.app.German;

public class MyLogger {
	static Logger logger;
	static LoggerContext context;
//	static File file = new File("./German_Learning/src/resources/log4j2.properties");

	public MyLogger(Class<German> cl) {
		File file = new File("./German_Learning/src/resources/log4j2.xml");
		context = (LoggerContext) LogManager.getContext(false);
		context.setConfigLocation(file.toURI());
		logger = LogManager.getLogger(cl);
	}

	public Object getContext() {
		return context.getExternalContext();
	}
}
