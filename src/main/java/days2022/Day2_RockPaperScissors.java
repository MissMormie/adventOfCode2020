package days2022;

import helpers.InputProvider;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

public class Day2_RockPaperScissors {
	public static int day = 2;

	public static int year = 2022;

	/**
	 * X means you need to lose, Y means you need to end the round in a draw, and Z means you need to win. Good luck!"
	 */
	public static int getScore(Hand otherHand, char result) {
		Hand own;
		if (result == 'X') {
			own = otherHand.getLoser();
		} else if (result == 'Y') {
			own = otherHand;
		} else {
			own = otherHand.getWinner();
		}
		return own.getScore(otherHand);
	}

	public static void main(String[] args) throws IOException {
		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
		System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
	}

	public static int runA(Stream<String> input) {
		return input.map(line -> Hand.getHand(line.charAt(2)).getScore(Hand.getHand(line.charAt(0))))
				.mapToInt(i -> i)
				.sum();
	}

	public static int runB(Stream<String> input) {
		return input.map(line -> getScore(Hand.getHand(line.charAt(0)), line.charAt(2)))
				.mapToInt(i -> i)
				.sum();
	}

	public enum Hand {
		ROCK(1, "SCISSOR"), PAPER(2, "ROCK"), SCISSOR(3, "PAPER");

		final int score;

		final String winsOf;

		Hand(int score, String winsOf) {
			this.score = score;
			this.winsOf = winsOf;
		}

		static Hand getHand(char c) {
			if (c == 'A' || c == 'X') {
				return ROCK;
			}
			if (c == 'B' || c == 'Y') {
				return PAPER;
			}
			return SCISSOR;
		}

		Hand getLoser() {
			// meh. Shouldn't calculate this everytime, but good enough.
			return Arrays.stream(Hand.values())
					.filter(value -> value.toString().equals(winsOf))
					.findAny().get();
		}

		/**
		 * The score for a single round is the score for the shape you selected (1 for Rock, 2 for Paper, and 3 for Scissors)
		 * plus the score for the outcome of the round (0 if you lost, 3 if the round was a draw, and 6 if you won).
		 */
		int getScore(Hand otherHand) {
			if (this.equals(otherHand)) {
				return 3 + score;
			}
			if (getLoser().equals(otherHand)) {
				return 6 + score; // won
			}
			return score; // lost
		}

		Hand getWinner() {
			return Arrays.stream(Hand.values())
					.filter(value -> !value.toString().equals(winsOf) && !value.equals(this))
					.findAny().get();
		}
	}
}
