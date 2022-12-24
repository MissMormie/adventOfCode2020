package days2022;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day23_UnstableDiffusionTest {

	@Test
	void runA() {
		String input;
		int answer;

		input = "....#..\n" +
				"..###.#\n" +
				"#...#.#\n" +
				".#...##\n" +
				"#.###..\n" +
				"##.#.##\n" +
				".#..#..";
		answer = 110;
		assertEquals(answer, Day23_UnstableDiffusion.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

	}


	@Test
	void runB() {
		String input;
		int answer;

		input = "....#..\n" +
				"..###.#\n" +
				"#...#.#\n" +
				".#...##\n" +
				"#.###..\n" +
				"##.#.##\n" +
				".#..#..";
		answer = 110;
		assertEquals(answer, Day23_UnstableDiffusion.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
	}
}