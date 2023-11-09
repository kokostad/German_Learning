package edu.german.io;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import edu.german.sql.QueryContractor;
import edu.german.sql.SqlQuery;

public class SentenceFromData {
	private List<Sentence> list;
	private Optional<String> optData;

	public record Sentence(String[] arr) {

		String getSentence() {
			return arr[0].toString();
		};

		String getMeaning() {
			return arr[1].toString();
		};

		String getType() {
			return arr[2].toString();
		};

		int getOid(String sentence, String meaninig) {
			String sql = new SqlQuery().getSql("check_sentence_meaning");
			return new QueryContractor().getId(sql, sentence, meaninig);
		};

		String getOIDAsString() {
			return String.valueOf(getOid(getSentence(), getMeaning()));
		};

		String[] getFullSentece() {
			return new String[] { getOIDAsString(), getSentence(), getMeaning(), getType() };
		};
	};

	public SentenceFromData(String data, String fileType, String firstInOrder) {
		// TODO change this metod
		list = new LinkedList<>();
//		data
//		.lines()
//		.map(r -> r.split(";"))
//		.map(s -> new Sentence(s))
//		.forEach(a -> list.add(a));
	}

	public SentenceFromData(Optional<String> optData) {
		this.optData = optData;
	}

	public boolean checkData() {
		return optData.isEmpty();
	}

	public List<Sentence> getList() {
		return list;
	}

	public List<String> getListFromData() {
		List<String> list = new LinkedList<>();
		for (String line : optData.get().split("\\n")) {
			list.add(line);
		}
		return list;
	}

	/*
	 * NOTICE for now i'll get only two fields, sentence and meaning it's need to do
	 * more than two parameters, type of sentence, key word and other
	 */
	public List<String[]> arrayList(List<String> listToExecute) {

		List<String[]> list = new LinkedList<>();
		listToExecute.forEach(s -> {
			String[] arr = s.split(";");
			int oid = getOid(arr[0], arr[1]);
			if (oid < 0) {
				list.add(arr);
			}
		});
		return list;
	}

	private int getOid(String sentence, String meaninig) {
		String sql = new SqlQuery().getSql("check_sentence_meaning");
		return new QueryContractor().getId(sql, sentence, meaninig);
	}

	public List<String[]> arrayListFromJSON(List<String> listFromData) {
		List<String[]> newList = new LinkedList<>();
		List<String[]> list = new SentenceFromData(optData).arrayList(listFromData);

		// NOTICE check if sentence exists in database
		list.forEach(var -> {
			if (!(var[1]).equals(null)) {
				if (getOid(var[0], var[1]) > 0) {
					newList.add(var);
				}
			}
		});

		return newList;
	}
}
