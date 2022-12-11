package helpers;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class DirectionalMovingObject {
	// Should not extend coords but have it as a variable.

	public Coordinate coordinate;

	public Direction direction;

	public Set<String> visitedCoords = new HashSet<>();

	Direction[] directions = Direction.values();

	public DirectionalMovingObject(int x, int y, Direction dir) {
		coordinate = new Coordinate(x, y);
		visitedCoords.add(coordinate.getCoords());
		direction = dir;
	}

	public void move(int steps) {
		IntStream.range(0, steps)
				.forEach(i -> move());
	}

	public void move() {
		if (direction == Direction.UP) {
			coordinate.y++;
		} else if (direction == Direction.DOWN) {
			coordinate.y--;
		} else if (direction == Direction.RIGHT) {
			coordinate.x--;
		} else if (direction == Direction.LEFT) {
			coordinate.x++;
		}

		// Save new coordinate to path.
		visitedCoords.add(coordinate.getCoords());
	}

	public void moveBy(int x, int y) {
		this.coordinate.x += x;
		this.coordinate.y += y;

	}

	public void moveBy(Coordinate coordinate) {
		moveBy(coordinate.x, coordinate.y);
	}

	public void moveInDirection(Direction dir) {
		direction = dir;
		move();
	}

	public void turnAround() {
		int newDirection = (direction.ordinal() + 2) % directions.length;
		direction = directions[newDirection];
	}

	public void updateVisitedCoords() {
		visitedCoords.add(coordinate.getCoords());
	}

	public void turnInDirection(Direction direction) {
		this.direction = direction;
	}

	public void turnLeft() {
		int newDirection = direction.ordinal() == 0 ? directions.length - 1 : (direction.ordinal() - 1);
		direction = directions[newDirection];
	}

	public void turnRight() {
		int newDirection = (direction.ordinal() + 1) % directions.length;
		direction = directions[newDirection];
	}

	public enum Direction {
		LEFT, UP, RIGHT, DOWN;

		public static Direction getDirection(String direction) {
			if ("R".equals(direction)) {
				return LEFT;
			}
			if ("L".equals(direction)) {
				return RIGHT;
			}
			if ("U".equals(direction)) {
				return DOWN;
			}
			return UP;
		}
	}

}
