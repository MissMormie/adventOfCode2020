package days2022;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day25_FullOfHotAirTest {

	@Test
	void runA() {
		String input;
		String answer;

		input = "1=-0-2\n" +
				"12111\n" +
				"2=0=\n" +
				"21\n" +
				"2=01\n" +
				"111\n" +
				"20012\n" +
				"112\n" +
				"1=-1=\n" +
				"1-12\n" +
				"12\n" +
				"1=\n" +
				"122";
		answer = "2=-1=0";
		assertEquals(answer, Day25_FullOfHotAir.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

	}

	@Test
	void convertToSnafu() {
		assertEquals("2=-01", Day25_FullOfHotAir.convertToSnafu(976));
		assertEquals("1=11-2", Day25_FullOfHotAir.convertToSnafu(2022));
		assertEquals("1=", Day25_FullOfHotAir.convertToSnafu(3));
		assertEquals("10", Day25_FullOfHotAir.convertToSnafu(5));
		assertEquals("1", Day25_FullOfHotAir.convertToSnafu(1));
		assertEquals("2", Day25_FullOfHotAir.convertToSnafu(2));
		assertEquals("1-", Day25_FullOfHotAir.convertToSnafu(4));
		assertEquals("10", Day25_FullOfHotAir.convertToSnafu(5L));
		assertEquals("1121-1110-1=0", Day25_FullOfHotAir.convertToSnafu(314159265));
	}

	@Test
	void convertToDecimal() {
		assertEquals(1, Day25_FullOfHotAir.convertToDecimal("1"));
		assertEquals(2, Day25_FullOfHotAir.convertToDecimal("2"));
		assertEquals(3, Day25_FullOfHotAir.convertToDecimal("1="));
		assertEquals(4, Day25_FullOfHotAir.convertToDecimal("1-"));
		assertEquals(5, Day25_FullOfHotAir.convertToDecimal("10"));
		assertEquals(2022, Day25_FullOfHotAir.convertToDecimal("1=11-2"));
		assertEquals(314159265, Day25_FullOfHotAir.convertToDecimal("1121-1110-1=0"));

	}

	@Test
	void runB() {
		String input;
		int answer;

		input = "";
		answer = 0;
		assertEquals(answer, Day25_FullOfHotAir.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
	}
}