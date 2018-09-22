import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Random;

public class EfficientWordMarkov extends WordMarkovModel{
	HashMap<WordGram,ArrayList<String>> myMap;
	
	public EfficientWordMarkov() {
		this(2);
	}
	
	public EfficientWordMarkov(int order){
		myOrder = order;
		myRandom = new Random(RANDOM_SEED);
	}

	@Override
	public void setTraining(String text){
		myWords = text.split("\\s+");
		myMap = new HashMap<WordGram,ArrayList<String>>();
		WordGram currentWordGram = new WordGram(myWords, 0, myOrder);
		for (int k = 0; k <= myWords.length - myOrder; k++) {
			if (!myMap.containsKey(currentWordGram)){
				myMap.put(currentWordGram, new ArrayList<String>());
			}
			if (k == myWords.length - myOrder) {
				myMap.get(currentWordGram).add(PSEUDO_EOS);
			}
			else {
				myMap.get(currentWordGram).add(myWords[k + myOrder]);
				currentWordGram = currentWordGram.shiftAdd(myWords[k + myOrder]);
			}
		}
			
		}
	
	
	@Override
	public ArrayList<String> getFollows(WordGram kGram) {
		if (!myMap.containsKey(kGram)) {
			throw new NoSuchElementException("map does not contain key");
		}
		return myMap.get(kGram);
	}
}
