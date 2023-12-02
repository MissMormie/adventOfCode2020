package days2023;

import helpers.InputProvider;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day2CubeConundrum {

  public static int day = 2;
  public static int year = 2023;

  public static void main(String[] args) throws IOException {
    System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
    System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
  }

  public static int runA(Stream<String> input) {
    return input.map(line -> line.split("[:;]"))
        .filter(Day2CubeConundrum::isValid)
        .map(line -> {String[] split = line[0].split("Game |:(.*)");
          return Integer.parseInt(split[1]);
        })
        .reduce(Integer::sum)
        .orElse(-1);
  }

  public static boolean isValid(String[] array) {
    for(int i = 1; i < array.length; i++) {
      String[] split = array[i].split("[, ]");
      for(int block = 0; block < split.length; block += 3) {
        switch (split[block+2]) {
          case "red": if(Integer.parseInt(split[block+1]) >12) return false; break;
          case "green": if(Integer.parseInt(split[block+1]) >13) return false; break;
          case "blue": if(Integer.parseInt(split[block+1]) >14) return false; break;
        }
      }
    }
    return true;
  }


  public static int runB(Stream<String> input) {
   return input.map(line -> {
      String[] split = line.split("[\\s :,;]");
      HashMap<String, Integer> numberOfColor = new HashMap<>();
      for (int i = 3; i < split.length; i+=3) {
        if(!numberOfColor.containsKey(split[i + 1]) || numberOfColor.get(split[i + 1]) < Integer.parseInt(split[i])) {
          numberOfColor.put(split[i + 1], Integer.parseInt(split[i]));
        }
      }
      return numberOfColor.values().stream().reduce(1, (a,b) -> a * b);
    }).reduce(Integer::sum).orElse(-1);

  }

}
