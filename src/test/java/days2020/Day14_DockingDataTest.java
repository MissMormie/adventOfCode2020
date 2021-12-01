package days2020;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day14_DockingDataTest {
	@Test
	void runA() {
		String input;
		int answer;

		input = "mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X\n" +
				"mem[8] = 11\n" +
				"mem[7] = 101\n" +
				"mem[8] = 0";
		answer = 165;
		assertEquals(answer, Day14_DockingData.runA(input), "input = " + input + " answer: " + answer);
	}

	@Test
	void runB() {
		String input;
		int answer;

		input = "mask = 000000000000000000000000000000X1001X\n" +
				"mem[42] = 100\n" +
				"mask = 00000000000000000000000000000000X0XX\n" +
				"mem[26] = 1";
		answer = 208;
		assertEquals(answer, Day14_DockingData.runB(input), "input = " + input + " answer: " + answer);

	}

}