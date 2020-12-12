package days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day11_SeatingSystemTest {
	@Test
	void runA() {
		String input;
		int answer;

		input = "L.LL.LL.LL\n" +
				"LLLLLLL.LL\n" +
				"L.L.L..L..\n" +
				"LLLL.LL.LL\n" +
				"L.LL.LL.LL\n" +
				"L.LLLLL.LL\n" +
				"..L.L.....\n" +
				"LLLLLLLLLL\n" +
				"L.LLLLLL.L\n" +
				"L.LLLLL.LL";
		answer = 37;
		assertEquals(answer, Day11_SeatingSystem.runA(input), "input = " + input + " answer: " + answer);
	}

	@Test
	void runB() {
		String input;
		int answer;

		input = "L.LL.LL.LL\n" +
				"LLLLLLL.LL\n" +
				"L.L.L..L..\n" +
				"LLLL.LL.LL\n" +
				"L.LL.LL.LL\n" +
				"L.LLLLL.LL\n" +
				"..L.L.....\n" +
				"LLLLLLLLLL\n" +
				"L.LLLLLL.L\n" +
				"L.LLLLL.LL";
		answer = 26;
		assertEquals(answer, Day11_SeatingSystem.runB(input), "input = " + input + " answer: " + answer);
	}

}