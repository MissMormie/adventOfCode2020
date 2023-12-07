package days2023;

import helpers.InputProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day4ScratchCards {

  public static int day = 4;
  public static int year = 2023;

  public static void main(String[] args) throws IOException {
    System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day), 12, 38));
    System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day),12, 38 ));
  }

  public static int runA(Stream<String> input, int lowSplit, int highSplit) {
    return input.map(line -> {
          String[] split = line.split("\\s+");
          ArrayList<String> winningNumbers = new ArrayList<>();

          for (int i = 2; i < lowSplit; i++) {
            winningNumbers.add(split[i]);
          }
          int score = 0;
          for (int i = lowSplit + 1; i < highSplit; i++) {
            if (winningNumbers.contains(split[i])) {
              if (score == 0) {
                score = 1;
              }
              else {
                score *= 2;
              }
            }
          }

          return score;
        })
        .reduce(0, Integer::sum);
  }

  public static int runB(Stream<String> input, int lowSplit, int highSplit) {
    Map<Integer, ScratchCard> scratchCards = input.map((String line) -> new ScratchCard(line, lowSplit, highSplit))
        .collect(Collectors.toMap(ScratchCard::getNumCard, Function.identity()));

    return scratchCards.values().stream()
        .map((scratchCard) -> scratchCard.getScratchCardsWonIncludingCurrent(scratchCards))
        .reduce(0, Integer::sum);
  }

  public static class ScratchCard {
    public Integer scratchCardsWon;

    public int numCard;

    public Integer getNumCard() {
      return numCard;
    }

    public int matching = 0;

    public ScratchCard(String line, int lowSplit, int highSplit) {
      String[] split = line.split("\\s+");
      ArrayList<String> winningNumbers = new ArrayList<>();
      numCard = Integer.parseInt(split[1].substring(0, split[1].length() - 1));

      for (int i = 2; i < lowSplit; i++) {
        winningNumbers.add(split[i]);
      }
      for (int i = lowSplit + 1; i < highSplit; i++) {
        if (winningNumbers.contains(split[i])) {
          matching++;
        }
      }
    }

    public int getScratchCardsWonIncludingCurrent(Map<Integer, ScratchCard> scratchCards) {
      if (scratchCardsWon != null) {
        return scratchCardsWon + 1;
      }

      if (matching == 0) {
        scratchCardsWon = 0;
        return scratchCardsWon + 1;
      }

      return IntStream.rangeClosed(numCard + 1, numCard + matching )
          .filter(scratchCards::containsKey)
          .map(i -> scratchCards.get(i).getScratchCardsWonIncludingCurrent(scratchCards))
          .reduce(1, Integer::sum); // starting with 1 to include self.

    }

  }

}
