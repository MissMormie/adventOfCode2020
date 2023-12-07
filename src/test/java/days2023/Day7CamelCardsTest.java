package days2023;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day7CamelCardsTest {
  	@Test
  	void runA() {
  		String input;
  		int answer;

  		input = "32T3K 765\n" +
              "T55J5 684\n" +
              "KK677 28\n" +
              "KTJJT 220\n" +
              "QQQJA 483";
  		answer = 6440;
  		assertEquals(answer, Day7CamelCards.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

			Day7CamelCards.Hand hand = new Day7CamelCards.Hand("11111 12");
			assertEquals(60_101_010_101L, hand.strength);
			Day7CamelCards.Hand hand2 = new Day7CamelCards.Hand("AAAAA 12");
			assertEquals(61_414_141_414L, hand2.strength);

		}


  	@Test
  	void runB() {
  		String input;
  		int answer;

			input = "32T3K 765\n" +
							"T55J5 684\n" +
							"KK677 28\n" +
							"KTJJT 220\n" +
							"QQQJA 483";
  		answer = 5905;
  		assertEquals(answer, Day7CamelCards.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
  	}
}