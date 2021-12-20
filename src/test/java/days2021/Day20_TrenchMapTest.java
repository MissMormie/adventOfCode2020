package days2021;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day20_TrenchMapTest {

	@Test
	void runA() {
		String input;
		int answer;

		input = "#..#.\n" +
				"#....\n" +
				"##..#\n" +
				"..#..\n" +
				"..###";
		Day20_TrenchMap.enhancementAlgorithm = "..#.#..#####.#.#.#.###.##.....###.##.#..###.####..#####..#....#..#..##..###..######.###...####..#..#####..##..#.#####...##.#.#..#.##..#.#......#.###.######.###.####...#.##.##..#..#..#####.....#.#....###..#.##......#.....#..#..#..##..#...##.######.####.####.#.#...#.......#..#.#.#...####.##.#......#..#...##.#.##..#...##.#.##..###.#......#.#.......#.#.#.####.###.##...#.....####.#..#..#.##.#....##..#.####....##...##..#...#......#.#.......#.......##..####..#...#.#.#...##..#.#..###..#####........#..####......#..#";
		answer = 35;
		assertEquals(answer, Day20_TrenchMap.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

	}
//
//
//	@Test
//	void runB() {
//		String input;
//		int answer;
//
//		input = "";
//		answer = 0;
//		assertEquals(answer, Day0.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
//	}
}