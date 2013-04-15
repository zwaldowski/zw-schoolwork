import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class ZachTests {

	@Test
	public void testFrequencyMap() {
		String myString = "this is an example for huffman encoding";
		Map<Character, Integer> mine = new HashMap<>();
		mine.put('t', 1);
		mine.put('h', 2);
		mine.put('i', 3);
		mine.put('s', 2);
		mine.put(' ', 6);
		mine.put('a', 3);
		mine.put('n', 4);
		mine.put('e', 3);
		mine.put('x', 1);
		mine.put('m', 2);
		mine.put('p', 1);
		mine.put('l', 1);
		mine.put('f', 3);
		mine.put('o', 2);
		mine.put('r', 1);
		mine.put('u', 1);
		mine.put('c', 1);
		mine.put('d', 1);
		mine.put('g', 1);
		Map<Character, Integer> freq = Huffman.buildFrequencyMap(myString);
		assertEquals(mine, freq);
	}

    static String roundTrip(String s) {
		Map<Character, Integer> freq = Huffman.buildFrequencyMap(s);
		Node tree = Huffman.buildHuffmanTree(freq);
		Map<Character, EncodedString> encodingMap = Huffman.buildEncodingMap(tree);
		EncodedString out = Huffman.encode(encodingMap, s);
		return Huffman.decode(tree, out);
    }

	@Test
	public void testSimple() {
		String s = "this is an example for huffman encoding";
		assertEquals(s, roundTrip(s));
	}

	@Test
	public void testChinese() {
		String s = "不，我不能说中国话。我为什么会呢？";
		assertEquals(s, roundTrip(s));
	}

	@Test
	public void testBacon() {
		String s = "ಠ_ಠ";
		assertEquals(s, roundTrip(s));
	}

	@Test
	public void testAlex() {
		String s = "🐼 🐙✨";
		assertEquals(s, roundTrip(s));
	}

}
