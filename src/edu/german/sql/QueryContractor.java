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
import edu.german.tools.PrepareArrayFromString;
import edu.german.words.model.Noun;
import edu.german.words.model.Word;

public class QueryContractor {
	private DbConnect dbc;
	private Connection con;

	public void loadDriver() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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

	public void closeConnection() {
		dbc.closeConnection(con);
	}

	public int getId(String sql, String word) {
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, word);

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

	public int getId(String sql, String word, String genus) {
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, word);
			ps.setString(2, genus);

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
				noun.setNoun(rs.getString("word").split(" "));
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
				noun.setNoun(rs.getString("word").split(" "));
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
				noun.setNoun(rs.getString("word").split(" "));
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

	public void addVerb(String sql, String modus, int woid, String tens, String ich, String ja, String du, String ty,
			String erSieEs, String onOnaOno, String wir, String my, String ihr, String wy, String sieSie,
			String oniPanstwo) {

		int i = -1;
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

		int i = -1;
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

	public void addSentenceToDatabase(String sql, String sentence, String meaning, String category, String tens,
			String word) {
		loadDriver();
		dbc = new DbConnect();
		con = dbc.getConnection();

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, sentence);
			ps.setString(2, meaning);
			ps.setString(3, category);
			ps.setString(4, tens);
			ps.setString(5, word);

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
				noun.setNoun(rs.getString("word").split(" "));
				noun.setWord(rs.getString("word"));
//				String[] article = rs.getString("word").split(" ", 1);
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

}
