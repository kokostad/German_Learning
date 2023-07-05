package edu.german.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;

import edu.german.dao.DbConnect;
import edu.german.words.model.Noun;

public class NounQueryContractor extends QueryContractor {

	public Properties getProperties(String sql, int oid, String[] modus) {
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();
		Properties properties = new Properties();
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, oid);
			ps.setString(2, modus[0]);

			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();

			String[] headers = new String[count];
			for (int i = 1, k = 0; i <= headers.length; i++, k++)
				headers[k] = rsmd.getColumnName(i);

			while (rs.next()) {
				for (int k = 0; k < headers.length; k++)
					if (rs.getObject(headers[k]) != null)
						properties.put(headers[k].toUpperCase(), rs.getObject(headers[k]));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}

		if (!properties.isEmpty())
			return properties;

		return null;
	}

	public Properties getProperties(String sql, int oid) {
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();
		Properties properties = new Properties();
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, oid);

			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();

			String[] headers = new String[count];
			for (int i = 1, k = 0; i <= headers.length; i++, k++)
				headers[k] = rsmd.getColumnName(i);

			while (rs.next()) {
				for (int k = 0; k < headers.length; k++)
					if (rs.getObject(headers[k]) != null)
						properties.put(headers[k].toUpperCase(), rs.getObject(headers[k]));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}

		if (!properties.isEmpty())
			return properties;

		return null;
	}

	public Noun getById(String sql, int id) {
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();

		Noun noun = new Noun();

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				noun.setOid(id);
				String word = rs.getString("word");
				noun.setNoun(word);
				noun.setWord(word);
				noun.setArticle(word);
				noun.setMainPart(word);
				noun.setMeaning(rs.getString("meaning"));
				if (rs.getString("word_plural") != null)
					noun.setWordPlural(rs.getString("word_plural"));
				if (rs.getString("meaning_plural") != null)
					noun.setMeanigPlural(rs.getString("meaning_plural"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}
		return noun;
	}

}
