package days2022;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day11_MonkeyInTheMiddleTest {

	@Test
	void runA() {
		String input;
		long answer;

		input = "Monkey 0:\n" +
				"  Starting items: 79, 98\n" +
				"  Operation: new = old * 19\n" +
				"  Test: divisible by 23\n" +
				"    If true: throw to monkey 2\n" +
				"    If false: throw to monkey 3\n" +
				"\n" +
				"Monkey 1:\n" +
				"  Starting items: 54, 65, 75, 74\n" +
				"  Operation: new = old + 6\n" +
				"  Test: divisible by 19\n" +
				"    If true: throw to monkey 2\n" +
				"    If false: throw to monkey 0\n" +
				"\n" +
				"Monkey 2:\n" +
				"  Starting items: 79, 60, 97\n" +
				"  Operation: new = old * old\n" +
				"  Test: divisible by 13\n" +
				"    If true: throw to monkey 1\n" +
				"    If false: throw to monkey 3\n" +
				"\n" +
				"Monkey 3:\n" +
				"  Starting items: 74\n" +
				"  Operation: new = old + 3\n" +
				"  Test: divisible by 17\n" +
				"    If true: throw to monkey 0\n" +
				"    If false: throw to monkey 1";
		answer = 10605;
		assertEquals(answer, Day11_MonkeyInTheMiddle.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

	}


	@Test
	void runB() {
		String input;
		long answer;

		input = "Monkey 0:\n" +
				"  Starting items: 79, 98\n" +
				"  Operation: new = old * 19\n" +
				"  Test: divisible by 23\n" +
				"    If true: throw to monkey 2\n" +
				"    If false: throw to monkey 3\n" +
				"\n" +
				"Monkey 1:\n" +
				"  Starting items: 54, 65, 75, 74\n" +
				"  Operation: new = old + 6\n" +
				"  Test: divisible by 19\n" +
				"    If true: throw to monkey 2\n" +
				"    If false: throw to monkey 0\n" +
				"\n" +
				"Monkey 2:\n" +
				"  Starting items: 79, 60, 97\n" +
				"  Operation: new = old * old\n" +
				"  Test: divisible by 13\n" +
				"    If true: throw to monkey 1\n" +
				"    If false: throw to monkey 3\n" +
				"\n" +
				"Monkey 3:\n" +
				"  Starting items: 74\n" +
				"  Operation: new = old + 3\n" +
				"  Test: divisible by 17\n" +
				"    If true: throw to monkey 0\n" +
				"    If false: throw to monkey 1";
		answer = 2713310158L;
		assertEquals(answer, Day11_MonkeyInTheMiddle.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
	}
}