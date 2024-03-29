package edu.german.tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class MyProperties {
	private String DEFAULT_PATH = "config/";
	private String FILE;

	public MyProperties(String FILE) {
		this.FILE = DEFAULT_PATH + FILE;
	}

	public MyProperties(String PATH, String FILE) {
		this.FILE = PATH + FILE;
	}

	public HashMap<Object, Object> getProps() throws IOException {
		Properties properties = loadProps();
		Map<Object, Object> myMap = new HashMap<>();
		for (final Entry<Object, Object> entry : properties.entrySet())
			myMap.put((String) entry.getKey(), (String) entry.getValue());

		return (HashMap<Object, Object>) myMap;
	}

	public String getText(String pattern) {
		Properties properties = null;
		try {
			properties = loadProps();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return properties.getProperty(pattern);
	}

	public int getIntValue(String pattern) {
		Properties properties = null;
		try {
			properties = loadProps();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return Integer.parseInt(properties.getProperty(pattern));
	}

	/**
	 * @param pattern - criteria of choice
	 * @param change  - boolean
	 * @return Value as a String
	 */
	public String getValue(String pattern, boolean change) {
		Properties properties = null;
		try {
			properties = loadProps();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (change)
			return properties.getProperty(new PrepareString().replaceSpaceWithUnderscore(pattern));
		else
			return properties.getProperty(new PrepareString().replaceUnderscoreWithSpace(pattern));
	}

	public String getValue(String pattern) {
		Properties properties = null;
		try {
			properties = loadProps();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return properties.getProperty(pattern);
	}

	public String[] getValuesArray(String pattern) {
		if (getText(pattern) != null)
			return (getText(pattern)).split(",");

		return null;
	}

	public String[] getValuesArray(String pattern, boolean change) {
		return getValue(pattern, change).split(",");
	}

	private Properties loadProps() throws IOException {
		Properties properties = new Properties();
		properties.load(new InputStreamReader(new FileInputStream(FILE), Charset.forName("UTF-8")));
		return properties;
	}

	public double getDoubleValue(String pattern) {
		Properties properties = null;
		try {
			properties = loadProps();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return Double.parseDouble(properties.getProperty(pattern));
	}
}
