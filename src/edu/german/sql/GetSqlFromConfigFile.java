package edu.german.sql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GetSqlFromConfigFile {
	private String pattern;
	private static String rgxStart = "[";
	private static String rgxEnd = "]";
	private String sql;
	private static String FILEPATH = "src/edu/german/sql/queries.lst";

	public GetSqlFromConfigFile() {
	}

	public GetSqlFromConfigFile(String pattern) {
		this.pattern = pattern;
	}

	public String getSql() {
		String expression = rgxStart + getPattern() + rgxEnd;
		BufferedReader br = null;
		try {
			File file = new File(FILEPATH);
			br = new BufferedReader(new FileReader(file));
			StringBuffer textBuffer = new StringBuffer();
			String line;
			while ((line = br.readLine()) != null) {
				if (line.equals(expression))
					while ((line = br.readLine()) != null) {
						if (line.contains("EOSQL"))
							break;
						else {
							textBuffer.append(line);
							sql = textBuffer.toString();
						}
					}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException exception) {
					exception.printStackTrace();
				}
			}
		}

		return sql;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

}
