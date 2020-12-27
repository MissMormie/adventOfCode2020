package days;

import java.util.HashMap;
import java.util.Map;

public class Day23_CrabCups {

	public static void main(String[] args) {
		System.out.println("answer A: " + runA(textInput()));
		long startTime = System.nanoTime();
		System.out.println("answer B: " + runB(textInput())); // 474747880250
		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;

		System.out.println("Execution time in milliseconds : " + timeElapsed / 1000000);
	}

	public static String runA(String input) {
		Map<Integer, Integer> cupMap = new HashMap<>(); // first int is number of cup, second is number of next cup.
		for (int i = 0; i < input.length() - 1; i++) {
			cupMap.put(input.charAt(i) - 48, input.charAt(i + 1) - 48);
		}
		// add last cup
		cupMap.put(input.charAt(input.length() - 1) - 48, input.charAt(0) - 48);

		playGame(cupMap, input.charAt(0) - 48, 100);

		Integer temp = 1;
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < cupMap.size() - 1; j++) {
			temp = cupMap.get(temp);
			sb.append(temp);
		}

		return sb.toString();
	}

	public static Long runB(String input) {
		Map<Integer, Integer> cupMap = new HashMap<>(); // first int is number of cup, second is number of next cup.

		// Add the numbered cups
		for (int i = 0; i < input.length() - 1; i++) {
			cupMap.put(input.charAt(i) - 48, input.charAt(i + 1) - 48);
		}
		// add intermediate  cup
		cupMap.put(input.charAt(input.length() - 1) - 48, input.length() + 1);

		// fill up cups to 1 million
		for (int i = input.length() + 1; i <= 1_000_000 - 1; i++) {
			cupMap.put(i, i + 1);
		}

		// update last cup
		cupMap.put(1_000_000, input.charAt(0) - 48);

		playGame(cupMap, input.charAt(0) - 48, 10_000_000);

		Integer nextToOne = cupMap.get(1);
		return 1l * nextToOne * cupMap.get(nextToOne);
	}

	private static void playGame(Map<Integer, Integer> cupMap, Integer currentCup, long rounds) {
		for (int i = 0; i < rounds; i++) {

			// The crab picks up the three cups that are immediately clockwise of the current cup.
			// They are removed from the circle; cup spacing is adjusted as necessary to maintain the circle.
			Integer cup1 = cupMap.get(currentCup);
			Integer cup2 = cupMap.get(cup1);
			Integer cup3 = cupMap.get(cup2);

			// set next cup for the current cup as the 4th cup from the current.
			cupMap.put(currentCup, cupMap.get(cup3));

			// The crab selects a destination cup: the cup with a label equal to the current cup's label minus one.
			// If this would select one of the cups that was just picked up, the crab will keep subtracting one until it
			// finds a cup that wasn't just picked up. If at any point in this process the value goes below the lowest value
			// on any cup's label, it wraps around to the highest value on any cup's label instead.
			Integer destination = currentCup - 1;
			while (destination < 1 || destination.equals(cup1) || destination.equals(cup2) || destination.equals(cup3) || destination.equals(currentCup)) {
				if (destination < 1) {
					destination = cupMap.size(); // the three removed cups
				} else {
					destination -= 1;
				}
			}

			// The crab places the cups it just picked up so that they are immediately clockwise of the destination cup.
			// They keep the same order as when they were picked up.
			Integer afterDestination = cupMap.get(destination);
			cupMap.put(destination, cup1);
			cupMap.put(cup3, afterDestination);

			// The crab selects a new current cup: the cup which is immediately clockwise of the current cup.
			currentCup = cupMap.get(currentCup);
		}
	}

	private static String textInput() {
		return "193467258";
	}
}
