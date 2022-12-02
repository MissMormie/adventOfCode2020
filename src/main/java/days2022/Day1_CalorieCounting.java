package days2022;

import helpers.InputProvider;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day1_CalorieCounting {
	public static int day = 1;

	public static int year = 2022;

	public static void main(String[] args) throws IOException {
		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
		System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
	}

	public static int runA(Stream<String> input) {
		List<String> collect = input.collect(Collectors.toList());
		int max = 0;
		int current = 0;
		for (String calorie : collect) {
			if (StringUtils.isNotBlank(calorie)) {
				current += Integer.parseInt(calorie);
			} else {
				if (current > max) {
					max = current;
				}
				current = 0;
			}
		}
		return max;
	}


	public static int runB(Stream<String> input) {
		List<String> collect = input.collect(Collectors.toList());
		List<Integer> caloriesCarried = new ArrayList<>();
		int current = 0;
		for (String calorie : collect) {
			if (StringUtils.isNotBlank(calorie)) {
				current += Integer.parseInt(calorie);
			} else {
				caloriesCarried.add(current);
				current = 0;
			}
		}
		caloriesCarried.add(current);

		caloriesCarried.sort(Integer::compareTo);
		List<Integer> top3 = caloriesCarried.subList(caloriesCarried.size() - 3, caloriesCarried.size());
		return top3.stream().mapToInt(i -> i).sum();
	}
}
