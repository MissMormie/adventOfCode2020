package days2021;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day10_SyntaxScoringTest {

	@Test
	void runA() {
		String input;
		int answer;

		input = "[({(<(())[]>[[{[]{<()<>>\n" +
				"[(()[<>])]({[<{<<[]>>(\n" +
				"{([(<{}[<>[]}>{[]{[(<()>\n" +
				"(((({<>}<{<{<>}{[]{[]{}\n" +
				"[[<[([]))<([[{}[[()]]]\n" +
				"[{[{({}]{}}([{[{{{}}([]\n" +
				"{<[[]]>}<{[{[{[]{()[[[]\n" +
				"[<(<(<(<{}))><([]([]()\n" +
				"<{([([[(<>()){}]>(<<{{\n" +
				"<{([{{}}[<[[[<>{}]]]>[]]";

		answer = 26397;
		assertEquals(answer, Day10_SyntaxScoring.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
	}

	@Test
	void runB() {
		String input;
		int answer;

		input = "[({(<(())[]>[[{[]{<()<>>\n" +
				"[(()[<>])]({[<{<<[]>>(\n" +
				"{([(<{}[<>[]}>{[]{[(<()>\n" +
				"(((({<>}<{<{<>}{[]{[]{}\n" +
				"[[<[([]))<([[{}[[()]]]\n" +
				"[{[{({}]{}}([{[{{{}}([]\n" +
				"{<[[]]>}<{[{[{[]{()[[[]\n" +
				"[<(<(<(<{}))><([]([]()\n" +
				"<{([([[(<>()){}]>(<<{{\n" +
				"<{([{{}}[<[[[<>{}]]]>[]]";
		answer = 288957;
		assertEquals(answer, Day10_SyntaxScoring.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
	}
}