package days2021;

import helpers.InputProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day21_DiracDice {
	public static int day = 21;
	public static int year = 2021;

	public static void main(String[] args) throws IOException {
		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
		System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
	}

	public static int runA(Stream<String> input) {
		List<Integer> collect = input.map(in -> Integer.parseInt(in.substring(in.lastIndexOf(" ") + 1))).collect(Collectors.toList());
		int score1 = 0;
		int score2 = 0;
		int position1 = collect.get(0);
		int position2 = collect.get(1);
		int losingScore= 0;
		while(true) {
			position1 = (position1 + doDiceRoll() + doDiceRoll() + doDiceRoll()) % 10;
			if(position1 == 0) {
				position1 = 10;
			}
			score1 += position1;
			if(score1 >= 1000) {
				losingScore = score2;
				break;
			}
			position2 = (position2 + doDiceRoll() + doDiceRoll() + doDiceRoll()) % 10;
			if(position2 == 0) {
				position2 = 10;
			}

			score2 += position2;
			if(score2 >= 1000) {
				losingScore = score1;
				break;
			}
		}
		return losingScore * diceRolls;
		// too high 1185480
	}

	public static int diceNumber = 0;
	public static int diceRolls = 0;
	public static int doDiceRoll() {
		diceRolls++;
		if(++diceNumber > 100) {
			diceNumber = 1;
		}
		return diceNumber;
	}

	public static long runB(Stream<String> input) {
		setPermutations();
		List<Integer> collect = input.map(in -> Integer.parseInt(in.substring(in.lastIndexOf(" ") + 1))).collect(Collectors.toList());
		WinScore winScore = playTurns(collect.get(0), 0, collect.get(1), 0, true);
		if(winScore.win1 > winScore.win2) {
			return winScore.win1;
		}
		return winScore.win2;
	}

	public static void setPermutations() {
		List<Integer> permutationsList = new ArrayList<>();
		for (int i = 1; i < 4; i++) {
			for (int j= 1; j < 4; j++) {
				for (int k= 1; k < 4; k++) {
					permutationsList.add(i+j+k);
				}
			}
		}
		permutations = permutationsList.stream().collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));
	}

	static Map<Integer, Long> permutations;

	public static Map<String, WinScore> winScoresMap = new HashMap<>();
	public static int winningScore = 21;

	public static WinScore playTurns(int position1, int score1, int position2, int score2, boolean player1Turn) {
		if(winScoresMap.containsKey(WinScore.getId(score1, score2, position1, position2, player1Turn))) {
			return winScoresMap.get(WinScore.getId(score1, score2, position1, position2, player1Turn));
		}

		Stream<WinScore> wins;
		if (player1Turn) {
			wins = permutations.entrySet().stream().map(entry -> {
				int newPosition = (position1 + entry.getKey()) % 10;
				if (newPosition == 0) {
					newPosition = 10;
				}
				if (newPosition + score1 >= winningScore) {
					return new WinScore(score1, score2, position1, position2, entry.getValue(), 0, true);
				}
				WinScore winScore = playTurns(newPosition, score1 + newPosition, position2, score2, false);
				return new WinScore(winScore.win1 * entry.getValue(), winScore.win2 * entry.getValue());
			});
		} else {
			wins = permutations.entrySet().stream().map( entry -> {
				int newPosition = (position2 + entry.getKey()) % 10 ;
				if(newPosition == 0) {
					newPosition = 10;
				}
				if(newPosition + score2 >= winningScore) {
					return new WinScore(score1, score2, position1, position2, 0, entry.getValue(),false);
				}
				WinScore winScore = playTurns(position1, score1, newPosition, score2 + newPosition, true);
				return new WinScore(winScore.win1 * entry.getValue(), winScore.win2 * entry.getValue());
			});
		}
		WinScore winScore = new WinScore(score1, score2, position1, position2, 0, 0, player1Turn);
		wins.forEach(scores ->{
			winScore.win1 += scores.win1;
			winScore.win2 += scores.win2;
		});
		winScoresMap.put(winScore.id, winScore);
		return winScore;
	}

	public static class WinScore {
		String id;
		long win1;
		long win2;

		public WinScore(long win1, long win2) {
			this.win1 = win1;
			this.win2 = win2;
		}

		public WinScore(int score1, int score2, int position1, int position2, long win1, long win2, boolean player1Turn) {
			id = getId(score1, score2, position1, position2, player1Turn);
			this.win1 = win1;
			this.win2 = win2;
		}

		public static String getId(int score1, int score2, int position1, int position2, boolean player1Turn) {
			return score1 + "-" + score2 + "-" + position1 + "-" + position2 + "-" + player1Turn;
		}


	}
}
