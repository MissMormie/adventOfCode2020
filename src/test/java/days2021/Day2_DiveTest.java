package days2021;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day2_DiveTest {
	@Test
	void runA() {
		String input;
		int answer;

		input = "forward 5\n" +
				"down 5\n" +
				"forward 8\n" +
				"up 3\n" +
				"down 8\n" +
				"forward 2";
		answer = 150;
		assertEquals(answer, Day2_Dive.runA(input), "input = " + input + " answer: " + answer);

	}

	@Test
	void runB() {
		String input;
		int answer;

		input = "forward 5\n" +
				"down 5\n" +
				"forward 8\n" +
				"up 3\n" +
				"down 8\n" +
				"forward 2";
		answer = 900;
		assertEquals(answer, Day2_Dive.runB(input), "input = " + input + " answer: " + answer);
	}
}