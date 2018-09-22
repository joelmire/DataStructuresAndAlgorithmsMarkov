import org.junit.*;
import java.util.*;

import static org.junit.Assert.*;

public class WordGramTester {

	private WordGram[] myGrams;

	@Before
	public void setUp(){
		String str = "aa bb cc aa bb cc aa bb cc aa bb dd ee ff gg hh ii jj";
		String[] array = str.split("\\s+");
		myGrams= new WordGram[array.length-2];
		for(int k=0; k < array.length-2; k++){
			myGrams[k] = new WordGram(array,k,3);
		}
	}

	@Test
	public void testHashEquals(){
		assertEquals("hash fail on equals 0,3",myGrams[0].hashCode(),myGrams[3].hashCode());
		assertEquals("hash fail on equals 0,3",myGrams[0].hashCode(),myGrams[6].hashCode());
		assertEquals("hash fail on equals 0,3",myGrams[1].hashCode(),myGrams[4].hashCode());
		assertEquals("hash fail on equals 0,3",myGrams[2].hashCode(),myGrams[8].hashCode());
		assertEquals("hash fail on equals 0,3",myGrams[2].hashCode(),myGrams[5].hashCode());
	}

	@Test
	public void testEquals(){

		assertEquals("eq fail on 0,3",myGrams[0].equals(myGrams[3]),true);
		assertEquals("eq fail on 0,6",myGrams[0].equals(myGrams[6]),true);
		assertEquals("eq fail on 1,4",myGrams[1].equals(myGrams[4]),true);
		assertEquals("eq fail on 2,5",myGrams[2].equals(myGrams[5]),true);
		assertEquals("eq fail on 2,8",myGrams[2].equals(myGrams[8]),true);
		assertEquals("eq fail on 0,2",myGrams[0].equals(myGrams[2]),false);
		assertEquals("eq fail on 0,4",myGrams[0].equals(myGrams[2]),false);
		assertEquals("eq fail on 2,3",myGrams[2].equals(myGrams[3]),false);
		assertEquals("eq fail no 2,6",myGrams[2].equals(myGrams[6]),false);
		assertEquals("eq fail no 7,8",myGrams[7].equals(myGrams[8]),false);
	}

	@Test
	public void testHash(){
		Set<Integer> set = new HashSet<Integer>();
		for(WordGram w : myGrams) {
			set.add(w.hashCode());
		}

		assertTrue("hash code test", set.size() > 9);
	}
	
	@Test
	public void testCompare(){
		String[] words = {"apple", "zebra", "mongoose", "hat"};
		WordGram a = new WordGram(words,0,4);
		WordGram b = new WordGram(words,0,4);
		WordGram a2 = new WordGram(words,0,3);
		WordGram b2 = new WordGram(words,2,0);
		
		assertEquals("comp fail self",a.compareTo(a) == 0, true);
		assertEquals("comp fail copy",a.compareTo(b) == 0, true);
		assertEquals("fail sub", a2.compareTo(a) < 0, true);
		assertEquals("fail super",a.compareTo(a2) > 0, true);
		assertEquals("fail empty",b2.compareTo(a2) < 0, true);
	}
	
	@Test
	public void testShift() {
		String[] words = {"apple", "zebra", "mongoose", "hat","cat"};
		WordGram a = new WordGram(words,0,4);
		WordGram b = new WordGram(words,1,4);
		WordGram as = a.shiftAdd("cat");
		assertEquals("shift add",as.equals(b),true);
		assertEquals("shift add length",as.length() == a.length(),true);
	}
	
	@Test
	public void testToString() {
		String[] words = {"red", "blue", "yellow", "blue", "green"};   //add 2 more
		WordGram a = new WordGram(words, 0, 3);
		String b = "red blue yellow";
		String as = a.toString();
		assertEquals("test toString", as.equals(b), true);
		
		WordGram c = new WordGram(words, 0, 1);
		String d = "red";
		String cs = c.toString();
		assertEquals("test toString single item", cs.equals(d), true);
		
		String[] words2 = {"red", ""};
		WordGram a2 = new WordGram(words2, 0, 2);
		String b2 = new String("red");
		String as2 = a2.toString();
		assertEquals("test toString empty String", as2.equals(b2), true);
		
		String[] words3 ={"animal", "     ", " d", "blue", "green"}; 
		WordGram a3 = new WordGram(words3, 0, 3);
		String b3 = "animal        d";
		String as3 = a3.toString();
		assertEquals("test toString string of spaces", as3.equals(b3), true);
		
		//one other condition to consider: cases in which the starting word 
		//starts with a space or the ending word ends 
		//with a white space. see the trim method in wordGram
	}
	
	 @Test
	 public void testShiftAdd() {
		String[] words = {"red", "blue", "yellow", "blue", "green"};
		WordGram a = new WordGram(words,0,3);
		WordGram b = new WordGram(words,1,3);
		WordGram as = a.shiftAdd("blue");
		assertEquals("shift add",as.equals(b),true);
		
		assertEquals("shift add length",as.length() == a.length(),true);
		
		String[] words2 = {"red", "red", "red"};
		WordGram c = new WordGram(words2, 1, 2);
		WordGram cs = c.shiftAdd("red");
		assertEquals("shift add same string",cs.equals(c),true);
		
		assertEquals("shift add hashcode from above", c.hashCode() == cs.hashCode(), true);
		
	 }
	 
	 

}
