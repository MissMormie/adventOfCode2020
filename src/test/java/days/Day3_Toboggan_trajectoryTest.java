package days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day3_Toboggan_trajectoryTest {

	@Test
	void runA() {
		String input = "..##.......\n" +
				"#...#...#..\n" +
				".#....#..#.\n" +
				"..#.#...#.#\n" +
				".#...##..#.\n" +
				"..#.##.....\n" +
				".#.#.#....#\n" +
				".#........#\n" +
				"#.##...#...\n" +
				"#...##....#\n" +
				".#..#...#.#";

		long l = Day3_Toboggan_trajectory.runA(input);
		assertEquals(l, 7);
	}

	@Test
	void runB() {
		String input = "..##.......\n" +
				"#...#...#..\n" +
				".#....#..#.\n" +
				"..#.#...#.#\n" +
				".#...##..#.\n" +
				"..#.##.....\n" +
				".#.#.#....#\n" +
				".#........#\n" +
				"#.##...#...\n" +
				"#...##....#\n" +
				".#..#...#.#";

		long l = Day3_Toboggan_trajectory.runB(input);
		assertEquals(336, l);
	}
}