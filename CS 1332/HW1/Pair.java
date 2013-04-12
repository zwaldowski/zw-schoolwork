

import java.util.Objects;

public class Pair<A, B> {

	public final A a;
	public final B b;
	
	public Pair(A a, B b) {
		this.a = a;
		this.b = b;
	}
	
	@Override
	public String toString() {
		// appending things to the empty string prevents us from having to worry about null
		// and calling toString explicitly, Objects.toString(a) + " " + Objects.toString(b)
		// would also work
		return "" + a + " " + b;
	}
	
	@Override
	public boolean equals(Object obj) {
		// `obj instanceof Pair` will automatically return false if obj is null
		if (!(obj instanceof Pair)) {
			return false;
		}
		
		// some warnings with generics are unavoidable
		@SuppressWarnings("unchecked")
		Pair<A, B> p = (Pair<A, B>) obj;
		
		// we use Objects.equals() to handle nulls easily
		return Objects.equals(a, p.a) && Objects.equals(b, p.b);
	}
	
	@Override
	public int hashCode() {
		// we use Objects.hashCode() to handle nulls easily, 
		// the operation ^ is XOR, not exponentiation
		return Objects.hashCode(a) ^ Objects.hashCode(b);
	}
}
