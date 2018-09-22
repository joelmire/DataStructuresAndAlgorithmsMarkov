import java.util.Arrays;

public class WordGram implements Comparable<WordGram>{
	
	private int myHash;
	private String[] myWords;
	
	public WordGram(String[] words, int index, int size) {
		myWords = new String[size];
		for (int k = 0; k < size; k++) {
			myWords[k] = words[index + k];
		}
		myHash = Arrays.toString(myWords).hashCode();
	}
	
	// 48 the following can either be a char or a pseudo, if there are less words, the probability of a pseudo increases

	
	@Override
	public int hashCode() {
		return myHash;
	}
	
	@Override
	public String toString() {					//rewrite this in one line
		String s = "";
		for (String word : myWords)
			s += word + " ";
		s = s.trim();
		return s;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null || ! (other instanceof WordGram))
			return false;
		WordGram wg = (WordGram) other;
		if (wg.hashCode() != this.hashCode())
			return false;
		return true;
	}
	
	@Override
	public int compareTo(WordGram wg) {
		String thisString = this.toString();
		String wgString = wg.toString();
		return thisString.compareTo(wgString);
	}
	
	public int length() {
		return myWords.length;
	}
	
	public WordGram shiftAdd(String last) {
		String[] shifted = new String[myWords.length];
		for (int k = 1; k < myWords.length; k++)
			shifted[k - 1] = myWords[k];
		shifted[shifted.length - 1] = last;
		return new WordGram(shifted, 0, shifted.length);
	}
}