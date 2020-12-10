package days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day9_EncodingErrorTest {
	@Test
	void runA() {
		String input;
		int answer;
		int preamble;

		input = "35\n" +
				"20\n" +
				"15\n" +
				"25\n" +
				"47\n" +
				"40\n" +
				"62\n" +
				"55\n" +
				"65\n" +
				"95\n" +
				"102\n" +
				"117\n" +
				"150\n" +
				"182\n" +
				"127\n" +
				"219\n" +
				"299\n" +
				"277\n" +
				"309\n" +
				"576";
		answer = 127;
		preamble = 5;
		assertEquals(answer, Day9_EncodingError.runA(input, preamble), "input = " + input + " answer: " + answer);

	}

	@Test
	void runB() {
		String input;
		int answer;
		int preamble;


		input = "35\n" +
				"20\n" +
				"15\n" +
				"25\n" +
				"47\n" +
				"40\n" +
				"62\n" +
				"55\n" +
				"65\n" +
				"95\n" +
				"102\n" +
				"117\n" +
				"150\n" +
				"182\n" +
				"127\n" +
				"219\n" +
				"299\n" +
				"277\n" +
				"309\n" +
				"576";
		answer = 62;
		preamble = 5;
		assertEquals(answer, Day9_EncodingError.runB(input, preamble), "input = " + input + " answer: " + answer);

	}

}