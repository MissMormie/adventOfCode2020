package days2023;

import helpers.InputProvider;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day7CamelCards {

  public static int day = 7;
  public static int year = 2023;

  public static void main(String[] args) throws IOException {
    System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
    System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
  }

  public static int runA(Stream<String> input) {
    List<Hand> hands = input.map(Hand::new)
        .sorted()
        .collect(Collectors.toList());

    int winnings = 0;
    for (int i = 0; i < hands.size(); i++) {
      int bid = hands.get(i).bid;
      int value = bid * (i+1);
      winnings += hands.get(i).bid * (i + 1);
    }
    return winnings ;
  }


  public static int runB(Stream<String> input) {
    List<JokerHand> hands = input.map(JokerHand::new)
        .sorted()
        .collect(Collectors.toList());

    int winnings = 0;
    for (int i = 0; i < hands.size(); i++) {
      int bid = hands.get(i).bid;
      int value = bid * (i+1);
      winnings += hands.get(i).bid * (i + 1);
    }
    return winnings ;
  }


  public static class Hand implements Comparable<Hand>{
    int bid;
  long strength;
    public Hand(String line) {
      String[] split = line.split(" ");
      bid = Integer.parseInt(split[1]);
      Map<String, Long>
          output = Arrays.stream(split[0].split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

      List<Long> collect = output.values().stream().sorted().collect(Collectors.toList());
      Collections.reverse(collect);
      if(collect.get(0) == 5L) { // five of a kind
        strength = 60_000_000_000L;
      } else if(collect.get(0) == 4L) { // four of a kind
        strength = 50_000_000_000L;
      } else if(collect.get(0) == 3L && collect.get(1) == 2L) { // full house
        strength = 40_000_000_000L;
      } else if(collect.get(0) == 3L) {   // three of a kind
        strength = 30_000_000_000L;
      } else if(collect.get(0) == 2L && collect.get(1) == 2L) { // two pair
        strength = 20_000_000_000L;
      } else if(collect.get(0) == 2L) { // one pair
        strength = 10_000_000_000L;
      }

      char[] charArray = split[0].toCharArray();
      for(int i = 0; i < 5; i++) {
        long multiplier = Math.round(Math.pow(10, 8 - i * 2));
        char card = charArray[i];
        if (Character.isDigit(card)) {
          strength += Character.getNumericValue(card) * multiplier;
        }
        else {
          switch (card) {
            case 'T':
              strength += 10 * multiplier;
              break;
            case 'J':
              strength += 11 * multiplier;
              break;
            case 'Q':
              strength += 12 * multiplier;
              break;
            case 'K':
              strength += 13 * multiplier;
              break;
            case 'A':
              strength += 14 * multiplier;
              break;
          }
        }
      }

    }

    @Override
    public int compareTo(Hand o) {
      return Long.compare(this.strength, o.strength);
    }

  }

  public static class JokerHand implements Comparable<JokerHand>{
    int bid;
    long strength;
    public JokerHand(String line) {
      String[] split = line.split(" ");
      bid = Integer.parseInt(split[1]);
      Map<String, Long>
          output = Arrays.stream(split[0].split(""))
          .filter(c -> !c.equals("J"))
          .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

      Collection<Long> values = output.values();
      long numOfJs = 5 - values.stream().reduce(0L, Long::sum);

      List<Long> collect = values.stream().sorted().collect(Collectors.toList());
      Collections.reverse(collect);

      if(numOfJs == 5) {
        collect.add(0l);
      }

      collect.set(0, collect.get(0) + numOfJs);
      if(collect.get(0) == 5L) { // five of a kind
        strength = 60_000_000_000L;
      } else if(collect.get(0) == 4L) { // four of a kind
        strength = 50_000_000_000L;
      } else if(collect.get(0) == 3L && collect.get(1) == 2L) { // full house
        strength = 40_000_000_000L;
      } else if(collect.get(0) == 3L) {   // three of a kind
        strength = 30_000_000_000L;
      } else if(collect.get(0) == 2L && collect.get(1) == 2L) { // two pair
        strength = 20_000_000_000L;
      } else if(collect.get(0) == 2L) { // one pair
        strength = 10_000_000_000L;
      }

      char[] charArray = split[0].toCharArray();
      for(int i = 0; i < 5; i++) {
        long multiplier = Math.round(Math.pow(10, 8 - i * 2));
        char card = charArray[i];
        if (Character.isDigit(card)) {
          strength += Character.getNumericValue(card) * multiplier;
        }
        else {
          switch (card) {
            case 'T':
              strength += 10 * multiplier;
              break;
            case 'J':
              strength += 0 * multiplier;
              break;
            case 'Q':
              strength += 12 * multiplier;
              break;
            case 'K':
              strength += 13 * multiplier;
              break;
            case 'A':
              strength += 14 * multiplier;
              break;
          }
        }
      }

    }

    @Override
    public int compareTo(JokerHand o) {
      return Long.compare(this.strength, o.strength);
    }

  }

}
