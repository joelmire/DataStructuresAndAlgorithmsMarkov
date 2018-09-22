import java.util.*;

public class EfficientMarkov extends MarkovModel{
	HashMap<String,ArrayList<String>> myMap;
	
	public EfficientMarkov(int order) {
		 super(order);
		 myMap = new HashMap<String,ArrayList<String>>();
	}
	
	@Override
	public void setTraining(String text) {
		// stores the text parameter, clears the map, and then initializes the map
		myText = text;
		myMap = new HashMap<String,ArrayList<String>>();
		String lastKey = myText.substring(myText.length() - myOrder, myText.length());
	
		for (int k=0; k < myText.length() - myOrder; k++) {
			ArrayList<String> tempArrayList = new ArrayList<>();
			String currentKGram = myText.substring(k, k + myOrder);
			if (!myMap.containsKey(currentKGram))
				myMap.put(currentKGram, tempArrayList);
			myMap.get(currentKGram).add(myText.substring(k + myOrder, k + myOrder + 1)); 
		}
		
		if (!myMap.containsKey(lastKey)) {
			ArrayList<String> tempArrayList2 = new ArrayList<>();
			tempArrayList2.add(PSEUDO_EOS);
			myMap.put(lastKey, tempArrayList2);
		}
	}


	@Override
	public ArrayList<String> getFollows(String key){
		if (!myMap.containsKey(key)) {
			throw new NoSuchElementException("map does not contain key");
		}
		return myMap.get(key);
	}
}
