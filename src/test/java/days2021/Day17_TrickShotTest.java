package days2021;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day17_TrickShotTest {

	@Test
	void runA() {
		String input;
		int answer;

		input = "target area: x=20..30, y=-10..-5";
		answer = 45;
		assertEquals(answer, Day17_TrickShot.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

	}


	@Test
	void runB() {
		String input;
		int answer;

		input = "target area: x=20..30, y=-10..-5";
		answer = 112;
		assertEquals(answer, Day17_TrickShot.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
	}
}