package days2021;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day25_SeaCucumberTest {

	@Test
	void runA() {
		String input;
		int answer;

		input = "v...>>.vv>\n" +
				".vv>>.vv..\n" +
				">>.>v>...v\n" +
				">>v>>.>.v.\n" +
				"v>v.vv.v..\n" +
				">.>>..v...\n" +
				".vv..>.>v.\n" +
				"v.v..>>v.v\n" +
				"....v..v.>";
		answer = 58;
		assertEquals(answer, Day25_SeaCucumber.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

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