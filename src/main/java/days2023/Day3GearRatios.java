package days2023;

import helpers.Coordinate;
import helpers.InputProvider;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day3GearRatios {

  public static int day = 3;
  public static int year = 2023;

  public static void main(String[] args) throws IOException {
    System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
    System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
  }

  public static int runA(Stream<String> input) {
    final HashMap<String, Schematic> coordinateMap = getCoordinateMap(input);

    return coordinateMap.values().stream()
        .filter(schematic -> schematic.isChar)
        .map(schematic -> schematic.getSurroundingNumbers(coordinateMap))
        .flatMap(List::stream)
        .reduce(Integer::sum)
        .orElse(0);
  }

  private static HashMap<String, Schematic> getCoordinateMap(Stream<String> input) {
    final HashMap<String, Schematic> coordinateMap = new HashMap<>();
    List<String> lines = input.collect(Collectors.toList());
    for (int y = 0; y < lines.size(); y++) {
      for (int x = 0; x < lines.get(y).length(); x++) {
        Schematic schematic = new Schematic(lines.get(y).charAt(x), x, y);
        coordinateMap.put(schematic.getCoords(), schematic);
      }
    }
    return coordinateMap;
  }

  public static int runB(Stream<String> input) {
    final HashMap<String, Schematic> coordinateMap = getCoordinateMap(input);

    return coordinateMap.values().stream()
        .map(schematic -> schematic.getGearRatio(coordinateMap))
        .reduce(Integer::sum)
        .orElse(0);
  }

  public static class Schematic extends Coordinate {

    public Integer intValue;
    public boolean isChar;
    public char c;
    public boolean isCounted;

    public Schematic(char c, int x, int y) {
      super(x, y);
      if (Character.isDigit(c)) {
        intValue = Character.getNumericValue(c);
        return;
      }
      if (c != '.') {
        isChar = true;
        this.c = c;
      }
    }

    public int getGearRatio(HashMap<String, Schematic> coordinateMap) {
      if (c != '*') {
        return 0;
      }
      List<Integer> surroundingNumbers = getSurroundingNumbers(coordinateMap);
      if (surroundingNumbers.size() != 2) {
        return 0;
      }
      return surroundingNumbers.stream().reduce(1, (a, b) -> a * b);

    }

    public List<Integer> getSurroundingNumbers(HashMap<String, Schematic> coordinateMap) {
      getAdjacent(coordinateMap);
      return adjacentCoords.stream()
          .map(coords -> {
            Schematic schematic = coordinateMap.get(coords.getCoords());
            if (schematic == null || schematic.isCounted || schematic.intValue == null) {
              return Optional.empty();
            }
            return schematic.getNumber(coordinateMap);
          })
          .filter(Optional::isPresent)
          .map(optional -> (Integer) optional.get())
          .collect(Collectors.toList());

    }

    public Optional<Integer> getNumber(HashMap<String, Schematic> coordinateMap) {
      if (intValue == null) {
        return Optional.empty();
      }

      Schematic rightMostNumber = getRightMostNumber(coordinateMap);
      Integer numbersToLeft = rightMostNumber.getNumbersToLeft(coordinateMap);
      return Optional.of(numbersToLeft);
    }

    public Integer getNumbersToLeft(HashMap<String, Schematic> coordinateMap) {
      if (intValue == null) {
        return 0;
      }
      Schematic schematic = coordinateMap.get(makeCoordString(x - 1, y));
      int number = intValue;
      if (schematic != null && schematic.intValue != null) {
        number += 10 * coordinateMap.get(schematic.getCoords()).getNumbersToLeft(coordinateMap);
      }

      isCounted = true;
      return number;
    }

    public Schematic getRightMostNumber(HashMap<String, Schematic> coordinateMap) {
      Schematic schematic = coordinateMap.get(makeCoordString(x + 1, y));
      if (schematic != null && schematic.intValue != null) {
        return schematic.getRightMostNumber(coordinateMap);
      }
      return this;
    }

  }

}

