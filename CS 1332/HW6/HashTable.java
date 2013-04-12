import java.util.ArrayList;	// for values()
import java.util.Collection;// for values()
import java.util.HashSet;	// for keySet()/entrySet()
import java.util.Set;		// for keySet()/entrySet()

//
// Homework 6
// Zachary Waldowski
// CS 1332
//

/**
* A simple data structure that provides a dictionary. (HW06)
* @author Zachary Waldowski
*/
public class HashTable<K,V> {
	
	/**
	 * The initial size of the hashtable
	 */
	private final int TABLE_INITIAL_SIZE = 11;

	/**
	 * The maximum load factor for this hashtable
	 */
	private final double MAX_LOAD_FACTOR = .64;

	/**
	 * The number of entries in this hashtable
	 */
	private int size;
	
	private int maxSize = 0;

	/**
	 * The underlying array for this hashtable
	 */
	private Entry<K,V>[] table;
	
	@SuppressWarnings("unchecked")
	public HashTable() {
		table = new Entry[TABLE_INITIAL_SIZE];
		maxSize = (int)(TABLE_INITIAL_SIZE * MAX_LOAD_FACTOR);
	}
	
	private static int hashKey(Object key) {
		return Math.abs(key.hashCode());
	}
	
	private static int indexForHash(int hash, Entry<?,?>[] table) {
		return hash % (table.length - 1);
	}
	
	/**
	 * Puts the key value pair into the table. If the
	 * key already exists in the table, replace the
	 * old value with the new one and return the old value
	 * 
	 * @param key, never null
	 * @param value, possibly null
	 * @return the replaced value, null if nothing existed previously
	 */
	public V put(K key ,V value) {
		int hash = hashKey(key);
		int index = indexForHash(hash, table);
		
		if (table[index] == null) {
			addEntryAtIndex(index, key, value);
			return null;
		} else {
			Entry <K, V> entry = table[index];
			while (entry != null) {
				if (!entry.isAvailable() && (entry.getKey() == key || entry.getKey().equals(key))) {
					V oldValue = entry.getValue();
					entry.setValue(value);
					entry.setAvailable((value == null) ? true : false);
					return oldValue;
				}
				
				index = indexForHash(index + 1, table);
				entry = table[index];
			}
		}
		
		// So we're now at a point where we can just insert it.
		// Thanks, linear probing!
		addEntryAtIndex(index, key, value);
		return null;
	}
	
	private void addEntryToTableAtIndex(Entry<K,V>[] table, int idx, K key, V value) {
		Entry<K, V> entry = new Entry<>(key, value);
		entry.setAvailable((value == null) ? true : false);
		table[idx] = entry;
	}
	
	@SuppressWarnings("unchecked")
	private void resize() {
		int newSize = (2 * table.length) + 1;
		
		Entry<K,V>[] oldTable = table;
		Entry<K,V>[] newTable = new Entry[newSize];
		
		tableTransfer(oldTable, newTable);
		
		table = newTable;
		maxSize = (int)(newSize * MAX_LOAD_FACTOR);
	}
	
	private void putForResize(Entry<K,V>[] localTable, K key, V value) {
		int index = indexForHash(hashKey(key), localTable);
		
		Entry <K, V> entry = localTable[index];
		while (entry != null) {				
			index = indexForHash(index + 1, localTable);
			entry = localTable[index];
		}
		
		addEntryToTableAtIndex(localTable, index, key, value);
	}
	
	private void tableTransfer(Entry<K,V>[] src, Entry<K,V>[] dest) {
		for (int i = 0; i < src.length; i++) {
			Entry<K, V> entry = src[i];
			if (entry != null && !entry.isAvailable()) {
				src[i] = null;
				putForResize(dest, entry.getKey(), entry.getValue());
			}
		}
	}
	
	private void addEntryAtIndex(int hash, K key, V value) {
		addEntryToTableAtIndex(table, hash, key, value);
		size++;
		if (size >= maxSize) {
			resize();
		}
	}
	
	/**
	 * Removes the entry containing the given key
	 * 
	 * (remember that all objects have a hashCode method)
	 * 
	 * @param key, never null
	 * @return the value of the removed entry
	 */
	public V remove(Object key) {
		Entry <K, V> entry = removeEntryWithKey(key);
		if (entry == null) return null;
		V value = entry.getValue();
		entry.setValue(null);
		return value;
	}
	
	private Entry<K, V> removeEntryWithKey(Object key) {
		int hash = hashKey(key);
		int index = indexForHash(hash, table);
		
		Entry <K, V> entry = table[index];
		while (entry != null) {
			if (!entry.isAvailable() && (entry.getKey() == key || entry.getKey().equals(key))) {
				entry.setAvailable(true);
				size--;
				break;
			}
			
			index = indexForHash(index + 1, table);
			entry = table[index];
		}
		return entry;
	}
	
	/**
	 * Gets the value of the entry given a specific key
	 * 
	 * (remember that all objects have a hashCode method)
	 * 
	 * @param key, never null
	 * @return
	 */
	public V get(Object key) {
		Entry <K, V> entry = getEntryWithKey(key);
		if (entry == null) return null;
		return entry.getValue();
	}
	
	/**
	 * @param key, never null
	 * @return true if this table contains the given key, false otherwise
	 */
	public boolean containsKey(Object key) {
		Entry <K, V> entry = getEntryWithKey(key);
		if (entry == null) return false;
		return !entry.isAvailable();
	}
	
	private Entry<K, V> getEntryWithKey(Object key) {
		int hash = hashKey(key);
		int index = indexForHash(hash, table);
		
		Entry <K, V> entry = table[index];
		while (entry != null) {
			if (!entry.isAvailable() && (entry.getKey() == key || entry.getKey().equals(key))) {
				break;
			}
			
			index = indexForHash(index + 1, table);
			entry = table[index];
		}
		
		return entry;
	}
	
	/**
	 * Clears this hashTable
	 */
	public void clear() {
		for (int i = 0; i < table.length; i++) {
			table[i] = null;
		}
		size = 0;
	}
	
	/**
	 * @return true if this hashtable is empty, false otherwise
	 */
	public boolean isEmpty() {
		return (size == 0);
	}
	
	/**
	 * @return the value from this hashtable
	 */
	public Collection<V> values() {
		ArrayList<V> array = new ArrayList<>(size);
		for (int i = 0; i < table.length; i++) {
			Entry <K, V> entry = table[i];
			if (entry != null && !entry.isAvailable()) {
				array.add(entry.getValue());
			}
		}
		return array;
	}
	
	/**
	 * @return the unique keys from this hashtable
	 */
	public Set<K> keySet() {
		HashSet<K> set = new HashSet<>(size);
		for (int i = 0; i < table.length; i++) {
			Entry <K, V> entry = table[i];
			if (entry != null && !entry.isAvailable()) {
				set.add(entry.getKey());
			}
		}
		return set;
	}
	
	/**
	 * @return the unique entries from this hashtable
	 */
	public Set<Entry<K, V>> entrySet() {
		HashSet<Entry<K, V>> set = new HashSet<>(size);
		for (int i = 0; i < table.length; i++) {
			Entry <K, V> entry = table[i];
			if (entry != null && !entry.isAvailable()) {
				set.add(entry);
			}
		}
		return set;
	}
	
	/**
	 * @return the size of this hashtable
	 */
	public int size() {
		return size;
	}

	/*
	 * Don't modify any code below this point
	 */
	
	public void setSize(int size) {
		this.size = size;
	}

	public Entry<K,V>[] getTable() {
		return table;
	}

	public void setTable(Entry<K,V>[] table) {
		this.table = table;
	}

	public double getMaxLoadFactor() {
		return MAX_LOAD_FACTOR;
	}

	public static class Entry<K,V> {
		private K key;
		private V value;
		private boolean available;
		
		public Entry(K key, V value) {
			this.setKey(key);
			this.setValue(value);
			this.setAvailable(true);
		}
		
		public void setKey(K key) {
			this.key = key;
		}
		
		public K getKey() {
			return this.key;
		}
		
		public void setValue(V value) {
			this.value = value;
		}
		
		public V getValue() {
			return this.value;
		}

		public boolean isAvailable() {
			return available;
		}

		public void setAvailable(boolean available) {
			this.available = available;
		}
	}
}
