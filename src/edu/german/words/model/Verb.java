package edu.german.words.model;

public class Verb implements WordModel {
	private String genus = "das Verb";
	// TODO make Builder inner class 
	
	public Verb() {
	}
	

	public String getGenus() {
		return genus;
	}

	@Override
	public int getWoid() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getOid() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getMainWord() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getWord() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMeaning() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getMeanings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setWoid(int woid) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setOid(int oid) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMainWord(String word) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMeaning(String meaning) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMeanings(String[] meanings) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setWord(String word) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isExist(String word, String genus) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void putIntoRepository(String word, String meaning, String genus) {
		// TODO Auto-generated method stub

	}

}
