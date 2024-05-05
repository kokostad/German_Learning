package edu.german.sql;

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
import edu.german.tools.PrepareArrayFromString;
import edu.german.words.Noun;
import edu.german.words.model.Word;

public class WordQueryContractor extends QueryContractor {

	public WordQueryContractor() {
		super();
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

	public List<Word> getAllWordList(String sql) {
		List<Word> wordLst = new LinkedList<>();
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Word word = new Word();
				word.setWoid(rs.getInt("woid"));
				word.setMainWord(rs.getString("word"));
				word.setMeaning(rs.getString("meaning"));
				word.setMeanings(new PrepareArrayFromString(rs.getString("meaning")).getArray());
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

	public List<Map<String, String>> getObjectMapWithoutIDAndWithGenus(String sql, String genus) {
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
				map.put("GENUS", genus);
				for (int i = 1; i < numOfCol; i++) {
					Object key = rsmd.getColumnName(i).toUpperCase();
					Object value = rs.getObject(i);

					if (value != null) {
						if (!key.equals("OID") && !key.equals("WOID")) {
							map.put((String) key, (String) value);
						}
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

}
