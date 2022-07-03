package edu.german.services;

import java.util.List;

import edu.german.sql.QueryBuilder;
import edu.german.sql.QueryContractor;
import edu.german.words.model.Noun;

public class GetNounList {

	public List<Noun> getList() {
		String sql = new QueryBuilder().getAllWordFromSpecyficTable("das Substantiv");
		System.out.println(sql);
		
		QueryContractor qc = new QueryContractor();
		List<Noun> list = qc.getAllNounsList(sql);
		
		return list;
	}

}
