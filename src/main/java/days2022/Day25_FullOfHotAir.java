package days2022;

import helpers.InputProvider;

import java.io.IOException;
import java.util.stream.Stream;

public class Day25_FullOfHotAir {

	public static int day = 25;
	public static int year = 2022;

	public static void main(String[] args) throws IOException {
		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
		System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
	}

	public static String runA(Stream<String> input) {
		long sum = input.map(Day25_FullOfHotAir::convertToDecimal)
				.mapToLong(i -> i)
				.sum();
		return convertToSnafu(sum);
	}

	public static String convertToSnafu(long number) {
		String s = Long.toString(number, 5);

		StringBuilder snafu =  new StringBuilder();
		int addToNext = 0;
		for (int i = s.length() -1; i >= 0; i--) {
			if(addToNext == 0) {
				switch (s.charAt(i)) {
					case '0' : snafu.append(0); break;
					case '1' : snafu.append(1); break;
					case '2' : snafu.append(2); break;
					case '3' : snafu.append("="); addToNext = 1; break;
					case '4' : snafu.append("-"); addToNext = 1; break;
				}
			} else {
				switch (s.charAt(i)) {
					case '0' : snafu.append(1); addToNext = 0; break;
					case '1' : snafu.append(2); addToNext = 0; break;
					case '2' : snafu.append("="); break;
					case '3' : snafu.append("-"); break;
					case '4' : snafu.append("0"); break;
				}
			}
		}

		if(addToNext == 1) {
			snafu.append(1);
		}

		return snafu.reverse().toString();
	}

	public static long convertToDecimal(String snafu) {
		char[] chars = snafu.toCharArray();
		long total = 0;
		for (int i = 0; i < chars.length; i++) {
			int position = chars.length - i -1;
			double add = Math.pow(5,position);
			switch (chars[i]) {
				case '=' : total += -2 * add; break;
				case '-' : total += -1 * add; break;
				case '1' : total += 1 * add; break;
				case '2' : total += 2 * add; break;
			}
		}

		return total;
	}

	public static int runB(Stream<String> input) {
		return 0;
	}

}
