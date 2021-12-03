package days2020;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day20_JurassicJigsawTest {
	@Test
	void runA() {
		String input;
		int answer;

		input = "";
		answer = 0;
		assertEquals(answer, Day20_JurassicJigsaw.runA(input), "input = " + input + " answer: " + answer);

	}

	@Test
	void isMonsterAt() {
		String input=".####...#####..#...###..\n" +
				"#####..#..#.#.####..#.#.\n" +
				".#.#...#.###...#.##.O#..\n" +
				"#.O.##.OO#.#.OO.##.OOO##\n" +
				"..#O.#O#.O##O..O.#O##.##\n" +
				"...#.#..##.##...#..#..##\n" +
				"#.##.#..#.#..#..##.#.#..\n" +
				".###.##.....#...###.#...\n" +
				"#.####.#.#....##.#..#.#.\n" +
				"##...#..#....#..#...####\n" +
				"..#.##...###..#.#####..#\n" +
				"....#.##.#.#####....#...\n" +
				"..##.##.###.....#.##..#.\n" +
				"#...#...###..####....##.\n" +
				".#.##...#.##.#.#.###...#\n" +
				"#.###.#..####...##..#...\n" +
				"#.###...#.##...#.##O###.\n" +
				".O##.#OO.###OO##..OOO##.\n" +
				"..O#.O..O..O.#O##O##.###\n" +
				"#.#..##.########..#..##.\n" +
				"#.#####..#.#...##..#....\n" +
				"#....##..#.#########..##\n" +
				"#...#.....#..##...###.##\n" +
				"#..###....##.#...##.##.#";
		boolean answer = true;
		String[] splitRadarImage = input.split("\n");

		assertEquals(false, Day20_JurassicJigsaw.isNessieAt(splitRadarImage, 21, 3, 'O'), "input = " + input + " answer: " + answer);

		assertEquals(answer, Day20_JurassicJigsaw.isNessieAt(splitRadarImage, 20, 2, 'O'), "input = " + input + " answer: " + answer);
		assertEquals(answer, Day20_JurassicJigsaw.isNessieAt(splitRadarImage, 19, 16, 'O'), "input = " + input + " answer: " + answer);
	}

	@Test
	void countMonsters() {
		String input=".####...#####..#...###..\n" +
				"#####..#..#.#.####..#.#.\n" +
				".#.#...#.###...#.##.O#..\n" +
				"#.O.##.OO#.#.OO.##.OOO##\n" +
				"..#O.#O#.O##O..O.#O##.##\n" +
				"...#.#..##.##...#..#..##\n" +
				"#.##.#..#.#..#..##.#.#..\n" +
				".###.##.....#...###.#...\n" +
				"#.####.#.#....##.#..#.#.\n" +
				"##...#..#....#..#...####\n" +
				"..#.##...###..#.#####..#\n" +
				"....#.##.#.#####....#...\n" +
				"..##.##.###.....#.##..#.\n" +
				"#...#...###..####....##.\n" +
				".#.##...#.##.#.#.###...#\n" +
				"#.###.#..####...##..#...\n" +
				"#.###...#.##...#.##O###.\n" +
				".O##.#OO.###OO##..OOO##.\n" +
				"..O#.O..O..O.#O##O##.###\n" +
				"#.#..##.########..#..##.\n" +
				"#.#####..#.#...##..#....\n" +
				"#....##..#.#########..##\n" +
				"#...#.....#..##...###.##\n" +
				"#..###....##.#...##.##.#";
		int answer = 2;
		String[] splitRadarImage = input.split("\n");

		assertEquals(answer, Day20_JurassicJigsaw.countSeaMonsters(input, 'O'), "input = " + input + " answer: " + answer);
	}


	@Test
	void runB() {
		String input;
		int answer;

		input = "Tile 2311:\n" +
				"..##.#..#.\n" +
				"##..#.....\n" +
				"#...##..#.\n" +
				"####.#...#\n" +
				"##.##.###.\n" +
				"##...#.###\n" +
				".#.#.#..##\n" +
				"..#....#..\n" +
				"###...#.#.\n" +
				"..###..###\n" +
				"\n" +
				"Tile 1951:\n" +
				"#.##...##.\n" +
				"#.####...#\n" +
				".....#..##\n" +
				"#...######\n" +
				".##.#....#\n" +
				".###.#####\n" +
				"###.##.##.\n" +
				".###....#.\n" +
				"..#.#..#.#\n" +
				"#...##.#..\n" +
				"\n" +
				"Tile 1171:\n" +
				"####...##.\n" +
				"#..##.#..#\n" +
				"##.#..#.#.\n" +
				".###.####.\n" +
				"..###.####\n" +
				".##....##.\n" +
				".#...####.\n" +
				"#.##.####.\n" +
				"####..#...\n" +
				".....##...\n" +
				"\n" +
				"Tile 1427:\n" +
				"###.##.#..\n" +
				".#..#.##..\n" +
				".#.##.#..#\n" +
				"#.#.#.##.#\n" +
				"....#...##\n" +
				"...##..##.\n" +
				"...#.#####\n" +
				".#.####.#.\n" +
				"..#..###.#\n" +
				"..##.#..#.\n" +
				"\n" +
				"Tile 1489:\n" +
				"##.#.#....\n" +
				"..##...#..\n" +
				".##..##...\n" +
				"..#...#...\n" +
				"#####...#.\n" +
				"#..#.#.#.#\n" +
				"...#.#.#..\n" +
				"##.#...##.\n" +
				"..##.##.##\n" +
				"###.##.#..\n" +
				"\n" +
				"Tile 2473:\n" +
				"#....####.\n" +
				"#..#.##...\n" +
				"#.##..#...\n" +
				"######.#.#\n" +
				".#...#.#.#\n" +
				".#########\n" +
				".###.#..#.\n" +
				"########.#\n" +
				"##...##.#.\n" +
				"..###.#.#.\n" +
				"\n" +
				"Tile 2971:\n" +
				"..#.#....#\n" +
				"#...###...\n" +
				"#.#.###...\n" +
				"##.##..#..\n" +
				".#####..##\n" +
				".#..####.#\n" +
				"#..#.#..#.\n" +
				"..####.###\n" +
				"..#.#.###.\n" +
				"...#.#.#.#\n" +
				"\n" +
				"Tile 2729:\n" +
				"...#.#.#.#\n" +
				"####.#....\n" +
				"..#.#.....\n" +
				"....#..#.#\n" +
				".##..##.#.\n" +
				".#.####...\n" +
				"####.#.#..\n" +
				"##.####...\n" +
				"##..#.##..\n" +
				"#.##...##.\n" +
				"\n" +
				"Tile 3079:\n" +
				"#.#.#####.\n" +
				".#..######\n" +
				"..#.......\n" +
				"######....\n" +
				"####.#..#.\n" +
				".#...#.##.\n" +
				"#.#####.##\n" +
				"..#.###...\n" +
				"..#.......\n" +
				"..#.###...";
		answer = 273;
		assertEquals(answer, Day20_JurassicJigsaw.runB(input), "input = " + input + " answer: " + answer);
	}
}