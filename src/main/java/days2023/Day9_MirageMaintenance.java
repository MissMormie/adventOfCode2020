package days2023;

import helpers.InputProvider;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

public class Day9_MirageMaintenance {

  public static int day = 9;
  public static int year = 2023;

  public static void main(String[] args) throws IOException {
    System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day))); // too high 1581679991
    System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
  }

  public static long runA(Stream<String> input) {
    return input.map(line -> {
          Long[] array = Arrays.stream(line.split(" "))
              .map(Long::parseLong)
              .toArray(Long[]::new);
          return array[array.length - 1] + getExtrapolatedValue(array);
        })
        .reduce(Long::sum).orElse(0l);
  }

  public static long getExtrapolatedValue(Long[] numbers) {
    Long[] differences = new Long[numbers.length - 1];
    for (int i = 0; i < numbers.length - 1; i++) {
      differences[i] = numbers[i + 1] - numbers[i];
    }
    if (differences[differences.length - 1] == 0) {
      return 0;
    }
    return getExtrapolatedValue(differences) + differences[differences.length - 1];
  }

  public static int runB(Stream<String> input) {
    return 0;
  }

}
