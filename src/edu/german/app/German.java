package edu.german.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.File;

/**
 * German.java
 * @author Tadeusz Kokotowski, email: t.kokotowski@gmail.com
 *
 */
public class German {
	static Logger logger = LogManager.getLogger(edu.german.app.German.class);

	public static void main(String[] args) {
//		System.setProperty("log4j.configurationFile", "./German_Learning/src/resources/log4j2.properties");
		File file = new File("./German_Learning/src/resources/log4j2.xml");
		LoggerContext context = (LoggerContext) LogManager.getContext(false);

//		context.getExternalContext(file.toURI());
		context.setConfigLocation(file.toURI());

		new Learning();
	}

}
