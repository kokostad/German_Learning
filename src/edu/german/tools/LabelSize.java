package edu.german.tools;

public class LabelSize {
	private int size;
	private int factor = 10;
	
	public LabelSize(String s) {
        char[] strArray = s.toCharArray();
        setSize((strArray.length) * factor);
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	
}
