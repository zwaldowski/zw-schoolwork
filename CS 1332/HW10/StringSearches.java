import java.util.Collection;
import java.util.LinkedList;
import java.util.Iterator;

public class StringSearches {
	
	private static int[] buildResults(Collection<Integer> matches) {
		int[] ret = new int[matches.size()];
		Iterator<Integer> iterator = matches.iterator();
	    for (int i = 0; i < ret.length; i++) {
	        ret[i] = iterator.next().intValue();
	    }
	    return ret;
	}

	/**
	 * Return a table for use with Boyer-Moore.
	 * 
	 * map[c] = the length - 1 - last index of c in the needle
	 * map[c] = the length if c doesn't appear in the needle
	 * 
	 * the map should have an entry for every character, 0 to Character.MAX_VALUE
	 */
	public static int[] buildCharTable(String needle) {
		int[] map = new int[Character.MAX_VALUE + 1];
		final int length = needle.length();
		
        for (int i = 0; i < map.length; i++)
        	map[i] = length;
        
        for (int i = length - 1; i >= 0; i--) {
        	char c = needle.charAt(i);
        	if (map[c] == length) map[c] = Math.max(length - 1 - i, 1);
        }

		return map;
	}

	/**
	 * Run Boyer-Moore on the given strings, looking for needle in haystack.
	 * Return an array of the indices of the occurrence of the needle in the
	 * haystack. 
	 * 
	 * If there are matches that start at index 4, 7, and 9 in the
	 * haystack, return an array containing only 4, 7, and 9. If there are no
	 * matches return an empty array, new int[0]
	 * 
	 * Running time matters, you will not get full credit if it is not
	 * implemented correctly
	 * 
	 * 
	 */
	public static int[] boyerMoore(String needle, String haystack) {
		if (needle == null || haystack == null || needle.length() == 0 || haystack.length() == 0) {
			return new int[0];
		}
		
		LinkedList<Integer> matches = new LinkedList<Integer>();
		
		int[] map = buildCharTable(needle);
		int hLength = haystack.length();
		int nLength = needle.length();
		
		int alignedIdx = 0;
		while (alignedIdx + (nLength - 1) < hLength) {
			for (int needleIdx = nLength - 1; needleIdx >= 0; needleIdx--) {
				int haystackIdx = alignedIdx + needleIdx;
				if (haystackIdx >= hLength) break;
				
				char haystackChar = haystack.charAt(haystackIdx);
				char needleChar = needle.charAt(needleIdx);
				if (haystackChar != needleChar) {
					alignedIdx += map[haystackChar];
					break;
				} else if (needleIdx == 0) {
					matches.add(alignedIdx);
					alignedIdx++;
				}
			}
		}
		
		return buildResults(matches);
	}

	/**
	 * Return a table for use with KMP. In this table, table[i] is the length of
	 * the longest possible prefix that matches a proper suffix in the string
	 * needle.substring(0, i)
	 */
	public static int[] buildTable(String needle) {
		final int length = needle.length();
		int[] map = new int[length];
		
		int pos = 2, cnd = 0;
		map[0] = -1;
		if (length > 1) map[1] = -0;

		while (pos < length) {
			if (needle.charAt(pos - 1) == needle.charAt(cnd)) {
				cnd++;
				map[pos] = cnd;
				pos++;
			} else if (cnd > 0) {
				cnd = map[cnd];
			} else {
				map[pos] = 0;
				pos++;
			}
		}

		return map;
	}

	/**
	 * Run Knuth-Morris-Pratt on the given strings, looking for needle in
	 * haystack. Return an array of the indices of the occurrence of the needle
	 * in the haystack.
	 * 
	 * If there are matches that start at index 4, 7, and 9 in the
	 * haystack, return an array containing only 4, 7, and 9. If there are no
	 * matches return an empty array, new int[0]
	 */
	public static int[] kmp(String needle, String haystack) {
		if (needle == null || haystack == null || needle.length() == 0 || haystack.length() == 0) {
			return new int[0];
		}
		
		LinkedList<Integer> matches = new LinkedList<Integer>();
		
		int[] table = buildTable(needle);
		int hLength = haystack.length();
		int nLength = needle.length();
		
		int i = 0, j = 0;
		while (i < hLength) {
			while (haystack.charAt(i) != needle.charAt(j)) {
				if (j == 0) {
					i++;
					if (i >= hLength) break;
				} else {
					j = table[j];
				}
			}
			
			if (j == nLength - 1) {
				int match = i - j;
				matches.add(match);
				i = match;
				j = -1;
			}

			i++;
			j++;
		}
		
		return buildResults(matches);
	}

	// This is the base you should use, don't change it
	public static final int BASE = 1332;
	
	public static int pow(int base, int exp) {
		int pow = 1;
		for (; exp > 0; exp--) {
			pow *= base;
		}
		return pow;
	}
	
	public static int basePow(int exp) {
		return pow(BASE, exp);
	}

	/**
	 * Given the hash for a string, return the hash for that string removing
	 * oldChar from the front and adding newChar to the end.
	 * 
	 * Power is BASE raised to the power of the length of the needle
	 */
	public static int updateHash(int oldHash, int power, char newChar, char oldChar) {
		return (oldHash - (oldChar * power)) * BASE + (int)newChar;
	}

	/**
	 * Hash the given string, using the formula given in the homework
	 */
	public static int hash(String s) {
		int hash = 0;
		int length = s.length();
		for (int i = 0; i < length; i++) {
			int thisChar = s.charAt(i);
			hash += thisChar * pow(BASE, length - 1 - i);
		}
		return hash;
	}
	
	public static boolean bruteForceCompare(String one, String two) {
		if (one == null || two == null || one.length() != two.length()) {
			return false;
		}
		
		for (int i = 0; i < one.length(); i++) {
			if (one.charAt(i) != two.charAt(i)) return false;
		}
		
		return true;
	}

	/**
	 * Run Rabin-Karp on the given strings, looking for needle in haystack.
	 * Return an array of the indices of the occurrence of the needle in the
	 * haystack.
	 * 
	 * If there are matches that start at index 4, 7, and 9 in the
	 * haystack, return an array containing only 4, 7, and 9. If there are no
	 * matches return an empty array, new int[0]
	 */
	public static int[] rabinKarp(String needle, String haystack) {
		if (needle == null || haystack == null || needle.length() == 0 || haystack.length() == 0) {
			return new int[0];
		}
		
		LinkedList<Integer> matches = new LinkedList<Integer>();
		
		final int hLength = haystack.length();
		final int nLength = needle.length();
        final int hashNeedle = hash(needle);
        final int basePow = basePow(nLength-1);

        int hashSubstr = hash(haystack.substring(0, nLength));
                
        for (int i = 0; i < hLength - nLength + 1; i++) {
        	String sub = haystack.substring(i, i + nLength);
        	if (hashNeedle == hashSubstr && bruteForceCompare(needle, sub))
        		matches.add(i);
        	int nextCharIdx = i + nLength;
        	if (nextCharIdx < hLength)
        		hashSubstr = updateHash(hashSubstr, basePow, haystack.charAt(nextCharIdx), haystack.charAt(i));
        }
		
		// TODO
		return buildResults(matches);
	}
}
