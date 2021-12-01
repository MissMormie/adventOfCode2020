package days2020;

import helpers.DirectionalMovingObject;

import java.util.Arrays;
import java.util.stream.IntStream;

import static helpers.DirectionalMovingObject.Direction.DOWN;
import static helpers.DirectionalMovingObject.Direction.LEFT;
import static helpers.DirectionalMovingObject.Direction.RIGHT;
import static helpers.DirectionalMovingObject.Direction.UP;

public class Day12_RainRisk {

	public static void main(String[] args) {
		System.out.println("answer A: " + runA(textInput()));
		System.out.println("answer B: " + runB(textInput()));
	}

	public static void moveFerry(String line, DirectionalMovingObject ferry) {
		DirectionalMovingObject.Direction previousDirection = ferry.direction;
		int num = Integer.parseInt(line.substring(1));
		switch (line.charAt(0)) {
			case 'N': ferry.turnInDirection(UP); ferry.move(num); break;
			case 'S': ferry.turnInDirection(DOWN); ferry.move(num); break;
			case 'E': ferry.turnInDirection(RIGHT); ferry.move(num); break;
			case 'W': ferry.turnInDirection(LEFT); ferry.move(num); break;
			case 'F': ferry.move(num); return;
			case 'L': IntStream.range(0, num / 90).forEach(i -> ferry.turnLeft()); return;
			case 'R': IntStream.range(0, num / 90).forEach(i -> ferry.turnRight()); return;
		}
		ferry.direction = previousDirection;
	}

	public static void moveFerryAndWayPoint(String line, DirectionalMovingObject ferry, DirectionalMovingObject waypoint) {
		char instruction = line.charAt(0);
		int num = Integer.parseInt(line.substring(1));

		switch (instruction) {
			case 'N': waypoint.turnInDirection(UP); waypoint.move(num); break;
			case 'S': waypoint.turnInDirection(DOWN); waypoint.move(num); break;
			case 'E': waypoint.turnInDirection(RIGHT); waypoint.move(num); break;
			case 'W': waypoint.turnInDirection(LEFT); waypoint.move(num); break;
			// F10 moves the ship to the waypoint 10 times (a total of 100 units east and 10 units north),
			// leaving the ship at east 100, north 10. The waypoint stays 10 units east and 1 unit north of the ship.
			case 'F':
				int waypointRelativeX = (waypoint.coordinate.x - ferry.coordinate.x) * num;
				int waypointRelativeY = (waypoint.coordinate.y - ferry.coordinate.y) * num;
				ferry.coordinate.x += waypointRelativeX;
				ferry.coordinate.y += waypointRelativeY;
				waypoint.coordinate.x += waypointRelativeX;
				waypoint.coordinate.y += waypointRelativeY;
				break;

			// Action L means to rotate the waypoint around the ship left (counter-clockwise) the given number of degrees.
			case 'L':
				IntStream.range(0, num / 90).forEach(i -> {
					int tempY = (waypoint.coordinate.y - ferry.coordinate.y);
					waypoint.coordinate.y = ferry.coordinate.y + (waypoint.coordinate.x - ferry.coordinate.x);
					waypoint.coordinate.x = ferry.coordinate.x + -tempY;
				});
				break;
			case 'R':
				IntStream.range(0, num / 90).forEach(i -> {
					int tempY = (waypoint.coordinate.y - ferry.coordinate.y);
					waypoint.coordinate.y = ferry.coordinate.y + -(waypoint.coordinate.x - ferry.coordinate.x);
					waypoint.coordinate.x = ferry.coordinate.x + tempY;
				});
				break;
		}
	}

	public static int runA(String input) {
		DirectionalMovingObject ferry = new DirectionalMovingObject(0, 0, DirectionalMovingObject.Direction.RIGHT);
		Arrays.stream(input.split("\n"))
				.forEach(line -> moveFerry(line, ferry));

		return ferry.coordinate.getManhattanDistance(0,0);
	}

	public static int runB(String input) {
		DirectionalMovingObject ferry = new DirectionalMovingObject(0, 0, DirectionalMovingObject.Direction.RIGHT);
		DirectionalMovingObject waypoint = new DirectionalMovingObject(10, 1, DirectionalMovingObject.Direction.RIGHT);

		Arrays.stream(input.split("\n"))
				.forEach(line -> moveFerryAndWayPoint(line, ferry, waypoint));

		return ferry.coordinate.getManhattanDistance(0,0);
	}

	private static String textInput() {
		return "R90\n" +
				"F56\n" +
				"R90\n" +
				"F56\n" +
				"R90\n" +
				"R180\n" +
				"W5\n" +
				"L90\n" +
				"E2\n" +
				"L90\n" +
				"S5\n" +
				"E1\n" +
				"F11\n" +
				"L90\n" +
				"F46\n" +
				"S2\n" +
				"E2\n" +
				"S1\n" +
				"E2\n" +
				"E3\n" +
				"N4\n" +
				"W1\n" +
				"N4\n" +
				"L90\n" +
				"W4\n" +
				"L90\n" +
				"N1\n" +
				"E1\n" +
				"S4\n" +
				"F45\n" +
				"S4\n" +
				"W4\n" +
				"S1\n" +
				"E3\n" +
				"E3\n" +
				"E1\n" +
				"F76\n" +
				"S5\n" +
				"E1\n" +
				"N3\n" +
				"W2\n" +
				"F55\n" +
				"E3\n" +
				"F34\n" +
				"S2\n" +
				"S2\n" +
				"E3\n" +
				"S5\n" +
				"L90\n" +
				"L90\n" +
				"W5\n" +
				"F23\n" +
				"R90\n" +
				"F14\n" +
				"F21\n" +
				"L90\n" +
				"N3\n" +
				"L180\n" +
				"N3\n" +
				"F98\n" +
				"R180\n" +
				"S5\n" +
				"F94\n" +
				"R90\n" +
				"N5\n" +
				"L90\n" +
				"S3\n" +
				"F85\n" +
				"R90\n" +
				"F77\n" +
				"S1\n" +
				"R90\n" +
				"E3\n" +
				"S2\n" +
				"E5\n" +
				"R180\n" +
				"W3\n" +
				"F46\n" +
				"R90\n" +
				"F44\n" +
				"S3\n" +
				"W3\n" +
				"L180\n" +
				"N4\n" +
				"L90\n" +
				"N1\n" +
				"E2\n" +
				"L90\n" +
				"S4\n" +
				"R90\n" +
				"E1\n" +
				"N4\n" +
				"F100\n" +
				"L90\n" +
				"S4\n" +
				"F94\n" +
				"L180\n" +
				"W2\n" +
				"S1\n" +
				"R90\n" +
				"E5\n" +
				"L90\n" +
				"S4\n" +
				"L90\n" +
				"W3\n" +
				"E1\n" +
				"R270\n" +
				"S4\n" +
				"E2\n" +
				"N5\n" +
				"F3\n" +
				"R180\n" +
				"F21\n" +
				"L90\n" +
				"F57\n" +
				"W1\n" +
				"F77\n" +
				"F24\n" +
				"N4\n" +
				"E5\n" +
				"S3\n" +
				"L90\n" +
				"F64\n" +
				"N5\n" +
				"L90\n" +
				"N1\n" +
				"F42\n" +
				"S5\n" +
				"W1\n" +
				"F50\n" +
				"S1\n" +
				"W3\n" +
				"N5\n" +
				"E5\n" +
				"F19\n" +
				"R180\n" +
				"W4\n" +
				"S4\n" +
				"F20\n" +
				"L90\n" +
				"W1\n" +
				"F88\n" +
				"R90\n" +
				"F57\n" +
				"L90\n" +
				"N4\n" +
				"W5\n" +
				"R90\n" +
				"S3\n" +
				"E2\n" +
				"S1\n" +
				"E2\n" +
				"N1\n" +
				"E4\n" +
				"S1\n" +
				"F3\n" +
				"R90\n" +
				"W1\n" +
				"N4\n" +
				"R90\n" +
				"F38\n" +
				"E5\n" +
				"E1\n" +
				"N2\n" +
				"R90\n" +
				"N5\n" +
				"S4\n" +
				"E3\n" +
				"F8\n" +
				"W5\n" +
				"R90\n" +
				"E4\n" +
				"F19\n" +
				"N1\n" +
				"F50\n" +
				"E1\n" +
				"F54\n" +
				"W5\n" +
				"L90\n" +
				"F10\n" +
				"S2\n" +
				"E3\n" +
				"R90\n" +
				"S5\n" +
				"L90\n" +
				"F59\n" +
				"N3\n" +
				"R90\n" +
				"W5\n" +
				"F33\n" +
				"N4\n" +
				"R270\n" +
				"F46\n" +
				"S2\n" +
				"L270\n" +
				"F97\n" +
				"E4\n" +
				"N2\n" +
				"E5\n" +
				"R90\n" +
				"F31\n" +
				"R180\n" +
				"N1\n" +
				"W1\n" +
				"R90\n" +
				"F40\n" +
				"R90\n" +
				"S5\n" +
				"F15\n" +
				"L90\n" +
				"E2\n" +
				"L90\n" +
				"L90\n" +
				"F53\n" +
				"N4\n" +
				"F28\n" +
				"E5\n" +
				"F58\n" +
				"L90\n" +
				"W1\n" +
				"L90\n" +
				"E1\n" +
				"L90\n" +
				"N1\n" +
				"F42\n" +
				"S2\n" +
				"S3\n" +
				"F62\n" +
				"E5\n" +
				"F73\n" +
				"W1\n" +
				"R270\n" +
				"F10\n" +
				"R90\n" +
				"E5\n" +
				"N3\n" +
				"E4\n" +
				"S1\n" +
				"E2\n" +
				"N4\n" +
				"E4\n" +
				"F47\n" +
				"E3\n" +
				"E5\n" +
				"S5\n" +
				"F24\n" +
				"L90\n" +
				"N2\n" +
				"L180\n" +
				"S2\n" +
				"F82\n" +
				"W3\n" +
				"S2\n" +
				"W1\n" +
				"R90\n" +
				"N5\n" +
				"E3\n" +
				"N4\n" +
				"L90\n" +
				"N1\n" +
				"F53\n" +
				"S3\n" +
				"L90\n" +
				"S5\n" +
				"F88\n" +
				"F40\n" +
				"S1\n" +
				"E4\n" +
				"N4\n" +
				"F66\n" +
				"W3\n" +
				"L90\n" +
				"E1\n" +
				"R180\n" +
				"F73\n" +
				"R90\n" +
				"W4\n" +
				"N4\n" +
				"F85\n" +
				"E4\n" +
				"F66\n" +
				"S1\n" +
				"W4\n" +
				"F16\n" +
				"R180\n" +
				"N5\n" +
				"L90\n" +
				"S3\n" +
				"E4\n" +
				"N4\n" +
				"W2\n" +
				"L90\n" +
				"R90\n" +
				"W3\n" +
				"N5\n" +
				"R90\n" +
				"F45\n" +
				"E2\n" +
				"S5\n" +
				"R90\n" +
				"F6\n" +
				"W4\n" +
				"S2\n" +
				"E4\n" +
				"F74\n" +
				"W4\n" +
				"L90\n" +
				"F75\n" +
				"W2\n" +
				"L90\n" +
				"F97\n" +
				"R90\n" +
				"N4\n" +
				"F27\n" +
				"L180\n" +
				"F45\n" +
				"S1\n" +
				"L90\n" +
				"W2\n" +
				"F10\n" +
				"W3\n" +
				"S4\n" +
				"F42\n" +
				"W1\n" +
				"F30\n" +
				"R90\n" +
				"E2\n" +
				"F94\n" +
				"E5\n" +
				"R90\n" +
				"F41\n" +
				"E1\n" +
				"N3\n" +
				"W1\n" +
				"N2\n" +
				"R90\n" +
				"S5\n" +
				"F93\n" +
				"N1\n" +
				"R90\n" +
				"F38\n" +
				"S3\n" +
				"F50\n" +
				"L270\n" +
				"W1\n" +
				"S4\n" +
				"F64\n" +
				"W1\n" +
				"N1\n" +
				"F61\n" +
				"R90\n" +
				"W2\n" +
				"R90\n" +
				"N1\n" +
				"F31\n" +
				"S2\n" +
				"F7\n" +
				"N2\n" +
				"R90\n" +
				"S5\n" +
				"E3\n" +
				"S1\n" +
				"E1\n" +
				"R90\n" +
				"E2\n" +
				"R90\n" +
				"E4\n" +
				"F76\n" +
				"R90\n" +
				"W4\n" +
				"F15\n" +
				"S3\n" +
				"F2\n" +
				"R90\n" +
				"N4\n" +
				"F66\n" +
				"L180\n" +
				"W3\n" +
				"N2\n" +
				"F1\n" +
				"S4\n" +
				"L90\n" +
				"N1\n" +
				"F19\n" +
				"F20\n" +
				"E1\n" +
				"R90\n" +
				"N3\n" +
				"F54\n" +
				"N4\n" +
				"F62\n" +
				"W2\n" +
				"S1\n" +
				"F49\n" +
				"W3\n" +
				"S4\n" +
				"W3\n" +
				"N2\n" +
				"E4\n" +
				"N1\n" +
				"R90\n" +
				"E1\n" +
				"N2\n" +
				"L90\n" +
				"F46\n" +
				"S4\n" +
				"E2\n" +
				"L90\n" +
				"S3\n" +
				"L90\n" +
				"N4\n" +
				"R180\n" +
				"F56\n" +
				"L90\n" +
				"E2\n" +
				"F51\n" +
				"W1\n" +
				"N5\n" +
				"E2\n" +
				"L90\n" +
				"E3\n" +
				"S5\n" +
				"W3\n" +
				"F73\n" +
				"E5\n" +
				"F100\n" +
				"L90\n" +
				"N2\n" +
				"W2\n" +
				"L180\n" +
				"F89\n" +
				"L90\n" +
				"N3\n" +
				"W4\n" +
				"S2\n" +
				"W1\n" +
				"L270\n" +
				"E5\n" +
				"N2\n" +
				"R90\n" +
				"F65\n" +
				"W1\n" +
				"F41\n" +
				"E2\n" +
				"N4\n" +
				"E1\n" +
				"F73\n" +
				"L180\n" +
				"S4\n" +
				"L90\n" +
				"N4\n" +
				"W3\n" +
				"N4\n" +
				"E1\n" +
				"R180\n" +
				"F67\n" +
				"E5\n" +
				"L90\n" +
				"N5\n" +
				"F75\n" +
				"N3\n" +
				"E4\n" +
				"R90\n" +
				"F28\n" +
				"R90\n" +
				"S3\n" +
				"F77\n" +
				"L180\n" +
				"N2\n" +
				"N1\n" +
				"R90\n" +
				"F77\n" +
				"S3\n" +
				"W3\n" +
				"N4\n" +
				"F60\n" +
				"W4\n" +
				"F40\n" +
				"L90\n" +
				"W3\n" +
				"W3\n" +
				"E1\n" +
				"S2\n" +
				"W2\n" +
				"N1\n" +
				"F86\n" +
				"R180\n" +
				"E1\n" +
				"L180\n" +
				"F17\n" +
				"R90\n" +
				"F11\n" +
				"E1\n" +
				"S2\n" +
				"F48\n" +
				"S2\n" +
				"W2\n" +
				"F1\n" +
				"N5\n" +
				"F53\n" +
				"N2\n" +
				"W1\n" +
				"L180\n" +
				"L90\n" +
				"N5\n" +
				"W1\n" +
				"L180\n" +
				"E1\n" +
				"F67\n" +
				"W4\n" +
				"N3\n" +
				"E5\n" +
				"N1\n" +
				"F88\n" +
				"R90\n" +
				"F93\n" +
				"E1\n" +
				"F33\n" +
				"L90\n" +
				"F1\n" +
				"W2\n" +
				"N4\n" +
				"W3\n" +
				"F85\n" +
				"S1\n" +
				"E2\n" +
				"F69\n" +
				"R90\n" +
				"S4\n" +
				"W2\n" +
				"L90\n" +
				"W3\n" +
				"S5\n" +
				"F14\n" +
				"W1\n" +
				"S5\n" +
				"W2\n" +
				"F95\n" +
				"F30\n" +
				"E1\n" +
				"F4\n" +
				"W3\n" +
				"R90\n" +
				"F93\n" +
				"N5\n" +
				"E1\n" +
				"F9\n" +
				"L90\n" +
				"W5\n" +
				"L270\n" +
				"E1\n" +
				"L90\n" +
				"S2\n" +
				"F97\n" +
				"S1\n" +
				"F18\n" +
				"E3\n" +
				"R90\n" +
				"N1\n" +
				"R90\n" +
				"N5\n" +
				"F72\n" +
				"S4\n" +
				"L90\n" +
				"W3\n" +
				"S3\n" +
				"F13\n" +
				"W5\n" +
				"N2\n" +
				"F31\n" +
				"F17\n" +
				"S4\n" +
				"E2\n" +
				"L90\n" +
				"N5\n" +
				"R90\n" +
				"F42\n" +
				"W2\n" +
				"N1\n" +
				"S3\n" +
				"F60\n" +
				"W5\n" +
				"N4\n" +
				"R90\n" +
				"E5\n" +
				"S3\n" +
				"F88\n" +
				"E4\n" +
				"N5\n" +
				"W3\n" +
				"L180\n" +
				"N3\n" +
				"F66\n" +
				"E5\n" +
				"R270\n" +
				"E3\n" +
				"L180\n" +
				"W4\n" +
				"W3\n" +
				"S2\n" +
				"W1\n" +
				"F69\n" +
				"E5\n" +
				"L90\n" +
				"F23\n" +
				"W5\n" +
				"L90\n" +
				"F49\n" +
				"S3\n" +
				"R180\n" +
				"W2\n" +
				"L90\n" +
				"N1\n" +
				"R90\n" +
				"S4\n" +
				"F71\n" +
				"N5\n" +
				"F36\n" +
				"R180\n" +
				"F62\n" +
				"L180\n" +
				"F39\n" +
				"N5\n" +
				"F36\n" +
				"R180\n" +
				"N4\n" +
				"W4\n" +
				"E5\n" +
				"F94\n" +
				"F39\n" +
				"L90\n" +
				"N1\n" +
				"F70\n" +
				"R90\n" +
				"W2\n" +
				"R180\n" +
				"F16\n" +
				"E3\n" +
				"S1\n" +
				"R180\n" +
				"E1\n" +
				"S3\n" +
				"E5\n" +
				"L90\n" +
				"N5\n" +
				"F76\n" +
				"N4\n" +
				"R90\n" +
				"E3\n" +
				"L180\n" +
				"N3\n" +
				"F20\n" +
				"S3\n" +
				"R90\n" +
				"N3\n" +
				"L270\n" +
				"E4\n" +
				"N5\n" +
				"F49\n" +
				"S3\n" +
				"F79\n" +
				"W3\n" +
				"R270\n" +
				"E1\n" +
				"L180\n" +
				"R90\n" +
				"F34\n" +
				"W3\n" +
				"S5\n" +
				"E5\n" +
				"F76\n" +
				"N3\n" +
				"N5\n" +
				"F66\n" +
				"L90\n" +
				"F48\n" +
				"E1\n" +
				"L270\n" +
				"E3\n" +
				"S2\n" +
				"L180\n" +
				"N3\n" +
				"F1\n" +
				"W5\n" +
				"L90\n" +
				"F9\n" +
				"S5\n" +
				"F64\n" +
				"L180\n" +
				"W1\n" +
				"N1\n" +
				"E2\n" +
				"F4\n" +
				"R90\n" +
				"F81\n" +
				"W5\n" +
				"S4\n" +
				"E1\n" +
				"N4\n" +
				"E1\n" +
				"F61\n" +
				"R180\n" +
				"S2\n" +
				"R180\n" +
				"F24\n" +
				"N3\n" +
				"W5\n" +
				"N1\n" +
				"F67\n" +
				"L180\n" +
				"F50\n" +
				"W5\n" +
				"R90\n" +
				"N2\n" +
				"L180\n" +
				"F80\n" +
				"W5\n" +
				"L90\n" +
				"N1\n" +
				"F31\n" +
				"E5\n" +
				"R90\n" +
				"N5\n" +
				"R90\n" +
				"F93\n" +
				"E2\n" +
				"E3\n" +
				"L90\n" +
				"E2\n" +
				"L90\n" +
				"E1\n" +
				"N4\n" +
				"W4\n" +
				"F80\n" +
				"R180\n" +
				"F84\n" +
				"E5\n" +
				"N1\n" +
				"F37\n" +
				"S3\n" +
				"W5\n" +
				"S4\n" +
				"F9\n" +
				"R90\n" +
				"E2\n" +
				"R90\n" +
				"F39\n" +
				"R90\n" +
				"F77\n" +
				"N3\n" +
				"R90\n" +
				"E5\n" +
				"N2\n" +
				"F49\n" +
				"S2\n" +
				"W3\n" +
				"F42\n" +
				"N1\n" +
				"F19\n" +
				"E2\n" +
				"N3\n" +
				"R90\n" +
				"F48\n" +
				"N1\n" +
				"W3\n" +
				"S2\n" +
				"E3\n" +
				"L90\n" +
				"E1\n" +
				"R90\n" +
				"F100\n" +
				"E2\n" +
				"L90\n" +
				"F90\n" +
				"W1\n" +
				"F66\n" +
				"N4\n" +
				"R180\n" +
				"W2\n" +
				"F33";

	}
}
