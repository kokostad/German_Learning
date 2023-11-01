package edu.german.services;

import java.util.List;

import edu.german.sentences.Sentence;
import edu.german.sql.QueryBuilder;
import edu.german.sql.QueryContractor;
import edu.german.sql.SqlQuery;
import edu.german.tools.MyProgressBar;

public class ExecutorSentenceIntoDB implements Runnable {
	private List<Sentence> list;
	private List<String> strlist;
	private MyProgressBar bar;
	private int sum;
	private boolean order;
	private int i = 0;

	public ExecutorSentenceIntoDB(List<Sentence> list, MyProgressBar bar, boolean order) {
		this.bar = bar;
		this.list = list;
		this.order = order;
		sum = list.size();
	}

	@Override
	public void run() {
		addToRepo(list);
	}

	private void addToRepo(List<Sentence> list) {
		if (!order) {
			for (Sentence sentence : list) {
				i += 1;
				if (!checkSentence(sentence))
					putIntoRepository(makeArrayFromSentence(sentence));

				fillBar(i);
			}
		}

		else {
			for (Sentence sentence : list) {
				i += 1;
				if (!checkSentence(sentence))
					putIntoRepository(makeArrayFromSentence(sentence));

				fillBar(i);
			}
		}
	}

	private boolean checkSentence(Sentence sentence) {
		String sql = new SqlQuery().getSql("check_sentence_meaning");
		return new QueryContractor().executeQuery(sql, sentence.getSentence(), sentence.getMeaning());
	}

	private void putIntoRepository(String[] array) {
		String sql = new QueryBuilder().addNewSentenceToRepository(array);
		new QueryContractor().executeQuery(sql);
		System.out.println(sql);
	}

	private String[] makeArrayFromSentence(Sentence sentence) {
		if (sentence.getCategory() != null)
			return new String[] { sentence.getWord(), sentence.getMeaning(), sentence.getCategory() };
		else
			return new String[] { sentence.getWord(), sentence.getMeaning() };
	}

	private void fillBar(int i) {
		int result = ((i * 100) / sum);
		bar.fill(result);
		bar.showProgress(result);
		if (result == 100 || i == sum)
			bar.done();
	}

}
