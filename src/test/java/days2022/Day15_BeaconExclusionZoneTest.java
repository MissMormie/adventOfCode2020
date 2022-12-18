package days2022;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day15_BeaconExclusionZoneTest {

	@Test
	void runA() {
		String input;
		int answer;

		input = "Sensor at x=2, y=18: closest beacon is at x=-2, y=15\n" +
				"Sensor at x=9, y=16: closest beacon is at x=10, y=16\n" +
				"Sensor at x=13, y=2: closest beacon is at x=15, y=3\n" +
				"Sensor at x=12, y=14: closest beacon is at x=10, y=16\n" +
				"Sensor at x=10, y=20: closest beacon is at x=10, y=16\n" +
				"Sensor at x=14, y=17: closest beacon is at x=10, y=16\n" +
				"Sensor at x=8, y=7: closest beacon is at x=2, y=10\n" +
				"Sensor at x=2, y=0: closest beacon is at x=2, y=10\n" +
				"Sensor at x=0, y=11: closest beacon is at x=2, y=10\n" +
				"Sensor at x=20, y=14: closest beacon is at x=25, y=17\n" +
				"Sensor at x=17, y=20: closest beacon is at x=21, y=22\n" +
				"Sensor at x=16, y=7: closest beacon is at x=15, y=3\n" +
				"Sensor at x=14, y=3: closest beacon is at x=15, y=3\n" +
				"Sensor at x=20, y=1: closest beacon is at x=15, y=3";
		answer = 26;
		assertEquals(answer, Day15_BeaconExclusionZone.runA(Arrays.stream(input.split("\n")),10), "input = " + input + " answer: " + answer);

	}


	@Test
	void runB() {
		String input;
		long answer;


		input = "Sensor at x=2, y=18: closest beacon is at x=-2, y=15\n" +
				"Sensor at x=9, y=16: closest beacon is at x=10, y=16\n" +
				"Sensor at x=13, y=2: closest beacon is at x=15, y=3\n" +
				"Sensor at x=12, y=14: closest beacon is at x=10, y=16\n" +
				"Sensor at x=10, y=20: closest beacon is at x=10, y=16\n" +
				"Sensor at x=14, y=17: closest beacon is at x=10, y=16\n" +
				"Sensor at x=8, y=7: closest beacon is at x=2, y=10\n" +
				"Sensor at x=2, y=0: closest beacon is at x=2, y=10\n" +
				"Sensor at x=0, y=11: closest beacon is at x=2, y=10\n" +
				"Sensor at x=20, y=14: closest beacon is at x=25, y=17\n" +
				"Sensor at x=17, y=20: closest beacon is at x=21, y=22\n" +
				"Sensor at x=16, y=7: closest beacon is at x=15, y=3\n" +
				"Sensor at x=14, y=3: closest beacon is at x=15, y=3\n" +
				"Sensor at x=20, y=1: closest beacon is at x=15, y=3";
		answer = 56000011;
		assertEquals(answer, Day15_BeaconExclusionZone.runB(Arrays.stream(input.split("\n")), 20), "input = " + input + " answer: " + answer);
	}
}