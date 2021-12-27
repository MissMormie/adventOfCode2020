package days2021;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day23_AmphipodTest {

	@Test
	void runA() {
		String input;
		int answer;

		// detects correct solution correctly
//		input = "#############\n" +
//				"#...........#\n" +
//				"###A#B#C#D###\n" +
//				"  #A#B#C#D#\n" +
//				"  #########";
//		answer = 0;

		// calculates energy correctly
//		input = "#############\n" +
//				"#...........#\n" +
//				"###.#.#D#C###\n" +
//				"  #.#.#.#.#\n" +
//				"  #########";
//		answer = 5700;


		// detects correct solution correctly
		input = "#############\n" +
				"#...........#\n" +
				"###A#.#.#.###\n" +
				"  #B#.#.#.#\n" +
				"  #########";
		answer = 460;

		assertEquals(answer, Day23_Amphipod.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

	}

	@Test
	void runB() {
		String input;
		int answer;

		input = "#############\n" +
				"#...........#\n" +
				"###B#C#B#D###\n" +
				"  #A#D#C#A#\n" +
				"  #########";
		answer = 44169;
		assertEquals(answer, Day23_Amphipod.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
	}
}