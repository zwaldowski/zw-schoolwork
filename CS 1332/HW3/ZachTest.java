import java.util.List;
import org.junit.Test;

/*
 * This is a compilation test, it passes if it compiles. Running
 * the test is not necessary, and may not actually pass when you
 * run it.
 */
public class ZachTest {

	@Test
	public void test() {
		BST<Integer> t = new BST<Integer>( );
        final int NUMS = 4000;
        final int GAP  =   37;

        System.out.println( "Checking... (no more output means success)" );

        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
            t.add( i );

        for( int i = 1; i < NUMS; i += 2 )
            t.remove( i );

        List<Integer> inOrder = t.inOrder();
        List<Integer> preOrder = t.preOrder();
        
        StringBuilder inOrderBld = new StringBuilder();
        
        for (Integer i : inOrder) {
        	inOrderBld.append(i);
        	inOrderBld.append(" ");
        }
        
        System.out.println(inOrderBld);
        
        StringBuilder preOrderBld = new StringBuilder();
        
        for (Integer i : preOrder) {
        	preOrderBld.append(i);
        	preOrderBld.append(" ");
        }
        
        System.out.println(preOrderBld);
		
	}
	

}
