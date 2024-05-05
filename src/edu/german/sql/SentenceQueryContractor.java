package edu.german.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import edu.german.dao.DbConnect;
import edu.german.sentences.Sentence;
import edu.german.tools.TextHandler;

public class SentenceQueryContractor extends QueryContractor {

	public SentenceQueryContractor() {
		super();
	}

	public void addSentenceToDatabase(String sql, String sentence, String meaning, String genus, String mode,
			String tens, String word) {
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, sentence);
			ps.setString(2, meaning);
			ps.setString(3, genus);
			ps.setString(4, mode);
			ps.setString(5, tens);
			ps.setString(6, word);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}
	}

	public int getWoidFromSentence(String sql, int oid) {
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, oid);

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

	public Sentence getSentence(String sql, String sentenceStr, String meaningStr) {
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();
		Sentence sentence = new Sentence();
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, sentenceStr);
			ps.setString(2, meaningStr);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				sentence.setOid(rs.getInt("oid"));
				sentence.setSentence(rs.getString("sentence"));
				sentence.setMeaning(rs.getString("meaning"));
				sentence.setType(rs.getString("type"));
				sentence.setCategory(rs.getString("category"));
				sentence.setTens(rs.getString("tens"));
				sentence.setWord(rs.getString("word"));
				sentence.setWoid(rs.getInt("woid"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}
		return sentence;
	}

	public void addSentenceToDatabase(String sql, String sentence, String meaning, String genus, String mode,
			String tens, String word, int woid) {
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, sentence);
			ps.setString(2, meaning);
			ps.setString(3, genus);
			ps.setString(4, mode);
			ps.setString(5, tens);
			ps.setString(6, word);
			ps.setInt(7, woid);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}
	}

	public List<String> getSentencesList(String sql, String separationSign, String category) {
		List<String> list = new LinkedList<>();
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, category);

			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int numOfCol = rsmd.getColumnCount();

			while (rs.next()) {
				StringBuilder sb = new StringBuilder();
				for (int i = 1; i <= numOfCol; i++) {
					Object var = rs.getObject(i);
					if (var != null) {
						sb.append(var.toString());
						sb.append(separationSign);
					}
				}
				list.add(sb.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}
		return list;
	}

	public List<String> getSentencesList(String sql, String separationSign) {
		List<String> list = new LinkedList<>();
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int numOfCol = rsmd.getColumnCount();

			while (rs.next()) {
				StringBuilder sb = new StringBuilder();
				for (int i = 1; i <= numOfCol; i++) {
					Object var = rs.getObject(i);
					if (var != null)
						sb.append(var.toString());
					else
						sb.append("null");
				}
				list.add(sb.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}
		return list;
	}

	public List<String> getSentencesAsList(String sql) {
		List<String> list = new LinkedList<>();
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int numOfCol = rsmd.getColumnCount();

			while (rs.next()) {
				StringBuilder sb = new StringBuilder();
				for (int i = 1; i <= numOfCol; i++) {
					Object var = rs.getObject(i);
					if ((i < numOfCol) && (var != null))
						sb.append(new TextHandler().removeTab(var.toString()) + ";");
					else if (var != null)
						sb.append(new TextHandler().removeTab(var.toString()));
				}
				list.add(sb.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}
		return list;
	}

	public List<String[]> getSentencesList(String sql) {
		List<String[]> list = new LinkedList<String[]>();
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int numOfCol = rsmd.getColumnCount();

			while (rs.next()) {
				String[] str = new String[numOfCol];
				for (int k = 0, i = 1; i <= numOfCol; i++, k++) {
					Object var = rs.getObject(i);
					if (var != null)
						str[k] = var.toString();
					else
						str[k] = "null";
				}
				list.add(str);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}
		return list;
	}

}
