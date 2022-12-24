package days2022;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day21_MonkeyMathTest {

	@Test
	void runA() {
		String input;
		int answer;

		input = "root: pppw + sjmn\n" +
				"dbpl: 5\n" +
				"cczh: sllz + lgvd\n" +
				"zczc: 2\n" +
				"ptdq: humn - dvpt\n" +
				"dvpt: 3\n" +
				"lfqf: 4\n" +
				"humn: 5\n" +
				"ljgn: 2\n" +
				"sjmn: drzm * dbpl\n" +
				"sllz: 4\n" +
				"pppw: cczh / lfqf\n" +
				"lgvd: ljgn * ptdq\n" +
				"drzm: hmdt - zczc\n" +
				"hmdt: 32";
		answer = 152;
		assertEquals(answer, Day21_MonkeyMath.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

	}


	@Test
	void runB() {
		String input;
		int answer;

		input = "root: pppw + sjmn\n" +
				"dbpl: 5\n" +
				"cczh: sllz + lgvd\n" +
				"zczc: 2\n" +
				"ptdq: humn - dvpt\n" +
				"dvpt: 3\n" +
				"lfqf: 4\n" +
				"humn: 5\n" +
				"ljgn: 2\n" +
				"sjmn: drzm * dbpl\n" +
				"sllz: 4\n" +
				"pppw: cczh / lfqf\n" +
				"lgvd: ljgn * ptdq\n" +
				"drzm: hmdt - zczc\n" +
				"hmdt: 32";;
		answer = 301;
		assertEquals(answer, Day21_MonkeyMath.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
	}
}