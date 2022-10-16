package edu.german.words;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import edu.german.sql.SqlQuery;
import edu.german.sql.QueryContractor;
import edu.german.words.model.Noun;
import edu.german.words.model.Word;

public class WordPkg {
	private List<WordPkg> pkgList;

	public WordPkg() {
	}

	public WordPkg(String genus, String pattern) {
	}

	private List<Noun> prepareNounList() {
		String query = new SqlQuery().getSql("get_all_nouns");
		return new QueryContractor().getAllNounsList(query);
	}

	public WordPkg(String genus, int number) {
		if (genus.equals("Substantiv")) {
			List<Object> list = prepareWordList(genus, number);
//			List<Noun> list = getNounList(number);
//			for (Object object : list) {
//				Map map = new HashMap();
//				map.put("GENUS", genus);
//				Object mainWord = list.get(number);
//				Object woid = list.get(number);
//				Object oid = list.get(number);
//			}
		}
	}

	private List<Object> prepareWordList(String genus, int number) {
		List<Object> tmpList = new LinkedList<>();
//		System.out.println("Genus: " + genus + ",  Number" + number);

		return tmpList;
	}

	public List<WordPkg> getList() {
		List<WordPkg> list = new LinkedList<WordPkg>();

		return list;
	}

	public List<WordPkg> getList(String genus) {
		List<WordPkg> list = new LinkedList<WordPkg>();

		return list;
	}

//	public List<WordPkg> getList(int number) {
//		List<WordPkg> list = new LinkedList<WordPkg>();
//		String query = new GetSqlFromConfigFile("get_limit_nouns").getSql();
//
//		return list;
//	}

	public List<Noun> getNounList(int number) {
		String query = new SqlQuery().getSql("get_limit_" + "nouns");
//		List<Noun> list = new QueryContractor().getNounsList(query, number);

		// NOTICE you need make case when word is getting from specific table
//		for (Noun noun : list) {
//			String word = noun.getMainWord();
//			noun.prepareNoun(word);
//		}
		return new QueryContractor().getNounsList(query, number);
	}

	public List<Noun> getNounList() {
		String query = new SqlQuery().getSql("get_all_" + "nouns");
		List<Noun> list = new QueryContractor().getAllNounsList(query);
		return list;
	}

//	public void setWoid(int woid) {
//		// TODO Auto-generated method stub
//
//	}

}
