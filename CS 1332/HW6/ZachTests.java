import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.junit.Test;


public class ZachTests {

	@Test
	public void testBasicPut() {
		HashTable<String, Integer> table = new HashTable<>();
		table.put("Cat", 1);
		table.put("Dog", 2);
		assertEquals(2, table.size());
		Integer valueTest1 = table.get("Cat");
		Integer valueTest2 = table.get("Dog");
		assertEquals((Integer)1, valueTest1);
		assertEquals((Integer)2, valueTest2);
	}

	@Test
	public void testBasicUpdate() {
		HashTable<String, Integer> table = new HashTable<>();
		table.put("Cat", 1);
		table.put("Cat", 2);
		assertEquals(1, table.size());
		Integer valueTest1 = table.get("Cat");
		assertEquals((Integer)2, valueTest1);
	}
	
	@Test
	public void testBasicRemove() {
		HashTable<String, Integer> table = new HashTable<>();
		table.put("Cat", 1);
		table.put("Dog", 2);
		assertEquals(2, table.size());
		Integer removedValue = table.remove("Cat");
		assertEquals(1, table.size());
		assertEquals((Integer)1, removedValue);
	}
	
	@Test
	public void testPutResizing() {
		HashTable<String, Integer> table = new HashTable<>();
		table.put("Cheetah", 0);
		table.put("Puma", 1);
		table.put("Jaguar", 2);
		table.put("Panther", 3);
		table.put("Tiger", 4);
		table.put("Leopard", 5);
		table.put("Snow Leopard", 6);
		table.put("Lion", 7);
		table.put("Mountain Lion", 8);
		assertEquals(9, table.size());
		assertEquals((Integer)0, table.get("Cheetah"));
		assertEquals((Integer)1, table.get("Puma"));
		assertEquals((Integer)2, table.get("Jaguar"));
		assertEquals((Integer)3, table.get("Panther"));
		assertEquals((Integer)4, table.get("Tiger"));
		assertEquals((Integer)5, table.get("Leopard"));
		assertEquals((Integer)6, table.get("Snow Leopard"));
		assertEquals((Integer)7, table.get("Lion"));
		assertEquals((Integer)8, table.get("Mountain Lion"));
	}

    private static final Comparator<Integer> VALUE_COMPARATOR = new Comparator<Integer>() {
        // Overriding the compare method to sort the age
        public int compare(Integer a, Integer b) {
            return a - b;
        }
    };
	
	@Test
	public void testValues() {
		HashTable<String, Integer> table = new HashTable<>();
		table.put("Cheetah", 0);
		table.put("Puma", 1);
		table.put("Jaguar", 2);
		table.put("Panther", 3);
		table.put("Tiger", 4);
		table.put("Leopard", 5);
		table.put("Snow Leopard", 6);
		table.put("Lion", 7);
		table.put("Mountain Lion", 8);
		assertEquals(table.size(), 9);
		List<Integer> values = (List<Integer>)table.values();
		Collections.sort(values, VALUE_COMPARATOR);
		Object[] result = values.toArray();
		Integer[] expected = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
		assertArrayEquals(expected, result);
	}
	
	@Test
	public void testKeys() {
		HashTable<String, Integer> table = new HashTable<>();
		table.put("Cheetah", 0);
		table.put("Puma", 1);
		table.put("Jaguar", 2);
		table.put("Panther", 3);
		table.put("Tiger", 4);
		table.put("Leopard", 5);
		table.put("Snow Leopard", 6);
		table.put("Lion", 7);
		table.put("Mountain Lion", 8);
		assertEquals(table.size(), 9);
		
		Set<String> keys = table.keySet();
		List<String> keysList = new ArrayList<>(keys);
		Collections.sort(keysList);
		Object[] result = keysList.toArray();
		String[] expected = { "Cheetah", "Jaguar", "Leopard", "Lion", "Mountain Lion", "Panther", "Puma", "Snow Leopard", "Tiger" };
		assertArrayEquals(expected, result);
	}

}
