package days;

import helpers.CircularLinkedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;

public class Day22_CrabCombat {
	public static void main(String[] args) {
		System.out.println("answer A: " + runA(textInputPlayer1(), textInputPlayer2()));
		System.out.println("answer B: " + runB(textInputPlayer1(), textInputPlayer2()));	// 33535 high
	}

	public static long runA(String player1Input, String player2Input) {
		CircularLinkedList<Long> handPlayer1 = createHand(player1Input);
		CircularLinkedList<Long> handPlayer2 = createHand(player2Input);

		while(handPlayer1.size() > 0 && handPlayer2.size() > 0 ) {
			playRoundA(handPlayer1, handPlayer2);
		}

		if(handPlayer1.size() == 0) {
			return calculateScore(handPlayer2);
		}
		return calculateScore(handPlayer1);
	}

	private static long calculateScore(CircularLinkedList<Long> winningHand) {
		long score = 0;
		for(int i = 1; i <= winningHand.size(); i++) {
			score += winningHand.getPrevious() * i;
		}
		return score;
	}

	private static CircularLinkedList<Long> createHand(String input) {
		CircularLinkedList<Long> hand = new CircularLinkedList<>();
		Arrays.stream(input.split("\n")).forEach(s -> hand.addObject(Long.parseLong(s)));
		hand.next();
		return hand;
	}

	public static void playRoundA(CircularLinkedList<Long> handPlayer1, CircularLinkedList<Long> handPlayer2) {
		CircularLinkedList<Long> winner = handPlayer1;
		CircularLinkedList<Long> loser = handPlayer2;

		if(handPlayer1.getCurrent() < handPlayer2.getCurrent()) {
			winner = handPlayer2;
			loser = handPlayer1;
		}

		winner.addObject(loser.removeCurrent());
		winner.next();
	}

	public static String PLAYER1 = "player1";
	public static String PLAYER2 = "player2";

	public static String playGameB(CircularLinkedList<Long> handPlayer1, CircularLinkedList<Long> handPlayer2) {
		List<String> previousHands1 = new ArrayList<>();
		List<String> previousHands2 = new ArrayList<>();
			try {
				while(handPlayer1.size() > 0 && handPlayer2.size() > 0 ) {
					playRoundB(handPlayer1, handPlayer2, previousHands1, previousHands2);
				}
			} catch (TimeoutException e) {
				return PLAYER1;
			}

		if(handPlayer1.size() == 0) {
			return PLAYER2;
		}
		return PLAYER1;
	}


	public static void playRoundB(CircularLinkedList<Long> handPlayer1, CircularLinkedList<Long> handPlayer2,
								  List<String> previousHands1, List<String> previousHands2) throws TimeoutException {
		// Before either player deals a card, if there was a previous round in this game that had exactly the same cards
		// in the same order in the same players' decks, the game instantly ends in a win for player 1. Previous rounds
		// from other games are not considered.
		String stringFromHandPlayer1 = getStringFromHand(handPlayer1);
		String stringFromHandPlayer2 = getStringFromHand(handPlayer2);
		if(previousHands1.contains(stringFromHandPlayer1) && previousHands2.contains(stringFromHandPlayer2)) {
			throw new TimeoutException(PLAYER1);
		}
		previousHands1.add(stringFromHandPlayer1);
		previousHands2.add(stringFromHandPlayer2);

		// Otherwise, this round's cards must be in a new configuration; the players begin the round by each drawing the top card of their deck as normal.
		// If both players have at least as many cards remaining in their deck as the value of the card they just drew,
		// the winner of the round is determined by playing a new game of Recursive Combat (see below).
		Long cardPlayer1 = handPlayer1.getCurrent();
		Long cardPlayer2 = handPlayer2.getCurrent();

		CircularLinkedList<Long> winner = handPlayer1;
		CircularLinkedList<Long> loser = handPlayer2;

		// -1 for the card just drawn.
		if(handPlayer1.size() -1 >= cardPlayer1 && handPlayer2.size() -1 >= cardPlayer2) {
			// + 1 because the card drawn is still in there.
			CircularLinkedList<Long> clone1 = cloneNumberOfCards(handPlayer1, cardPlayer1);
			CircularLinkedList<Long> clone2 = cloneNumberOfCards(handPlayer2, cardPlayer2);

			String winnerSubgame = playGameB(clone1, clone2);

			if(winnerSubgame == PLAYER2) {
				winner = handPlayer2;
				loser = handPlayer1;
			}
		} else {
			// Otherwise, at least one player must not have enough cards left in their deck to recurse;
			// the winner of the round is the player with the higher-value card.
			if(cardPlayer1 < cardPlayer2) {
				winner = handPlayer2;
				loser = handPlayer1;
			}
		}

		winner.addObject(loser.removeCurrent());
		winner.next();
	}

	public static CircularLinkedList<Long> cloneNumberOfCards(CircularLinkedList<Long> hand, long numCards) {
		CircularLinkedList<Long> partialClone = new CircularLinkedList<>();
		for(int i = 0; i < numCards; i++) {
			partialClone.addObject(hand.getNext());
		}
		partialClone.next();
		hand.previous((int) numCards);
		return partialClone;
	}

	public static String getStringFromHand(CircularLinkedList<Long> hand) {
		StringBuilder sb = new StringBuilder();
		hand.previous();
		IntStream.range(0, hand.size()).forEach(i -> sb.append(hand.getNext() + "-"));
		hand.next();
		return sb.toString();
	}

	public static long runB(String player1Input, String player2Input) {
		CircularLinkedList<Long> handPlayer1 = createHand(player1Input);
		CircularLinkedList<Long> handPlayer2 = createHand(player2Input);

		String winner = playGameB(handPlayer1, handPlayer2);

		if(winner.equals(PLAYER1)) {
			return calculateScore(handPlayer1);
		}
		return calculateScore(handPlayer2);
	}

	private static String textInputPlayer1() {
		return "40\n" +
				"26\n" +
				"44\n" +
				"14\n" +
				"3\n" +
				"17\n" +
				"36\n" +
				"43\n" +
				"47\n" +
				"38\n" +
				"39\n" +
				"41\n" +
				"23\n" +
				"28\n" +
				"49\n" +
				"27\n" +
				"18\n" +
				"2\n" +
				"13\n" +
				"32\n" +
				"29\n" +
				"11\n" +
				"25\n" +
				"24\n" +
				"35";
	}

	private static String textInputPlayer2() {
		return "19\n" +
				"15\n" +
				"48\n" +
				"37\n" +
				"6\n" +
				"34\n" +
				"8\n" +
				"50\n" +
				"22\n" +
				"46\n" +
				"20\n" +
				"21\n" +
				"10\n" +
				"1\n" +
				"33\n" +
				"30\n" +
				"4\n" +
				"5\n" +
				"7\n" +
				"31\n" +
				"12\n" +
				"9\n" +
				"45\n" +
				"42\n" +
				"16";
	}
}
