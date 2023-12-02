package days2023;

import helpers.InputProvider;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day1Trebuchet {
  public static int day = 1;
  public static int year = 2023;

  public static void main(String[] args) throws IOException {
//    System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
    System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day))); // 55266 too low
  }

  public static int runA(Stream<String> input) {
    return input.map(i -> i.chars().
        filter(Character::isDigit)
        .map(Character::getNumericValue)
        .boxed()
        .collect(Collectors.toList()))
      .map(list -> list.get(0) * 10 + list.get(list.size()-1))
        .reduce(0, Integer::sum);
  }


  public static int runB(Stream<String> input) {
    return runA(input.map(i ->
       i.replaceAll("one","one1one")
      .replaceAll("two","two2two")
      .replaceAll("three","three3three")
      .replaceAll("four","four4four")
      .replaceAll("five","five5five")
      .replaceAll("six","six6six")
      .replaceAll("seven","seven7seven")
      .replaceAll("eight","eight8eight")
      .replaceAll("nine","nine9nine")
    ));
  }
}
