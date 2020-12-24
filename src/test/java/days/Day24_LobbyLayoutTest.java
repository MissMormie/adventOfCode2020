package days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day24_LobbyLayoutTest {
	@Test
	void runA() {
		String input;
		int answer;

		input = "sesenwnenenewseeswwswswwnenewsewsw\n" +
				"neeenesenwnwwswnenewnwwsewnenwseswesw\n" +
				"seswneswswsenwwnwse\n" +
				"nwnwneseeswswnenewneswwnewseswneseene\n" +
				"swweswneswnenwsewnwneneseenw\n" +
				"eesenwseswswnenwswnwnwsewwnwsene\n" +
				"sewnenenenesenwsewnenwwwse\n" +
				"wenwwweseeeweswwwnwwe\n" +
				"wsweesenenewnwwnwsenewsenwwsesesenwne\n" +
				"neeswseenwwswnwswswnw\n" +
				"nenwswwsewswnenenewsenwsenwnesesenew\n" +
				"enewnwewneswsewnwswenweswnenwsenwsw\n" +
				"sweneswneswneneenwnewenewwneswswnese\n" +
				"swwesenesewenwneswnwwneseswwne\n" +
				"enesenwswwswneneswsenwnewswseenwsese\n" +
				"wnwnesenesenenwwnenwsewesewsesesew\n" +
				"nenewswnwewswnenesenwnesewesw\n" +
				"eneswnwswnwsenenwnwnwwseeswneewsenese\n" +
				"neswnwewnwnwseenwseesewsenwsweewe\n" +
				"wseweeenwnesenwwwswnew";
		answer = 10;
		assertEquals(answer, Day24_LobbyLayout.runA(input), "input = " + input + " answer: " + answer);

	}


	@Test
	void runB() {
		String input;
		int answer;

		input = "sesenwnenenewseeswwswswwnenewsewsw\n" +
				"neeenesenwnwwswnenewnwwsewnenwseswesw\n" +
				"seswneswswsenwwnwse\n" +
				"nwnwneseeswswnenewneswwnewseswneseene\n" +
				"swweswneswnenwsewnwneneseenw\n" +
				"eesenwseswswnenwswnwnwsewwnwsene\n" +
				"sewnenenenesenwsewnenwwwse\n" +
				"wenwwweseeeweswwwnwwe\n" +
				"wsweesenenewnwwnwsenewsenwwsesesenwne\n" +
				"neeswseenwwswnwswswnw\n" +
				"nenwswwsewswnenenewsenwsenwnesesenew\n" +
				"enewnwewneswsewnwswenweswnenwsenwsw\n" +
				"sweneswneswneneenwnewenewwneswswnese\n" +
				"swwesenesewenwneswnwwneseswwne\n" +
				"enesenwswwswneneswsenwnewswseenwsese\n" +
				"wnwnesenesenenwwnenwsewesewsesesew\n" +
				"nenewswnwewswnenesenwnesewesw\n" +
				"eneswnwswnwsenenwnwnwwseeswneewsenese\n" +
				"neswnwewnwnwseenwseesewsenwsweewe\n" +
				"wseweeenwnesenwwwswnew";
		answer = 2208;
		assertEquals(answer, Day24_LobbyLayout.runB(input), "input = " + input + " answer: " + answer);
	}
}