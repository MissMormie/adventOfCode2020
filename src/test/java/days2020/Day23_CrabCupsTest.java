package days2020;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day23_CrabCupsTest {
	@Test
	void runA() {
		String input;
		String answer;

		input = "389125467";
		answer = "67384529";
		assertEquals(answer, Day23_CrabCups.runA(input), "input = " + input + " answer: " + answer);

	}


	@Test
	void runB() {
		String input;
		long answer;

		input = "389125467";
		answer = 149245887792l;
		assertEquals(answer, Day23_CrabCups.runB(input), "input = " + input + " answer: " + answer);
	}
}