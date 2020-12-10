package days;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static helpers.StringHelper.getListOfNumbersSeperatedBy;

public class Day10_Adapter_Array {

	public static void main(String[] args) {

		System.out.println("answer A: " + runA(textInput())); // 1825 low
		long startTime = System.nanoTime();
		System.out.println("answer B: " + runB(textInput()));
		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;

		System.out.println("Execution time in milliseconds : " + timeElapsed / 1000000);
	}

	public static int runA(String input) {
		List<Integer> nums = getListOfNumbersSeperatedBy(input, "\n");
		nums.add(0); // charger outlet
		List<Integer> collect = nums.stream().sorted(Integer::compare)
				.collect(Collectors.toList());
		collect.add(collect.get(collect.size() - 1) + 3);

		int oneDifference = 0;
		int threeDifference = 0;
		for (int index = 0; index < collect.size() - 1; index++) {
			int joltDifference = collect.get(index + 1) - collect.get(index);
			if (joltDifference == 1) {
				oneDifference++;
			} else if (joltDifference == 3) {
				threeDifference++;
			}
		}
		return threeDifference * oneDifference;
	}

	public static long runB(String input) {
		List<Integer> nums = getListOfNumbersSeperatedBy(input, "\n");
		nums.add(0); // charging outlet
		nums.sort(Integer::compareTo);
		nums.add(nums.get(nums.size() - 1) + 3); // add final
		Map<Integer, Adapter> adapterMap = nums.stream().map(num -> new Adapter(num)).collect(Collectors.toMap(adapter -> adapter.jolts, adapter -> adapter));
		adapterMap.values().forEach(adapter -> adapter.setPossibleConnections(adapterMap));
		return adapterMap.get(0).getNumPossibilities();
	}

	private static class Adapter {
		List<Adapter> possibleConnections = new ArrayList<>();
		Integer jolts;
		Long permutations;

		public Adapter(int jolts) {
			this.jolts = jolts;
		}

		public void setPossibleConnections(Map<Integer, Adapter> adapterMap) {
			IntStream.rangeClosed(1, 3).forEach(index -> {
				if( adapterMap.containsKey(jolts + index)) {
					possibleConnections.add(adapterMap.get(jolts + index));
				}
			});
		}

		public Long getNumPossibilities() {
			if(permutations != null) {
				return permutations;
			}

			permutations = possibleConnections.stream()
					.mapToLong(Adapter::getNumPossibilities)
					.sum();

			if(possibleConnections.isEmpty()) {
				permutations = 1l;
			}

			return permutations;
		}
	}

	private static String textInput() {
		return "46\n" +
				"63\n" +
				"21\n" +
				"115\n" +
				"125\n" +
				"35\n" +
				"89\n" +
				"17\n" +
				"116\n" +
				"90\n" +
				"51\n" +
				"66\n" +
				"111\n" +
				"142\n" +
				"148\n" +
				"60\n" +
				"2\n" +
				"50\n" +
				"82\n" +
				"20\n" +
				"47\n" +
				"24\n" +
				"80\n" +
				"101\n" +
				"103\n" +
				"16\n" +
				"34\n" +
				"72\n" +
				"145\n" +
				"141\n" +
				"124\n" +
				"14\n" +
				"123\n" +
				"27\n" +
				"62\n" +
				"61\n" +
				"95\n" +
				"138\n" +
				"29\n" +
				"7\n" +
				"149\n" +
				"147\n" +
				"104\n" +
				"152\n" +
				"22\n" +
				"81\n" +
				"11\n" +
				"96\n" +
				"97\n" +
				"30\n" +
				"41\n" +
				"98\n" +
				"59\n" +
				"45\n" +
				"88\n" +
				"37\n" +
				"10\n" +
				"114\n" +
				"110\n" +
				"4\n" +
				"56\n" +
				"122\n" +
				"139\n" +
				"117\n" +
				"108\n" +
				"91\n" +
				"36\n" +
				"146\n" +
				"131\n" +
				"109\n" +
				"31\n" +
				"75\n" +
				"70\n" +
				"140\n" +
				"38\n" +
				"121\n" +
				"3\n" +
				"28\n" +
				"118\n" +
				"54\n" +
				"107\n" +
				"84\n" +
				"15\n" +
				"76\n" +
				"71\n" +
				"102\n" +
				"130\n" +
				"132\n" +
				"87\n" +
				"55\n" +
				"129\n" +
				"83\n" +
				"23\n" +
				"42\n" +
				"69\n" +
				"1\n" +
				"77\n" +
				"135\n" +
				"128\n" +
				"94";
	}
}
