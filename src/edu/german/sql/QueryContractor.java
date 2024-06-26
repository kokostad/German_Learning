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
import edu.german.sentences.Sentence;
import edu.german.tools.PrepareArrayFromString;
import edu.german.tools.PrepareString;
import edu.german.tools.TextHandler;
import edu.german.words.Noun;
import edu.german.words.cfg.WordGenus;
import edu.german.words.model.Word;

/**
 * QueryContractor.java
 * 
 * @author Tadeusz Kokotowski, email: t.kokotowski@gmail.com The class performs
 *         SQL queries
 */
public class QueryContractor {
	protected DbConnect dbc;
	protected Connection con;

	public void loadDriver() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void executeSQL(String sql) {
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}
	}

	public boolean executeQuery(String sql) {
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}
		return false;
	}

	public boolean executeQuery(String sql, String str1) {
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, str1);

			return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}
		return false;
	}

	public boolean executeQuery(String sql, String str1, String str2) {
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, str1);
			ps.setString(2, str2);

			return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}
		return false;
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
		} finally {
			dbc.closeConnection(con);
		}
		return false;
	}

	public int getId(String sql) {
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();

			while (rs.next())
				return rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}
		return -1;
	}

	public int getId(String sql, String variable) {
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, variable);

			ResultSet rs = ps.executeQuery();
			while (rs.next())
				return rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}
		return -1;
	}

	public int getWoid(String sql, String word, String genus) {
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, word);
			ps.setString(2, genus);

			ResultSet rs = ps.executeQuery();
			while (rs.next())
				return rs.getInt("woid");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}
		return -1;
	}

	public int getId(String sql, String valueOne, String valueTwo) {
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, valueOne);
			ps.setString(2, valueTwo);

			ResultSet rs = ps.executeQuery();
			while (rs.next())
				return rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}
		return -1;
	}

	public int getId(String sql, String word, String genus, int woid) {
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, word);
			ps.setString(2, genus);
			ps.setInt(3, woid);

			ResultSet rs = ps.executeQuery();
			while (rs.next())
				return rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}
		return -1;
	}

	public int getId(String sql, String mainWord, int woid, String param, String param2) {
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, mainWord);
			ps.setString(2, param);
			ps.setString(3, param2);
			ps.setInt(4, woid);

			ResultSet rs = ps.executeQuery();
			while (rs.next())
				return rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}
		return -1;
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
		} finally {
			dbc.closeConnection(con);
		}
		return list;
	}

	public List<String> getWordsAsStringList(String sql) {
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
					if (i < numOfCol)
						sb.append(var.toString() + ";");
					if (i == numOfCol && var != null)
						sb.append(var.toString());
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

			ResultSet rs = ps.executeQuery();
			while (rs.next())
				list.addAll(rs.getRow(), list);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}
		return list;
	}

	public void getWordFromMainTable(int woid) {
		// NOTICE method to do
	}

	public Map<Object, Object> getWordFromMainTable(String sql, String word) {
		Map<Object, Object> wordMap = new HashMap<Object, Object>();
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, word);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				wordMap.put("WOID", rs.getInt(1));
				wordMap.put("WORD", rs.getString(2));
				wordMap.put("MEANINGS", rs.getString(3));
				wordMap.put("GENUS", rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}
		return wordMap;
	}

	public int getWoidByWord(String sql, String word) {
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, word);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt("woid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}
		return -1;
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
				noun.setWord(rs.getString("word"));
				String[] article = rs.getString("word").split(" ", 1);
				noun.setArticle(article[0]);
				noun.setMeaning(rs.getString("meaning"));
				noun.setMeanings(new PrepareArrayFromString(rs.getString("meaning")).getArray());
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
				String[] article = rs.getString("word").split(" ", 1);
				noun.setArticle(article[0]);
				noun.setMeaning(rs.getString("meaning"));
				noun.setMeanings(new PrepareArrayFromString(rs.getString("meaning")).getArray());
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
				String[] array = rs.getString("word").split(" ");
				noun.setArticle(array[0]);
				noun.setMeaning(rs.getString("meaning"));
				noun.setMeanings(new PrepareArrayFromString(rs.getString("meaning")).getArray());
				nounLst.add(noun);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}
		return nounLst;
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

	public boolean addAdjectiveGraduation(String sql, String word, String goal, String goal2, String goal3,
			String goal4, String goal5, String goal6) {
		boolean state = false;
		int i = -1;
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, goal);
			ps.setString(2, goal2);
			ps.setString(3, goal3);
			ps.setString(4, goal4);
			ps.setString(5, goal5);
			ps.setString(6, goal6);
			ps.setString(7, word);
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}

		if (i > 0)
			state = true;

		return state;
	}

	public void addNewWord(String sql, String mainWord, String meaning, String genus) {
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, mainWord);
			ps.setString(2, meaning);
			ps.setString(3, genus);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}
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

	public List<Noun> getAllNounsFromView(String sql) {
		List<Noun> list = new LinkedList<>();
		loadDriver();

		dbc = new DbConnect();
		con = dbc.getConnection();

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Noun noun = new Noun();
				noun.setWord(rs.getString("word"));
				String[] article = rs.getString("word").split(" ");
				noun.setArticle(article[0]);
				noun.setMeaning(rs.getString("meaning"));
				noun.setMeanings(new PrepareArrayFromString(rs.getString("meaning")).getArray());

				list.add(noun);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}
		return list;
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

	public int getWoidByWord(String query, String word, String genus) {
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();

		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, word);
			ps.setString(2, genus);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt("woid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}
		return -1;
	}

	public List<Map<String, String>> getObjectMap(String sql) {
		List<Map<String, String>> list = new LinkedList<>();
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int numOfCol = rsmd.getColumnCount();

			while (rs.next()) {
				Map<String, String> map = new HashMap<>();
				for (int i = 1; i <= numOfCol; i++) {
					Object var = rs.getObject(i);
					if (var != null) {
						map.put(rsmd.getColumnName(i).toUpperCase(), var.toString());
					}
				}
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection(con);
		}
		return list;
	}

}
