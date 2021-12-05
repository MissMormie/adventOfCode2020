package helpers;

import java.util.ArrayList;
import java.util.List;

public class Line2D {
	Coordinate start;

	Coordinate end;

	public Line2D(String input) {
		String[] split = input.split(" -> ");
		start = new Coordinate(split[0]);
		end = new Coordinate(split[1]);
	}

	public boolean isHorizontalOrVertical() {
		return start.x == end.x || start.y == end.y;
	}

	public List<Coordinate> getCoordinatesOnStraightOr45DegreeLine() {
		List<Coordinate> coordinates = new ArrayList<>();

		int xDirection = start.x == end.x ? 0 : start.x - end.x > 0 ? -1 : 1;
		int yDirection = start.y == end.y ? 0 : start.y - end.y > 0 ? -1 : 1;
		int steps = Math.max(Math.abs(start.x - end.x), Math.abs(start.y - end.y));
		for (int step = 0; step <= steps; step++) {
			coordinates.add(new Coordinate(start.x + (step * xDirection), start.y + (step * yDirection)));
		}
		return coordinates;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Line2D line2D = (Line2D) o;

		if (!start.equals(line2D.start)) return false;
		return end.equals(line2D.end);
	}

	@Override
	public int hashCode() {
		int result = start.hashCode();
		result = 31 * result + end.hashCode();
		return result;
	}
}
