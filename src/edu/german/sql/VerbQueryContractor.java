package edu.german.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import edu.german.dao.DbConnect;
import edu.german.tools.PrepareArrayFromString;
import edu.german.words.Verb;

/**
 * VerbQueryContractor.java
 * 
 * @author Tadeusz Kokotowski, email: t.kokotowski@gmail.com The class performs
 *         SQL queries
 */
public class VerbQueryContractor extends QueryContractor {

	public int getVerbId(String sql, String word, String irregular, String separable) {
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, word);
			ps.setString(2, irregular);
			ps.setString(3, separable);

			ResultSet rs = ps.executeQuery();
			while (rs.next())
				return rs.getInt("oid");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}

		return -1;
	}

	public String getVerbMeaning(String sql, String word, String irregular, String separable) {
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, word);
			ps.setString(2, irregular);
			ps.setString(3, separable);

			ResultSet rs = ps.executeQuery();
			while (rs.next())
				return rs.getString("meaning");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}

		return null;
	}

	public void addVerb(String sql, String modus, int woid, String tens, String ich, String ja, String du, String ty,
			String erSieEs, String onOnaOno, String wir, String my, String ihr, String wy, String sieSie,
			String oniPanstwo) {

		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, modus);
			ps.setString(2, tens);
			ps.setString(3, ich);
			ps.setString(4, ja);
			ps.setString(5, du);
			ps.setString(6, ty);
			ps.setString(7, erSieEs);
			ps.setString(8, onOnaOno);
			ps.setString(9, wir);
			ps.setString(10, my);
			ps.setString(11, ihr);
			ps.setString(12, wy);
			ps.setString(13, sieSie);
			ps.setString(14, oniPanstwo);
			ps.setInt(15, woid);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}
	}

	public void addVerb(String sql, int woid, String modus, String tens, String presentInfinitiveGe,
			String presentInfinitivePl, String infinitivePerfectGe, String infinitivePerfectPl, String participleIGe,
			String participleIPl, String participleIIGe, String participleIIPl) {

		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, woid);
			ps.setString(2, modus);
			ps.setString(3, tens);
			ps.setString(4, presentInfinitiveGe);
			ps.setString(5, presentInfinitivePl);
			ps.setString(6, infinitivePerfectGe);
			ps.setString(7, infinitivePerfectPl);
			ps.setString(8, participleIGe);
			ps.setString(9, participleIPl);
			ps.setString(10, participleIIGe);
			ps.setString(11, participleIIPl);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}

	}

	public int getVerbId(String sql, int woid, String tens) {
		int oid = -1;
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, woid);
			ps.setString(2, tens);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				oid = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}

		return oid;
	}

	public int getVerb(String sql, String word, String value) {
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, word);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(value);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}

		return -1;
	}

	public Verb getSimpleVerb(String sql) {
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();
		Verb verb = new Verb();
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				verb.setOid(rs.getInt("oid"));
				verb.setWord(rs.getString("word"));
				verb.setMeaning(rs.getString("meaning"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}
		return verb;
	}

	public Properties getVerbProperties(String sql, int oid) {
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
		return properties;
	}

	public Verb getVerb(String sql, Integer woid, String type) {
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();
		Verb verb = new Verb();
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, woid);
			ps.setString(2, type);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				verb.setOid(rs.getInt("oid"));
				verb.setWoid(rs.getInt("woid"));
				verb.setWord(rs.getString("word"));
				verb.setMeaning(rs.getString("meaning"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}
		return verb;
	}

	public List<Verb> getVerbList(String sql) {
		List<Verb> list = new LinkedList<>();
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();
		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Verb verb = new Verb();
				verb.setOid(rs.getInt("oid"));
				verb.setWoid(rs.getInt("woid"));
				verb.setMainWord(rs.getString("word"));
				verb.setMeaning(rs.getString("meaning"));
				verb.setMeanings(new PrepareArrayFromString(rs.getString("meaning")).getArray());
				verb.setGenus(rs.getString("genus"));
				list.add(verb);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}

		return list;
	}

	public int getVoid(String sql, String word) {
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, word);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}

		return -1;
	}

	public int getVoid(String sql, String word, String irregular, String separable) {
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, word);
			ps.setString(2, irregular);
			ps.setString(3, separable);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}

		return -1;
	}

	public Properties getProperties(String sql, int oid, String tens, String modus) {
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();
		Properties properties = new Properties();
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, oid);
			ps.setString(2, tens);
			ps.setString(3, modus);

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

	public void addVerb(String query, String word, String meaning, String irregular, String separable) {
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();

		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, word);
			ps.setString(2, meaning);
			ps.setString(3, irregular);
			ps.setString(4, separable);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}
	}

	public void addVerbProperties(String query) {
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();

		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}
	}

	public void addVerbProperties(String query, int oid, String word, String irregular, String separable,
			Properties prop) {
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();

		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}
	}

	public boolean existVerbConjugation(String sql) {
		loadDriver();
		int id = -1;

		dbc = new DbConnect();
		con = dbc.getConnection();

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}

		if (id > 0)
			return true;

		return false;
	}

	public void updateVerb(String sql, int id, String word, String irregular, String separable) {
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, irregular);
			ps.setString(2, separable);
			ps.setInt(3, id);
			ps.setString(4, word);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}
	}
}
