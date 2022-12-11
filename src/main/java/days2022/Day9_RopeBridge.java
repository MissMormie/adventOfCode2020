package days2022;

import helpers.Coordinate;
import helpers.DirectionalMovingObject;
import helpers.InputProvider;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static helpers.DirectionalMovingObject.Direction.UP;
import static helpers.DirectionalMovingObject.Direction.getDirection;

public class Day9_RopeBridge {
	public static int day = 9;

	public static int year = 2022;

	public static void main(String[] args) throws IOException {
		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
		System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
	}


	public static int runA(Stream<String> input) {
		DirectionalMovingObject head = new DirectionalMovingObject(0, 0, UP);
		DirectionalMovingObject tail = new DirectionalMovingObject(0, 0, UP);
		head.coordinate.intValue = 0;
		tail.coordinate.intValue = 1;

		input.forEach(line -> {
			String[] s = line.split(" ");
			DirectionalMovingObject.Direction direction = getDirection(s[0]);
			head.turnInDirection(direction);
			for (int i = 0; i < Integer.parseInt(s[1]); i++) {
				head.move(1);
				moveTail(head.coordinate, tail.coordinate);
				tail.updateVisitedCoords();
			}
		});
		return tail.visitedCoords.size();
	}

	public static int runB(Stream<String> input) {
		List<DirectionalMovingObject> knots = IntStream.rangeClosed(0, 9)
				.mapToObj(i -> {
					DirectionalMovingObject directionalMovingObject = new DirectionalMovingObject(0, 0, UP);
					directionalMovingObject.coordinate.intValue = i;
					return directionalMovingObject;
				})
				.collect(Collectors.toList());
		input.forEach(line -> {
			String[] s = line.split(" ");
			DirectionalMovingObject.Direction direction = getDirection(s[0]);

			knots.get(0).turnInDirection(direction);
			for (int i = 0; i < Integer.parseInt(s[1]); i++) { // steps
				knots.get(0).move(1);
				for (int j = 1; j < knots.size(); j++) { // knots
					moveTail(knots.get(j - 1).coordinate, knots.get(j).coordinate);
				}
				knots.get(9).updateVisitedCoords();;
			}
//			visualize(knots);
		});
		return knots.get(9).visitedCoords.size();
	}


	public static void visualize(DirectionalMovingObject c1, DirectionalMovingObject c2) {
		visualize(List.of(c1, c2));
	}
	public static void visualize(List<DirectionalMovingObject> objects) {
		Map<String, Coordinate> collect = objects.stream().map(ob -> ob.coordinate)
				.collect(Collectors.toSet()).stream()
				.collect(Collectors.toMap(Coordinate::getCoords, Function.identity()));
		if (!collect.containsKey(Coordinate.makeCoordString(0, 0))) {
			collect.put("0,0", new Coordinate(0, 0));
		}
		Coordinate.printIntValuesForCoordinate(collect);
	}

	private static void moveTail(Coordinate head, Coordinate tail) {

//		If the head is ever two steps directly up, down, left, or right from the tail, the tail must also move one step
//		in that direction so it remains close enough:
		if(tail.x == head.x || tail.y == head.y) {
			if (Math.abs(tail.x - head.x) > 1) {
				tail.x += tail.x - head.x > 1 ? -1 : 1;
				return;
			}

			if (Math.abs(tail.y - head.y) > 1) {
				tail.y += tail.y - head.y > 1 ? -1 : 1;
			}
			return;
		}

		//Otherwise, if the head and tail aren't touching and aren't in the same row or column,
		// the tail always moves one step diagonally to keep up:
		if(Math.abs(tail.x - head.x) > 1 || Math.abs(tail.y - head.y) > 1) {
			if (tail.x > head.x) {
				tail.x -= 1;
			} else {
				tail.x += 1;
			}

			if (tail.y > head.y) {
				tail.y -= 1;
			} else {
				tail.y += 1;
			}
		}

	}
}
