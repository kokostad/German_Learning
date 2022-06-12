package edu.german.sentences;

import edu.german.sql.QueryContractor;
import edu.german.tools.GetQuery;

public class AddSenteceToDatabase {
	private GetQuery query;

	public AddSenteceToDatabase() {
		this.query = new GetQuery();
	}

	public boolean checkIfExist(String sentence, String type) {
		if (!sentence.isBlank() && !type.isBlank()) {
			String sql = query.getSql("check_sentence");
			QueryContractor qc = new QueryContractor();
			int id = qc.getId(sql, sentence, type);
			if (id > -1)
				return true;
		}
		return false;
	}

	public void addNewSentence(String sql, String sentence, String meaning, String genus) {
		if (!sentence.isBlank() && !meaning.isBlank() && !genus.isBlank())
			new QueryContractor().executeQuery(sql, sentence, meaning, genus);
	}

}
