package days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day6_CustomCustomsTest {
	@Test
	void runA() {
		String input;
		int answer;

		input = "abc\n" +
				"\n" +
				"a\n" +
				"b\n" +
				"c\n" +
				"\n" +
				"ab\n" +
				"ac\n" +
				"\n" +
				"a\n" +
				"a\n" +
				"a\n" +
				"a\n" +
				"\n" +
				"b";
		answer = 11;
		assertEquals(answer, Day6_CustomCustoms.runA(input), "input = " + input + " answer: " + answer);

	}


	@Test
	void runB() {
		String input;
		int answer;

		input = "";
		answer = 0;
		assertEquals(answer, Day6_CustomCustoms.runB(input), "input = " + input + " answer: " + answer);
	}
}