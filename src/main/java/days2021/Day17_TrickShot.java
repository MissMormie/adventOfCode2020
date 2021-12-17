package days2021;

import helpers.Coordinate;
import helpers.InputProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Day17_TrickShot {
	public static int day = 17;

	public static int year = 2021;

	public static void main(String[] args) throws IOException {
		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
		System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
	}

	public static int runA(Stream<String> input) {
		String s = input.findAny().get();
		String[] split = s.split("[a-z :.=,]+");
		int minY = Integer.parseInt(split[4]);
		int maxY = Integer.parseInt(split[3]);

		for (int y = 1000; y > 0; y--) {
			int dy = y;
			int runY = 0;
			int highestY = 0;
			while (runY > minY) {
				runY += dy;
				dy--;
				if (highestY < runY) {
					highestY = runY;
				}
			}
			if (runY >= maxY) {
				return highestY;
			}
		}

		return 0;
	}


	public static int runB(Stream<String> input) {
		String s = input.findAny().get();
		String[] split = s.split("[a-z :.=,]+");
		int minY = Integer.parseInt(split[4]);
		int maxY = Integer.parseInt(split[3]);
		int minX = Integer.parseInt(split[1]);
		int maxX = Integer.parseInt(split[2]);


		// Get all dy's that hit the target zone and at which time(s).
		List<Coordinate> dy_tList = new ArrayList<>();

		for (int y = 100; y >= maxY; y--) {
			int dy = y;
			int runY = 0;
			while (runY >= maxY) {
				if (runY <= minY) {
					dy_tList.add(new Coordinate(y, y - dy));
				}
				runY += dy;
				dy--;
			}
		}

		// Find all x's that hit the target area at the same time.
		Set<Coordinate> dx_dyList = new HashSet<>();
		for (Coordinate dy_t : dy_tList) {
			int y = dy_t.x; // yes, x should be assigned to y here. I'm abusing the coord object because it was right there.
			int steps = dy_t.y;

			for (int x = maxX; x > 0; x--) {
				int dx = x;
				int runX = 0;

				for (int step = 0; step < steps; step++) {
					runX += dx;
					if (dx > 0) {
						dx--;
					}
				}

				if (runX >= minX && runX <= maxX) {
					dx_dyList.add(new Coordinate(x, y));
				}
			}
		}

		return dx_dyList.size();
	}
}
