import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class StringTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Starting Test");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Finished Test");
	}

	@Test
	public void testCharTable() {
		int[] map = StringSearches.buildCharTable("aardvark");
		assertEquals(map['a'],2);
		assertEquals(map['r'],1);
		assertEquals(map['d'],4);
		assertEquals(map['v'],3);
		assertEquals(map['k'],1);
		assertEquals(map['z'],8);
	}
	
	
	
	//boyerMoore
	@Test
	public void testMoore() {
		System.out.println("Testing Moore: ");
		String needle = "aardvark";
		String haystack = "anteaters are aardvarks";
		int[] bMoore = StringSearches.boyerMoore(needle, haystack);
		int[] test = {14};
		assertArrayEquals(test, bMoore);
	}
	@Test
	public void testMoore2() {
		String haystack = "white people: gross Hi.: fucking asshole says he will ask one question on this topic that isnt even in any of our chapters Hi.: he makes the entire part 2 of the test on that shit";
		String needle = "fucking asshole";
				
		int[] bMoore = StringSearches.boyerMoore(needle, haystack);
		
		String s = haystack.substring(bMoore[0],bMoore[0] + needle.length());
		assertEquals(s,"fucking asshole");
	}
	@Test
	public void testMoore3() {
		String needle = "aardvark";
		String haystack = "crush kkyle with aardvarks";
		int[] bMoore = StringSearches.boyerMoore(needle, haystack);
		int[] test = {17};
		assertArrayEquals(test, bMoore);
	}
	@Test
	public void testMoore4() {
		String needle = "aaa";
		String haystack = "aaaaaa";
		int[] bMoore = StringSearches.boyerMoore(needle, haystack);
		String s = haystack.substring(bMoore[0],bMoore[0] + needle.length());
		assertEquals(4, bMoore.length);
		assertEquals(needle, s);
	}
	
	@Test
	public void testMoore5() {
		String haystack = "abababa";
		String needle = "aba";
	
		int[] bMoore = StringSearches.boyerMoore(needle, haystack);
		String s = haystack.substring(bMoore[0],bMoore[0] + needle.length());
		assertEquals(3, bMoore.length);
		assertEquals(s,needle);
	}

	@Test
	public void testMoore6() {
		String haystack = "abcdabcabcdabcfabcdabcabe";
		String needle = "abcdabcabe";
	
		int[] bMoore = StringSearches.boyerMoore(needle, haystack);
		String s = haystack.substring(bMoore[0],bMoore[0] + needle.length());
		assertEquals(1, bMoore.length);
		assertEquals(s,needle);
	}
	@Test
	public void testMoore7() {
		String needle = "";
		String haystack = "aaaaaa";
		int[] bRP = StringSearches.boyerMoore(needle, haystack);
		assertEquals(0, bRP.length);
		bRP = StringSearches.boyerMoore(null,null);
		assertEquals(0, bRP.length);
		
	}
	
	//Test KMP
	
	@Test
	public void buildTest() {
		int[] map = StringSearches.buildTable("abcdabd");
		assertEquals(-1, map[0]);
		assertEquals(0, map[1]);
		assertEquals(0, map[2]);
		assertEquals(0, map[3]);
		assertEquals(0, map[4]);
		assertEquals(1, map[5]);
		assertEquals(2, map[6]);
	}
	
	@Test
	public void buildTest2() {
		int[] map = StringSearches.buildTable("abcdabcabe");
		assertEquals(3, map[7]);
		assertEquals(1, map[8]);
		assertEquals(1, map[5]);
		assertEquals(2, map[6]);
		assertEquals(2, map[9]);
	}
	
	
	@Test
	public void testKMP() {
		System.out.println("Testing KMP: ");
		
		String needle = "aaa";
		String haystack = "aaaaaa";
		
		int[] bMoore = StringSearches.kmp(needle, haystack);
		String s = haystack.substring(bMoore[0],bMoore[0] + needle.length());
		
		assertEquals(4, bMoore.length);
		assertEquals(needle, s);
	}
	
	@Test
	public void testKMP2() {
		String needle = "aardvark";
		String haystack = "crush kkyle with aardvarks";
		
		int[] bMoore = StringSearches.kmp(needle, haystack);
		String s = haystack.substring(bMoore[0],bMoore[0] + needle.length());
		
		assertEquals(1, bMoore.length);
		assertEquals(needle, s);
	}
	
	
	@Test
	public void testKMP3() {
		String haystack = "white people: gross Hi.: fucking asshole says he will ask one question on this topic that isnt even in any of our chapters Hi.: he makes the entire part 2 of the test on that shit";
		String needle = "fucking asshole";
				
		int[] bMoore = StringSearches.kmp(needle, haystack);
		String s = haystack.substring(bMoore[0],bMoore[0] + needle.length());
		
		assertEquals(1, bMoore.length);
		assertEquals(s,"fucking asshole");
	}
	
	@Test
	public void testKMP4() {
		String haystack = "www";
		String needle = "w";
	
		int[] bMoore = StringSearches.kmp(needle, haystack);
		String s = haystack.substring(bMoore[0],bMoore[0] + needle.length());
		
		assertEquals(3, bMoore.length);
		assertEquals(s,"w");
	}
	
	@Test
	public void testKMP5() {
		String haystack = "abababa";
		String needle = "aba";
	
		int[] bMoore = StringSearches.kmp(needle, haystack);
		String s = haystack.substring(bMoore[0],bMoore[0] + needle.length());
		
		assertEquals(3, bMoore.length);	
		assertEquals(s,needle);
	}
	
	@Test
	public void testKMP6() {
		String haystack = "abcdabcabcdabcfabcdabcabe";
		String needle = "abcdabcabe";
	
		int[] bMoore = StringSearches.kmp(needle, haystack);
		String s = haystack.substring(bMoore[0],bMoore[0] + needle.length());
		
		assertEquals(1, bMoore.length);
		assertEquals(s,needle);
	}
	
	@Test
	public void testKMP7() {
		String needle = "";
		String haystack = "aaaaaa";
		int[] bRP = StringSearches.kmp(needle, haystack);
		assertEquals(0, bRP.length);
		bRP = StringSearches.kmp(null,null);
		assertEquals(0, bRP.length);
		
	}
	
	@Test
	public void testHashSimple() { //Fuckin numbers
		int i = StringSearches.hash("appl");
		int j = StringSearches.hash("pple");
		assertEquals(1802433388, i);
		assertEquals(-1403282091, j);
	}
	
	//rabinKarp
	
	@Test
	public void testRPHash() { //Fuckin numbers
		int i  = StringSearches.hash("ap");
		int j = StringSearches.hash("pe");
		
		int k = StringSearches.hash("fucking numbers");
		int l = StringSearches.hash("ucking numbersa");
		
		assertEquals(j,StringSearches.updateHash(i , StringSearches.basePow(1), 'e', 'a'));
		assertEquals(l,StringSearches.updateHash(k , StringSearches.basePow("ucking numbersa".length()-1), 'a', 'f'));
	}
	
	@Test
	public void testRP() {
		System.out.println("Testing RP: ");
		
		String needle = "aaa";
		String haystack = "aaaaaa";
		
		int[] bMoore = StringSearches.rabinKarp(needle, haystack);
		String s = haystack.substring(bMoore[0],bMoore[0] + needle.length());
		
		assertEquals(4, bMoore.length);
		assertEquals(needle, s);
	}
	
	@Test
	public void testRP2() {
		String haystack = "white people: gross Hi.: fucking asshole says he will ask one question on this topic that isnt even in any of our chapters Hi.: he makes the entire part 2 of the test on that shit";
		String needle = "fucking asshole";
				
		int[] bMoore = StringSearches.rabinKarp(needle, haystack);
		
		String s = haystack.substring(bMoore[0],bMoore[0] + needle.length());
		assertEquals(s,"fucking asshole");
	}
	
	@Test
	public void testRP3() {
		String needle = "aardvark";
		String haystack = "crush kkyle with aardvarks";
		int[] bRP = StringSearches.rabinKarp(needle, haystack);
		int[] test = {17};
		assertArrayEquals(test, bRP);
	}
	@Test
	public void testRP4() {
		String needle = "";
		String haystack = "aaaaaa";
		int[] bRP = StringSearches.rabinKarp(needle, haystack);
		//String s = haystack.substring(bRP[0],bRP[0] + needle.length());
		assertEquals(0, bRP.length);
		//assertEquals(needle, s);
	}
	@Test
	public void testRP5() {
		String haystack = "abababa";
		String needle = "aba";
	
		int[] bRP = StringSearches.rabinKarp(needle, haystack);
		String s = haystack.substring(bRP[0],bRP[0] + needle.length());
		assertEquals(3, bRP.length);
		assertEquals(s,needle);
	}
	
	@Test
	public void testRP6() {
		String haystack = "abcdabcabcdabcfabcdabcabe";
		String needle = "abcdabcabe";
	
		int[] bRP = StringSearches.rabinKarp(needle, haystack);
		String s = haystack.substring(bRP[0],bRP[0] + needle.length());
		assertEquals(1, bRP.length);
		assertEquals(s,needle);
	}

}
