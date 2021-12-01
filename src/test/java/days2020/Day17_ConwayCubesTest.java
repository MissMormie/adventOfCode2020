package days2020;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day17_ConwayCubesTest {

	@Test
	void runA() {
		String input;
		int answer;

		input = ".#.\n" +
				"..#\n" +
				"###";

		answer = 11;
		assertEquals(answer, Day17_ConwayCubes.runA(input,1), "input = " + input + " answer: " + answer);

		answer = 112;
		assertEquals(answer, Day17_ConwayCubes.runA(input,6), "input = " + input + " answer: " + answer);

	}


	@Test
	void runB() {
		String input;
		int answer;

		input = ".#.\n" +
				"..#\n" +
				"###";
		answer = 848;
		assertEquals(answer, Day17_ConwayCubes.runB(input, 6), "input = " + input + " answer: " + answer);
	}
}