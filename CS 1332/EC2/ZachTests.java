import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ZachTests {

	@Test
	public void test1() {
		int capacities[][] = new int[6][6];

		for (int[] u : capacities) {
			Arrays.fill(u, 0);
		}

		capacities[0][1] = 10;
		capacities[0][2] = 10;
		capacities[1][2] = 2;
		capacities[1][3] = 4;
		capacities[1][4] = 8;
		capacities[2][4] = 9;
		capacities[4][3] = 6;
		capacities[3][5] = 10;
		capacities[4][5] = 10;

		assertEquals(19, MaxFlow.flow(6, 0, 5, capacities));
	}

	@Test
	public void test2() {
		int[][] capacities = new int[4][4];
		for (int[] u : capacities) {
			Arrays.fill(u, 0);
		}

		capacities[0][1] = 4;
		capacities[0][2] = 2;
		capacities[1][3] = 1;
		capacities[1][2] = 3;
		capacities[2][3] = 6;

		assertEquals(6, MaxFlow.flow(4, 0, 3, capacities));
	}

	@Test
	public void test3() {
		int[][] capacities = new int[7][7];
		for (int[] u : capacities) {
			Arrays.fill(u, 0);
		}

		capacities[1][3] = 0;
		capacities[1][2] = 6;
		capacities[1][5] = 5;
		capacities[1][4] = 5;
		capacities[2][4] = 3;
		capacities[2][5] = 1;
		capacities[2][6] = 3;
		capacities[3][2] = 3;
		capacities[3][4] = Integer.MAX_VALUE;
		capacities[3][5] = 2;
		capacities[3][6] = 9;
		capacities[4][3] = Integer.MAX_VALUE;
		capacities[4][6] = 0;
		capacities[5][4] = 3;
		capacities[5][6] = 4;

		assertEquals(16, MaxFlow.flow(7, 1, 6, capacities));
	}

}
