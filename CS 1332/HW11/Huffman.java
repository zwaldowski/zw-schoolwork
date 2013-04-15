import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Iterator;

public class Huffman {

	/**
	 * Builds a frequency map of characters for the given string.
	 * 
	 * This should just be the count of each character.
	 * 
	 * @param s
	 * @return
	 */
	public static Map<Character, Integer> buildFrequencyMap(String s) {
		HashMap<Character, Integer> ret = new HashMap<>();
		for (char key : s.toCharArray()) {
			Integer prev = ret.remove(key);
			Integer next;
			if (prev != null) {
				next = prev+1;
			} else {
				next = 1;
			}
			ret.put(key, next);
		}
		return ret;
	}
	
	/**
	 * Build the Huffman tree using the frequencies given.
	 * 
	 * The frequency map will not necessarily come from the buildFrequencyMap() method.
	 * 
	 * @param freq
	 * @return
	 */
	public static Node buildHuffmanTree(Map<Character, Integer> freq) {
		PriorityQueue<Node> que = new PriorityQueue<Node>(); // QUE?!

		for (Map.Entry<Character, Integer> pair : freq.entrySet()) {
			que.offer(new Node(pair.getKey(), pair.getValue()));
		}
		
        while (que.size() > 1) {
            Node a = que.poll();
            Node b = que.poll();
            que.offer(new Node(a, b));
        }
		
		return que.poll();
	}
	
 	/**
 	 * Traverse the tree and extract the encoding for each character in the tree
 	 * 
 	 * The tree provided will be a valid Huffman tree but may not come from the buildHuffmanTree() method.
 	 * 
 	 * @param tree
 	 * @return
 	 */
 	public static Map<Character, EncodedString> buildEncodingMap(Node tree) {
 		if (tree.left == null && tree.right == null) { // Howdy, stupid
 			return null;
 		}
 		
 		HashMap<Character, EncodedString> ret = new HashMap<>();
 		
 		buildEncodingRecursive(tree, new EncodedString(), ret);
 		
 		return ret;
 	}
 	
 	private static void buildEncodingRecursive(Node node, EncodedString currEnc, Map<Character, EncodedString> outMap) {
 		if (node.left == null && node.right == null) {
 			outMap.put(node.character, currEnc);
 		}
		
		if (node.left != null) {
			// poor man's copy
			EncodedString nextEnc = new EncodedString();
			nextEnc.concat(currEnc);
			nextEnc.zero();
			buildEncodingRecursive(node.left, nextEnc, outMap);
		}

		if (node.right != null) {
			// herpity derpity
			EncodedString nextEnc = new EncodedString();
			nextEnc.concat(currEnc);
			nextEnc.one();
			buildEncodingRecursive(node.right, nextEnc, outMap);
 		}
 	}
	
	/**
	 * Encode each character in the string using the map provided.
	 * 
	 * If a character in the string doesn't exist in the map ignore it.
	 * 
	 * The encoding map may not necessarily come from the buildEncodingMap() method, but will be correct
	 * for the tree given to decode() when decoding this method's output.
	 * 
	 * @param encodingMap
	 * @param s
	 * @return
	 */
	public static EncodedString encode(Map<Character, EncodedString> encodingMap, String s) {
		EncodedString ret = new EncodedString();

		for (char key : s.toCharArray()) {
			EncodedString forThisChar = encodingMap.get(key);
			if (forThisChar != null) {
				ret.concat(forThisChar);
			}
		}
		
		return ret;
	}
	
	/**
	 * Decode the encoded string using the tree provided.
	 * 
	 * The encoded string may not necessarily come from encode, but will be a valid string for the given tree.
	 * 
	 * (tip: use StringBuilder to make this method faster -- concatenating strings is SLOW)
	 * 
	 * @param tree
	 * @param es
	 * @return
	 */
	public static String decode(Node tree, EncodedString es) {
		StringBuilder ret = new StringBuilder();
		
		Node cur = tree;
		Iterator<Byte> itor = es.iterator();
		while (itor.hasNext()) {
			
			Byte bit = itor.next();
			if (bit == 0) {
				cur = cur.left;
			} else {
				cur = cur.right;
			}

			if (cur.left == null && cur.right == null) {
				ret.append(cur.character);
				cur = tree;
			}
		}
		
		return ret.toString();
	}
}
