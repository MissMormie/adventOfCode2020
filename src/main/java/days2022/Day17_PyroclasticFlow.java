package days2022;

import helpers.Coordinate;
import helpers.DirectionalMovingObject;
import helpers.InputProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day17_PyroclasticFlow {
	public static int day = 17;

	public static int year = 2022;

	public static void main(String[] args) throws IOException {
//		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
		System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));

		// too low
		// 1537112278275
		// 1537175792409
		// 1537175792410
		// 1537175792411
	}

	public static int runA(Stream<String> input) {
		String inputString = input.collect(Collectors.toList()).get(0);
		Iterator<Character> gasIterator = inputString.codePoints().mapToObj(c -> (char) c).iterator();
		// How many units tall will the tower of rocks be after 2022 rocks have stopped falling?
		List<ShapeEnum> shapes = List.of(ShapeEnum.HORIZONTAL, ShapeEnum.PLUS, ShapeEnum.HOOK, ShapeEnum.VERTICAL, ShapeEnum.BLOCK);
		int lowestheigth = 0;

		// list of coords strings would be enough, but i can't visualize that as easily so sticking with map.
		Map<String, Coordinate> blocks = new HashMap<>();

		// add floor for visualization
		IntStream.rangeClosed(1, 7).mapToObj(i -> new Coordinate(i, 0)).forEach(coord -> blocks.put(coord.getCoords(),coord));

		for (int i = 0; i < 2022; i++) {

			// get shape
			Shape shape = new Shape(shapes.get(i % 5), lowestheigth);

			// After a rock appears, it alternates between being pushed by a jet of hot gas one unit
			// (in the direction indicated by the next symbol in the jet pattern) and then falling one unit down.
			do {
				if(!gasIterator.hasNext()) {
					gasIterator = inputString.codePoints().mapToObj(c -> (char) c).iterator();
				}
				shape.push(gasIterator.next(), blocks);
			} while (shape.drop(blocks));

			shape.parts.stream().map(part -> part.coordinate).forEach(coord -> blocks.put(coord.getCoords(), coord));
			lowestheigth = blocks.values().stream().mapToInt(block -> block.y).max().getAsInt();

		}
		return blocks.values().stream().mapToInt(coord -> coord.y).max().getAsInt();
	}

	public static long runB(Stream<String> input) {
		// Because the blocks repeat and the input repeats we just need to know when it starts repeating and how high the
		// tower will be for each repeat.
		String inputString = input.collect(Collectors.toList()).get(0);
		Iterator<Character> gasIterator = inputString.codePoints().mapToObj(c -> (char) c).iterator();
		// How many units tall will the tower of rocks be after 2022 rocks have stopped falling?
		List<ShapeEnum> shapes = List.of(ShapeEnum.HORIZONTAL, ShapeEnum.PLUS, ShapeEnum.HOOK, ShapeEnum.VERTICAL, ShapeEnum.BLOCK);

		// list of coords strings would be enough, but i can't visualize that as easily so sticking with map.
		Map<String, Coordinate> blocks = new HashMap<>();

		// add floor for visualization
		IntStream.rangeClosed(1, 7).mapToObj(i -> new Coordinate(i, 0)).forEach(coord -> blocks.put(coord.getCoords(),coord));

		// Find the repeat time, then calculate back from max rocks where the same position is first encountered.
		long maxRocks = 1000000000000L;

		// Run one complete repetition.

		int lowestheigth = 0;
		Shape shape = null;
		int i =0;
		boolean continuing = true;
		int shapePush = 0;
		Map<String, Result> resultMap = new HashMap<>();
		Result result = null;
		while(continuing) {

			// get shape
			shape = new Shape(shapes.get(i % 5), lowestheigth);

			// After a rock appears, it alternates between being pushed by a jet of hot gas one unit
			// (in the direction indicated by the next symbol in the jet pattern) and then falling one unit down.
			shapePush = 0;
			do {
				if(!gasIterator.hasNext()) {
					gasIterator = inputString.codePoints().mapToObj(c -> (char) c).iterator();
					result = new Result(shape.type, shapePush, lowestheigth, i);
					if(resultMap.containsKey(result.toString())) {
						// got first rep.
						continuing = false;
					} else {
						resultMap.put(result.toString(), result);
					}
				}
				shape.push(gasIterator.next(), blocks);
				shapePush++;
			} while (shape.drop(blocks));

			shape.parts.stream().map(part -> part.coordinate).forEach(coord -> blocks.put(coord.getCoords(), coord));
			lowestheigth = blocks.values().stream().mapToInt(block -> block.y).max().getAsInt();

			removeLowerBlocks(blocks, lowestheigth);
			i++;
		}

		// Done one repetition, fairly sure it's now a clean setup to move forwards.
		Result firstResult = resultMap.get(result.toString());
		long rocksStillToBeDropped = maxRocks - result.blocksDroppedBeforeThis;
		long rocksPerRepetition = result.blocksDroppedBeforeThis - ((long) firstResult.blocksDroppedBeforeThis);
		long numberOfRepetitionStillNeeded = rocksStillToBeDropped / rocksPerRepetition;
		int heightPerRepetition = result.lowestHeight - firstResult.lowestHeight;
		long numberOfDropsOutsideOfRepetitions = rocksStillToBeDropped % rocksPerRepetition;
		// block finished dropping after end of repetition

		// There's not a perfect number of repetitions until 1 trillion. Moving forward until there is.
		for(int j = 0; j <numberOfDropsOutsideOfRepetitions; j ++) {
			// get shape
			shape = new Shape(shapes.get(i % 5), lowestheigth);

			// After a rock appears, it alternates between being pushed by a jet of hot gas one unit
			// (in the direction indicated by the next symbol in the jet pattern) and then falling one unit down.
			do {
				if(!gasIterator.hasNext()) {
					gasIterator = inputString.codePoints().mapToObj(c -> (char) c).iterator();
				}
				shape.push(gasIterator.next(), blocks);
			} while (shape.drop(blocks));

			shape.parts.stream().map(part -> part.coordinate).forEach(coord -> blocks.put(coord.getCoords(), coord));
			lowestheigth = blocks.values().stream().mapToInt(block -> block.y).max().getAsInt();

			removeLowerBlocks(blocks, lowestheigth);
		}

		return lowestheigth + heightPerRepetition * numberOfRepetitionStillNeeded +1;
	}

	private static class Result {
		ShapeEnum shape;
		int push;
		int lowestHeight;
		final int blocksDroppedBeforeThis;

		public Result(ShapeEnum shape, int push, int lowestHeight, int blocksDroppedBeforeThis) {
			this.shape = shape;
			this.push = push;
			this.lowestHeight = lowestHeight;
			this.blocksDroppedBeforeThis = blocksDroppedBeforeThis;
		}
		public String toString() {
			return shape.toString() + push;
		}
	}

	private static void removeLowerBlocks(Map<String, Coordinate> blocks, int lowestheigth) {
		if(blocks.size() > 200) {
			// remove too low blocks to prevent massive growth of blocks.
			List<Coordinate> coordinates = new ArrayList<>();
			for (Coordinate coordinate : blocks.values()) {
				if (coordinate.y < lowestheigth - 100) {
					coordinates.add(coordinate);
				}
			}
			coordinates.forEach(coord -> blocks.remove(coord.getCoords()));
		}
	}

	private enum ShapeEnum {
		VERTICAL, PLUS, HOOK, HORIZONTAL, BLOCK
	}

	private static class Shape {
		List<DirectionalMovingObject> parts = new ArrayList();
		ShapeEnum type;

		public Shape(ShapeEnum shapeEnum, int lowestheight) {
			type = shapeEnum;
			// The tall, vertical chamber is exactly seven units wide. Each rock appears so that its left edge is two
			// units away from the left wall and its bottom edge is three units above the highest rock in the room
			// (or the floor, if there isn't one).

			switch (shapeEnum) {
				case HORIZONTAL:
					parts.add(new DirectionalMovingObject(3, lowestheight + 4, DirectionalMovingObject.Direction.DOWN));
					parts.add(new DirectionalMovingObject(4, lowestheight + 4, DirectionalMovingObject.Direction.DOWN));
					parts.add(new DirectionalMovingObject(5, lowestheight + 4, DirectionalMovingObject.Direction.DOWN));
					parts.add(new DirectionalMovingObject(6, lowestheight + 4, DirectionalMovingObject.Direction.DOWN));
					break;
				case PLUS:
					parts.add(new DirectionalMovingObject(4, lowestheight + 4, DirectionalMovingObject.Direction.DOWN));
					parts.add(new DirectionalMovingObject(3, lowestheight + 5, DirectionalMovingObject.Direction.DOWN));
					parts.add(new DirectionalMovingObject(4, lowestheight + 5, DirectionalMovingObject.Direction.DOWN));
					parts.add(new DirectionalMovingObject(5, lowestheight + 5, DirectionalMovingObject.Direction.DOWN));
					parts.add(new DirectionalMovingObject(4, lowestheight + 6, DirectionalMovingObject.Direction.DOWN));
					break;
				case HOOK:
					parts.add(new DirectionalMovingObject(3, lowestheight + 4, DirectionalMovingObject.Direction.DOWN));
					parts.add(new DirectionalMovingObject(4, lowestheight + 4, DirectionalMovingObject.Direction.DOWN));
					parts.add(new DirectionalMovingObject(5, lowestheight + 4, DirectionalMovingObject.Direction.DOWN));
					parts.add(new DirectionalMovingObject(5, lowestheight + 5, DirectionalMovingObject.Direction.DOWN));
					parts.add(new DirectionalMovingObject(5, lowestheight + 6, DirectionalMovingObject.Direction.DOWN));
					break;
				case VERTICAL:
					parts.add(new DirectionalMovingObject(3, lowestheight + 4, DirectionalMovingObject.Direction.DOWN));
					parts.add(new DirectionalMovingObject(3, lowestheight + 5, DirectionalMovingObject.Direction.DOWN));
					parts.add(new DirectionalMovingObject(3, lowestheight + 6, DirectionalMovingObject.Direction.DOWN));
					parts.add(new DirectionalMovingObject(3, lowestheight + 7, DirectionalMovingObject.Direction.DOWN));
					break;
				case BLOCK:
					parts.add(new DirectionalMovingObject(3, lowestheight + 4, DirectionalMovingObject.Direction.DOWN));
					parts.add(new DirectionalMovingObject(3, lowestheight + 5, DirectionalMovingObject.Direction.DOWN));
					parts.add(new DirectionalMovingObject(4, lowestheight + 4, DirectionalMovingObject.Direction.DOWN));
					parts.add(new DirectionalMovingObject(4, lowestheight + 5, DirectionalMovingObject.Direction.DOWN));
					break;
			}
		}

		/**
		 * returns true if it successfully dropped.
		 */
		public boolean drop(Map<String, Coordinate> blocks) {
			parts.forEach(part -> part.moveInDirection(DirectionalMovingObject.Direction.DOWN));

			// Any part of the shape is now part of the already existing blocks, move back. Don't need to check, because it
			// was there already
			if (parts.stream().anyMatch(part -> isPartNotInValidPosition(part, blocks))) {
				parts.forEach(part -> part.moveInDirection(DirectionalMovingObject.Direction.UP));
				return false;
			}

			return true;
		}

		private boolean isPartNotInValidPosition(DirectionalMovingObject part, Map<String, Coordinate> blocks) {
			// position of existing block
			if(blocks.containsKey(part.coordinate.getCoords())) {
				return true;
			}
			// position of walls and floor.
			if(part.coordinate.y == 0 || part.coordinate.x == 0 || part.coordinate.x == 8) {
				return true;
			}
			return false;
		}

		public void push(char c, Map<String, Coordinate> blocks) {
			if (c == '>') {
				parts.forEach(part -> part.moveInDirection(DirectionalMovingObject.Direction.LEFT));
			} else {
				parts.forEach(part -> part.moveInDirection(DirectionalMovingObject.Direction.RIGHT));
			}

			// Any part of the shape is now part of the already existing blocks, move back. Don't need to check, because it
			// was there already
			if (parts.stream().anyMatch(part -> isPartNotInValidPosition(part, blocks))) {
				if (c == '>') {
					parts.forEach(part -> part.moveInDirection(DirectionalMovingObject.Direction.RIGHT));
				} else {
					parts.forEach(part -> part.moveInDirection(DirectionalMovingObject.Direction.LEFT));
				}
			}

		}

	}


}
