import org.junit.Test;

/*
 * This is a compilation test, it passes if it compiles. Running
 * the test is not necessary, and may not actually pass when you
 * run it.
 */
public class ZachTest {

	@Test
	public void test() {
		AVL<Integer> t = new AVL<Integer>( );
        final int NUMS = 4000;
        System.out.println( "Checking... (no more output means success)" );

        for( int i = 1; i < NUMS; i++ )
            t.add( i );

        for( int i = 1; i < NUMS - 1; i++)
            t.remove( i );

		
	}
	

}
