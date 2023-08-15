package edu.german.services;

import java.util.ArrayList;
import java.util.List;

import edu.german.sentences.Sentence;
import edu.german.sql.QueryBuilder;
import edu.german.sql.QueryContractor;
import edu.german.tools.MyProgressBar;

public class ExecutorAddSentenceListToRepository implements Runnable {
	private List<String[]> arrList;
	private List<Sentence> list;
	private int sum;
	private boolean order;
	private MyProgressBar bar;

	public ExecutorAddSentenceListToRepository(List<Sentence> list, MyProgressBar bar, boolean order) {
		this.bar = bar;
		this.list = list;
		this.arrList = prepareArray(list);
		this.order = order;
		sum = list.size();
	}

	@Override
	public void run() {
//		addListToRepo(arrList);
	}

	private void addListToRepo(List<Sentence> list) {
		// TODO Auto-generated method stub

	}

	private List<String[]> prepareArray(List<Sentence> list) {

		list.forEach(sentence -> {
			arrList.add(checkSenence(sentence));
		});

//		List<String[]> al = new ArrayList<>(sum);

		return arrList;
	}

//	private void addListToRepo(List<String[]> list) {
//		int i = 0;
//		if (!order) {
//			for (String[] array : list) {
//				i += 1;
//				if (!makeSentenceFromArray(array[0].toString(), array[1].toString()))
//					putSentenceToRepository(array);
//
//				fillBar(i);
//			}
//		}
//
//		else {
//			for (String[] array : list) {
//				i += 1;
//				if (!makeSentenceFromArray(array[1].toString(), array[0].toString()))
//					putSentenceToRepository(array);
//
//				fillBar(i);
//			}
//		}
//	}

	private String[] checkSenence(Sentence sentence) {
		if (sentence.getOid() > 0) {
			return makeArrayFromSentence(sentence);
		}

		return null;
	}

	private String[] makeArrayFromSentence(Sentence sentence) {
		String[] arr = {sentence.getWord(), sentence.getMeaning(), sentence.getCategory()};
		return arr;
	}

	private void putSentenceToRepository(String[] sentence) {
		String sql = new QueryBuilder().addNewSentenceToRepository(sentence);
		new QueryContractor().executeQuery(sql);
	}

	private boolean makeSentenceFromArray(String sentece, String meaning) {
		Sentence sentence = new Sentence().getSentenceFromRepository(sentece, meaning);
		if (sentence.getOid() > 0)
			return true;

		return false;
	}

	private void fillBar(int i) {
		int result = ((i * 100) / sum);
		bar.fill(result);
		bar.showProgress(result);
		if (result == 100 || i == sum)
			bar.done();
	}

}
