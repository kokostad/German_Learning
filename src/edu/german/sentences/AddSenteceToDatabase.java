package edu.german.sentences;

import java.util.HashMap;
import java.util.List;

import edu.german.words.model.Word;

public class AddSenteceToDatabase {

	public AddSenteceToDatabase() {
	}

	public void addList(List<HashMap<String, String>> mapList) {
		for (HashMap<String, String> map : mapList) {
			String sentence = null;
			String meaning = null;
			String genus = null;
			String mode = null;
			String tens = null;
			String word = null;
			String wordMeaning = null;
			String wordGenus = null;

			// Sentence,Sentence_meaning,Sentence_kind,Sentence_tribe,Tens,Word,Word_meaning,Word_kind
			if (map.containsKey("SENTENCE"))
				sentence = map.get("SENTENCE");
			if (map.containsKey("SENTENCE_MEANING"))
				meaning = map.get("SENTENCE_MEANING");
			if (map.containsKey("SENTENCE_KIND"))
				genus = map.get("SENTENCE_KIND");
			if (map.containsKey("SENTENCE_TRIBE"))
				mode = map.get("SENTENCE_TRIBE");
			if (map.containsKey("TENS"))
				tens = map.get("TENS");
			if (map.containsKey("WORD"))
				word = map.get("WORD");
			if (map.containsKey("WORD_MEANING"))
				wordMeaning = map.get("WORD_MEANING");
			if (map.containsKey("WORD_KIND"))
				wordGenus = map.get("WORD_KIND");

			/*
			 * TODO check if word exist, if no add to repository
			 */
			Word newWord = new Word(word, wordMeaning, wordGenus);
			// TODO improve this method
			int woid1 = newWord.getWoid(word, wordGenus);
			int woid = newWord.getWoid();

			if (woid < 0) {
				newWord.putIntoRepository(word, wordMeaning, wordGenus);
//				woid = newWord.getWoid(word, wordGenus);
			}

			// sentence, meaning, type, category, tens, word, woid
			Sentence newSentence = new Sentence.Builder()
					.withSentence(sentence)
					.withMening(meaning)
					.withType(genus)
					.withCategory(mode)
					.withTens(tens)
					.withWord(word)
					.build();

			if (woid > 0)
				newSentence.addToRepository(woid);

		}
	}
}
