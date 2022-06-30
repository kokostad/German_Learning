package edu.german.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import edu.german.dao.DbConnect;
import edu.german.tools.Translator;
import edu.german.words.WordPkg;
import edu.german.words.model.Noun;
import edu.german.words.model.Word;

public class QueryContractor {
	private DbConnect dbc;
	private Connection con;

	public boolean executeQuery(String sql) {
		boolean state = false;
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			state = ps.execute();
			dbc.closeConnection(con);
			return state;
		} catch (SQLException e) {
			e.printStackTrace();
			return state;
		}
	}

	private void showSql(String sql) {
		System.out.println(sql);
	}

	public void loadDriver() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public boolean executeQuery(String sql, String str1, String str2, String str3) {
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, str1);
			ps.setString(2, str2);
			ps.setString(3, str3);

			return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void closeConnection() {
		dbc.closeConnection(con);
	}

	public int getId(String sql, String string, String genus) {
		int id = -1;
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, string);
			ps.setString(2, genus);

			ResultSet rs = ps.executeQuery();
			while (rs.next())
				id = rs.getInt(1);

			return id;
		} catch (SQLException e) {
			e.printStackTrace();
			return id;
		}
	}

	public List<String[]> getWordsList(String sql) {
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
		}

		return list;
	}

	/*
	 * NOTICE need to change and improve this method need to return the list of word
	 * as array or hashMap
	 */
	public List<String[]> getWordsList(String sql, String genus, int number) {
		List<String[]> list = new ArrayList<String[]>();

		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, genus);
			ps.setInt(2, number);

			showSql(ps.toString());

			ResultSet rs = ps.executeQuery();
			while (rs.next())
				list.addAll(rs.getRow(), list);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public void getWordFromMainTable(int woid) {

	}

	public Map<Object, Object> getWordFromMainTable(String sql, String word) {
		Map<Object, Object> wordMap = new HashMap<Object, Object>();

		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, word);

			showSql(ps.toString());

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				wordMap.put("WOID", rs.getInt(1));
				wordMap.put("WORD", rs.getString(2));
				wordMap.put("MEANINGS", rs.getString(3));
				wordMap.put("GENUS", rs.getString(4));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return wordMap;
	}

	public int getWoidByWord(String sql, String word) {
		int woid = -1;
		loadDriver();

		dbc = new DbConnect();
		con = dbc.getConnection();
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, word);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				woid = rs.getInt("woid");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}

		return woid;
	}

	public List<Noun> getNounsList(String sql, int number) {
		List<Noun> nounLst = new LinkedList<>();

		int limit = number;
		loadDriver();

		dbc = new DbConnect();
		con = dbc.getConnection();

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, limit);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Noun noun = new Noun();
				noun.setOid(rs.getInt("oid"));
				noun.setWoid(rs.getInt("woid"));
				noun.setNoun(rs.getString("word").split(" "));
				noun.setWord(rs.getString("word"));
				String[] article = rs.getString("word").split(" ", 1);
				noun.setArticle(article[0]);
				noun.setMeaning(rs.getString("meaning"));
				noun.setMeanings(rs.getString("meaning").split(", "));
				nounLst.add(noun);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}

		return nounLst;
	}

	public Noun getNoun(String sql, String word) {
		loadDriver();
		Noun noun = new Noun();
		dbc = new DbConnect();
		con = dbc.getConnection();
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, word);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				noun.setWoid(rs.getInt("woid"));
				noun.setOid(rs.getInt("oid"));
				noun.setWord(rs.getString("word"));
				noun.setNoun(rs.getString("word").split(" "));
				String[] article = rs.getString("word").split(" ", 1);
				noun.setArticle(article[0]);
				noun.setMeaning(rs.getString("meaning"));
				noun.setMeanings(rs.getString("meaning").split(", "));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}

		return noun;
	}

	public List<Noun> getAllNounsList(String sql) {
		List<Noun> nounLst = new LinkedList<>();

		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Noun noun = new Noun();
				noun.setWoid(rs.getInt("woid"));
				noun.setOid(rs.getInt("oid"));
				noun.setWord(rs.getString("word"));
				noun.setNoun(rs.getString("word").split(" "));
				String[] article = rs.getString("word").split(" ");
				noun.setArticle(article[0]);
				noun.setMeaning(rs.getString("meaning"));
				noun.setMeanings(rs.getString("meaning").split(", "));
				nounLst.add(noun);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}

		return nounLst;
	}

	public List<Word> getAllWordsList(String sql) {
		List<Word> wordLst = new LinkedList<>();

		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Word word = new Word();
				word.setWoid(rs.getInt("woid"));
				word.setOid(rs.getInt("oid"));
				word.setMainWord(rs.getString("word"));
				word.setMeaning(rs.getString("meaning"));
				word.setGenus(rs.getString("genus"));
				wordLst.add(word);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}

		return wordLst;
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
		}

		return list;
	}

}
