package days2022;

import com.google.common.collect.Iterators;
import helpers.InputProvider;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day11_MonkeyInTheMiddle {
	public static int day = 11;

	public static int year = 2022;

	public static void main(String[] args) throws IOException {
		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day))); // not 56168
		System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
	}

	public static long runA(Stream<String> input) {
		return playGame(input, 20, true);
	}

	public static long runB(Stream<String> input) {
		return playGame(input, 10000, false);
	}

	private static long playGame(Stream<String> input, int endExclusive, boolean reduceWorry) {
		// create monkey objects
		Map<Integer, Monkey> monkeyMap = new HashMap<>();
		Iterators.partition(input.iterator(), 7).forEachRemaining(i -> new Monkey(i, monkeyMap));

		// get the multiple of all division numbers
		long modulo = monkeyMap.values().stream().map(monkey -> monkey.divisible)
				.mapToLong(i -> i).reduce(1, (a, b) -> a * b);

		// play game
		IntStream.range(0, endExclusive)
				.forEach(i -> monkeyMap.values()
						.forEach(monkey -> {
							while (monkey.playWithItem(monkeyMap, reduceWorry, modulo)) {
							}
						}));

		// get the answer
		// This isn't efficient at all. Except for me writing the code ;)
		List<Integer> collect = monkeyMap.values().stream().map(monkey -> monkey.inspected).collect(Collectors.toList());

		long max1 = collect.stream().mapToLong(i -> i).max().getAsLong();
		long max2 = collect.stream().mapToLong(i -> i).filter(i -> i != max1).max().getAsLong();
		return max1 * max2;
	}

	private static class Monkey {
		final int divisible;

		final Deque<Long> items = new ArrayDeque<>();

		final Function<Long, Long> operation;

		final int targetIfFalse;

		final int targetIfTrue;

		String operationText;

		private int inspected;

		public Monkey(List<String> input, Map<Integer, Monkey> monkeyMap) {
			monkeyMap.put(Integer.parseInt(input.get(0).split("[^\\d]+")[1]), this);
			Arrays.stream(input.get(1).split("[^\\d]+")).forEach(i -> {
				if (!"".equals(i)) items.add(Long.parseLong(i));
			});
			divisible = Integer.parseInt(input.get(3).split("[^\\d]+")[1]);
			targetIfTrue = Integer.parseInt(input.get(4).split("[^\\d]+")[1]);
			targetIfFalse = Integer.parseInt(input.get(5).split("[^\\d]+")[1]);
			operationText = input.get(2);

			if (input.get(2).equals("  Operation: new = old * old")) {
				operation = (Long old) -> old * old;
			} else if (input.get(2).startsWith("  Operation: new = old *")) {
				String[] split = input.get(2).split("[^\\d]+");
				operation = (Long old) -> old * Long.parseLong(split[1]);
			} else if (input.get(2).equals("  Operation: new = old + old")) {
				operation = (Long old) -> old + old;
			} else if (input.get(2).startsWith("  Operation: new = old +")) {
				String[] split = input.get(2).split("[^\\d]+");
				operation = (Long old) -> old + Long.parseLong(split[1]);
			} else {
				throw new IllegalStateException(input.get(2));
			}
		}

		public void receiveItem(Long item) {
//			System.out.println(item);
			items.add(item);
		}

		private boolean playWithItem(Map<Integer, Monkey> monkeyMap, boolean reduceWorry, long modulo) {
			if (items.isEmpty()) {
				return false;
			}

//			Monkey inspects an item, increases worry level
			Long itemWithWorryLevel = operation.apply(items.removeFirst());
			if (reduceWorry) {
				// monkey gets bored, divide worry level by 3
				itemWithWorryLevel /= 3;
			}

			// Pass it to next monkey
			if (itemWithWorryLevel % divisible == 0) {
				monkeyMap.get(targetIfTrue).receiveItem(itemWithWorryLevel % modulo);
			} else {
				monkeyMap.get(targetIfFalse).receiveItem(itemWithWorryLevel % modulo);
			}
			inspected++;
			return true;
		}


	}
}
