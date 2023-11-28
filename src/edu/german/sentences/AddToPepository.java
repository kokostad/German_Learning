package edu.german.sentences;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.german.words.model.Word;

public class AddToPepository {

	public AddToPepository() {
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

			// NOTICE Sentence,Sentence_meaning,Sentence_kind,Sentence_tribe,Tens,Word,Word_meaning,Word_kind
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
			if (map.containsKey("WORD")) {
				Map<String, String> m = new HashMap<>();
				word = map.get("WORD");
				m.put("WORD", word);
				if (map.containsKey("WORD_MEANING")) {
					wordMeaning = map.get("WORD_MEANING");
					m.put("MEANING", wordMeaning);
				}
				if (map.containsKey("WORD_KIND")) {
					wordGenus = map.get("WORD_KIND");
					m.put("GENUS", wordGenus);
				}

				Word newWord = new Word(word, wordMeaning, wordGenus);
				int id = newWord.getOid();
				if (id < 0) {
					newWord.putIntoRepository(word, wordMeaning, wordGenus);
				}
			}

			// NOTICE sentence, meaning, type, category, tens, word, woid
			Sentence newSentence = new Sentence.Builder()
					.withSentence(sentence)
					.withMening(meaning)
					.withType(genus)
					.withCategory(mode)
					.withTens(tens)
					.withWord(word)
					.build();

			if (newSentence.getOid() < 0)
				newSentence.addToRepository();
		}
	}
}
