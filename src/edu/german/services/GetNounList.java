package edu.german.services;

import java.util.List;
import java.util.concurrent.Callable;

import edu.german.sql.SqlQueryBuilder;
import edu.german.sql.QueryContractor;
import edu.german.words.model.Noun;

public class GetNounList implements Callable<List<Noun>> {

	public List<Noun> getList() {
		String sql = new SqlQueryBuilder().getAllWordFromSpecyficTable("das Substantiv");
		System.out.println(sql);
		
		QueryContractor qc = new QueryContractor();
		List<Noun> list = qc.getAllNounsList(sql);
		
		return list;
	}

	@Override
	public List<Noun> call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
