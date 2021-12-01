package days2020;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day19_MonsterMessagesTest {
	@Test
	void runA() {
		String validations;
		int answer;
		String input;

		validations = "0: 4 1 5\n" +
				"1: 2 3 | 3 2\n" +
				"2: 4 4 | 5 5\n" +
				"3: 4 5 | 5 4\n" +
				"4: \"a\"\n" +
				"5: \"b\"";
		input = "ababb\n" +
				"bababa\n" +
				"abbba\n" +
				"aaabb\n" +
				"aaaabb";
		answer = 2;
		assertEquals(answer, Day19_MonsterMessages2.runA(validations, input), "validations = " + validations + " answer: " + answer);

	}


	@Test
	void runB() {
		String input;
		int answer;

		input = "";
		answer = 0;
		assertEquals(answer, Day19_MonsterMessages.runB(input), "input = " + input + " answer: " + answer);
	}
}