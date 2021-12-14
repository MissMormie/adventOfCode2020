package days2021;

import helpers.CircularLinkedList;
import helpers.InputProvider;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day14_ExtendedPolymerization {
	public static int day = 14;
	public static int year = 2021;

	public static void main(String[] args) throws IOException {
		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
		System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day), 40).toString());
	}

	public static BigInteger runA(Stream<String> input) {
		return getMaxSubstractMinusNumberOfPolymersAfterStep(input, 10);
	}


	public static String start;
	public static BigInteger runB(Stream<String> input, int steps) {
		return getMaxSubstractMinusNumberOfPolymersAfterStep(input, steps);
	}

	private static BigInteger getMaxSubstractMinusNumberOfPolymersAfterStep(Stream<String> input, int steps) {
		Map<String, Combi> occurencesMap = new HashMap<>();
		input.forEach(line -> {
			if (line.contains("->")) {
				String[] split = line.split(" -> ");
				occurencesMap.put(split[0], new Combi(split[0], split[1]));
			} else if (line.contains("N")) {
				start = line;
			}
		});
		String finalChar = start.substring(start.length()-1);

		char[] chars = start.toCharArray();
		for(int i =1; i < chars.length; i++) {
			String combi = Character.toString(chars[i -1]) + Character.toString(chars[i]);
			occurencesMap.get(combi).count = occurencesMap.get(combi).count.add(BigInteger.ONE);
		}

		Set<String> combinations = occurencesMap.entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toSet());
		for(int i = 0; i < steps; i++) {
			combinations.forEach(combination -> {
				Combi combi = occurencesMap.get(combination);
				if(combi.count.compareTo(BigInteger.ZERO) > 0) {
					occurencesMap.get(combi.newCombi1).countToAdd = occurencesMap.get(combi.newCombi1).countToAdd.add(combi.count);
					occurencesMap.get(combi.newCombi2).countToAdd = occurencesMap.get(combi.newCombi2).countToAdd.add(combi.count);
				}
			});

			occurencesMap.entrySet().forEach(entry -> entry.getValue().addCount());
		}

		// get map of occurences of each polymer
		Map<String, BigInteger> polymerMap = new HashMap<>();
		combinations.forEach(combination -> {
			for (char c : combination.toCharArray()) {
				polymerMap.put(Character.toString(c), BigInteger.ZERO);
			}
		});

		// add last char of input string.
		polymerMap.put(finalChar, BigInteger.ONE);
		occurencesMap.entrySet().forEach(entry -> {
			String a = "" + entry.getValue().name.charAt(0);
			polymerMap.put(a, polymerMap.get(a).add(entry.getValue().count));
		});

		BigInteger min = polymerMap.entrySet().stream().map(Map.Entry::getValue).min(BigInteger::compareTo).get();
		BigInteger max = polymerMap.entrySet().stream().map(Map.Entry::getValue).max(BigInteger::compareTo).get();
		return max.subtract(min);
	}

	public static class Combi {
		BigInteger count = BigInteger.ZERO;
		String name;
		String newCombi1;
		String newCombi2;
		BigInteger countToAdd = BigInteger.ZERO;


		public Combi(String name, String inbetween) {
			this.name = name;
			newCombi1 = name.charAt(0) + inbetween;
			newCombi2 = inbetween + name.charAt(1);
		}

		public void addCount() {
			count = countToAdd;
			countToAdd = BigInteger.ZERO;
		}
	}

}
