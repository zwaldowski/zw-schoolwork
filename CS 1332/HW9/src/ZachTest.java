import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;


public class ZachTest {

	@Test
	public void test() {
		Graph testgraph = new Graph("11 0 1 7 0 3 5 1 3 9 1 2 8 1 4 7 2 4 5 3 4 15 3 5 6 4 5 8 4 6 9 5 6 11");
		Vertex A = new Vertex(0),
		 B = new Vertex(1),
		 C = new Vertex(2),
		 D = new Vertex(3),
		 E = new Vertex(4),
		 F = new Vertex(5),
		 G = new Vertex(6);
		Edge one = new Edge(A, D, 5),
			 two = new Edge(C, E, 5),
			 thr = new Edge(D, F, 6),
			 fur = new Edge(A, B, 7),
			 fiv = new Edge(B, E, 7),
			 six = new Edge(E, G, 9);
		
		
		/*0 1 7
		1 3 9
		1 2 8
		2 4 5
		1 4 7
		4 3 15
		3 5 6
		4 5 8
		4 6 9
		5 6 11*/

		Collection<Edge> out = MST.kruskals(testgraph);
		System.out.println(out);
	}

}
