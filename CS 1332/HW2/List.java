import java.util.Collection;


/**
 * Refer to the java API for how each method should behave. (don't
 * worry about the return type for add and addAll) 
 * 
 * http://docs.oracle.com/javase/7/docs/api/java/util/List.html
 */
public interface List<E> {

	void add(E e);
	
	void addAll(Collection<? extends E> c);
	
	void clear();
	
	boolean contains(Object o);
	
	E get(int index);
	
	int indexOf(Object o);

	boolean isEmpty();
	
	E remove(int index);
	
	E remove(Object o);
	
	E set(int index, E e);
	
	int size();
	
}
