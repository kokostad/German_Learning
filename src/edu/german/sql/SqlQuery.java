package edu.german.sql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * SqlQuery.java
 * @author Tadeusz Kokotowski, email: t.kokotowski@gmail.com
 * The Class get SQL query from file return query as string
 */
public class SqlQuery {
	private String FILE_CFG = "src/edu/german/sql/queries.lst";
	private static String rgxStart = "[";
	private static String rgxEnd = "]";
	private String sql;

	public String getSql(String pattern) {
		String expression = rgxStart + pattern + rgxEnd;
		BufferedReader br = null;
		try {
			File file = new File(FILE_CFG);
			br = new BufferedReader(new FileReader(file));
			StringBuffer textBuffer = new StringBuffer();
			String line;
			while ((line = br.readLine()) != null) {
				if (line.equals(expression))
					while ((line = br.readLine()) != null) {
						if (line.contains("EOSQL"))
							break;
						else {
							sql = textBuffer.append(line).toString();
						}
					}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
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

}
