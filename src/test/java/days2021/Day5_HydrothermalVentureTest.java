package days2021;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day5_HydrothermalVentureTest {
	@Test
	void runA() {
		String input;
		int answer;

		input = "0,9 -> 5,9\n" +
				"8,0 -> 0,8\n" +
				"9,4 -> 3,4\n" +
				"2,2 -> 2,1\n" +
				"7,0 -> 7,4\n" +
				"6,4 -> 2,0\n" +
				"0,9 -> 2,9\n" +
				"3,4 -> 1,4\n" +
				"0,0 -> 8,8\n" +
				"5,5 -> 8,2";
		answer = 5;
		assertEquals(answer, Day5_HydrothermalVenture.runA(input), "input = " + input + " answer: " + answer);

	}


	@Test
	void runB() {
		String input;
		int answer;

		input = "0,9 -> 5,9\n" +
				"8,0 -> 0,8\n" +
				"9,4 -> 3,4\n" +
				"2,2 -> 2,1\n" +
				"7,0 -> 7,4\n" +
				"6,4 -> 2,0\n" +
				"0,9 -> 2,9\n" +
				"3,4 -> 1,4\n" +
				"0,0 -> 8,8\n" +
				"5,5 -> 8,2";
		answer = 12;
		assertEquals(answer, Day5_HydrothermalVenture.runB(input), "input = " + input + " answer: " + answer);
	}
}