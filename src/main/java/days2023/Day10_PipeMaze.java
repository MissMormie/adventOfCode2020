package days2023;

import helpers.Coordinate;
import helpers.DirectionalMovingObject;
import helpers.DirectionalMovingObject.Direction;
import helpers.InputProvider;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static helpers.DirectionalMovingObject.Direction.*;

public class Day10_PipeMaze {

  public static int day = 10;
  public static int year = 2023;

  public static Coordinate animalPosition;
  public static void main(String[] args) throws IOException {
    System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
    System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
  }

  public static int runA(Stream<String> input) {
    final HashMap<String, Pipe> coordinateMap = getCoordinateMap(input);
    // find starting direction (but for now hardcoding, cause why not.
    DirectionalMovingObject route =
        new DirectionalMovingObject(animalPosition.x, animalPosition.y, LEFT);
    do {
      route.move();
      route.turnInDirection(coordinateMap.get(route.coordinate.getCoords()).getNewDirection(route.direction));
    } while (!route.coordinate.getCoords().equals(animalPosition.getCoords()));

    return route.visitedCoords.size() / 2;
  }
  private static HashMap<String, Pipe> getCoordinateMap(Stream<String> input) {
    final HashMap<String, Pipe> coordinateMap = new HashMap<>();
    List<String> lines = input.collect(Collectors.toList());
    for (int y = 0; y < lines.size(); y++) {
      for (int x = 0; x < lines.get(y).length(); x++) {
        Pipe pipe = new Pipe(lines.get(y).charAt(x), x, y);
        coordinateMap.put(pipe.getCoords(), pipe);
        if (pipe.c == 'S') {
          animalPosition = pipe;
        }
      }
    }
    return coordinateMap;
  }

  public static int runB(Stream<String> input) {
    return 0;
  }

  public static class Pipe extends Coordinate {
    char c;
    public Pipe(char c, int x, int y) {
      super(x, y);
      this.c = c;
    }

    public Direction getNewDirection(Direction currentDirection) {
      switch (c) {
        case 'L': return currentDirection == DOWN ? RIGHT : UP;
        case 'J': return currentDirection == DOWN ? LEFT : UP;
        case '7': return currentDirection == UP ? LEFT : DOWN;
        case 'F': return currentDirection == UP ? RIGHT : DOWN;
        default: return currentDirection;
      }
    }

  }

}
