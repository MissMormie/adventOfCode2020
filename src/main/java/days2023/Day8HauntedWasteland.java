package days2023;

import helpers.CircularLinkedList;
import helpers.InputProvider;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Stream;

public class Day8HauntedWasteland {

  public static int day = 8;
  public static int year = 2023;

  public static void main(String[] args) throws IOException {
    System.out.println("answer A: " + runA(
        InputProvider.getInputFor(year, day),
        "LLRLRLRRRLRLRRRLRRRLRRLLRLLRRRLRLRRLLRLRLRRLRLRLLRLRRRLRLRRLRRLRRRLRRLRRLRRLLRRLLRRRLRRLRRLRRRLRLRRLRRLLLLRLRRLRLRRLLLRRLRRRLRRRLLRRRLRRRLRRLRRRLLLRRRLLLRRLRRLRRRLRRLRRRLRRLRRRLLRLRLRRRLRRLRLRLRRRLRLRLLLRRRLRRRLRRLRRLRLRRRLRRRLLRRRLRRLRLLLRRLLRRRLRRRLRRRLLRRRLLRRLRLRRRLRRLRRRR"
    ));
    System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
  }

  public static int runA(Stream<String> input, String leftRight) {
    HashMap<String, Pair<String, String>> nodes = new HashMap<>();
    input.forEach(line -> {
      String[] split = line.split("\\W+");
      nodes.put(split[0], Pair.of(split[1], split[2]));
    });
    
    int steps = 0;
    String currentNode = "AAA";

    CircularLinkedList<Character> instructions = new CircularLinkedList<>();
    leftRight.chars().forEach(c -> instructions.addAtEnd((char) c));
    instructions.moveToFirst().previous();

    while(!currentNode.equals("ZZZ")) {
      steps++;
      if(instructions.getNext() == 'L') currentNode = nodes.get(currentNode).getLeft();
      else currentNode = nodes.get(currentNode).getRight();
    }

    return steps;
  }

  public static int runB(Stream<String> input) {
    return 0;
  }

}
