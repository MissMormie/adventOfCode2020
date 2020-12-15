package days;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Day15_RambunctiousRecitation {
	public static void main(String[] args) {
		System.out.println("answer A: " + runA(textInput()));
		System.out.println("answer B: " + runB(textInput()));
	}

	public static long runA(String input) {
		return getNumberSpokenAt(input, 2020l);
	}

	public static long getNumberSpokenAt(String input, Long number) {
		Map<Long, Long> spokenNumbers = new HashMap<>(); // number, index spoken
		String[] split = input.split(",");
		// doesnt add the last number to map so we can check if that's a repeat.
		IntStream.range(0, split.length - 1)
				.forEach(i -> spokenNumbers.put(Long.parseLong(split[i]), new Long(i)));

		long lastNumber = getNextNumber(spokenNumbers, Long.parseLong(split[split.length - 1]), split.length);
		long nextNumber;
		spokenNumbers.put(Long.parseLong(split[split.length - 1]), new Long(split.length - 1));

		for (long i = split.length; i < number - 1; i++) {
			nextNumber = getNextNumber(spokenNumbers, lastNumber, i);
			spokenNumbers.put(lastNumber, i);
			lastNumber = nextNumber;
		}
		return lastNumber;
	}

	public static long runB(String input) {
		return getNumberSpokenAt(input, 30_000_000l);
	}

	public static String textInput() {
		return "9,12,1,4,17,0,18";
	}

	private static long getNextNumber(Map<Long, Long> spokenNumbers, Long lastNumber, long index) {
		if (!spokenNumbers.containsKey(lastNumber)) {
			return 0;
		} else {
			return index - spokenNumbers.get(lastNumber);
		}
	}
}
