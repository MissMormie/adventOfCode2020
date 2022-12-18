package days2022;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day16_ProboscideaVolcaniumTest {

	@Test
	void runA() {
		String input;
		int answer;

		input = "Valve AA has flow rate=0; tunnels lead to valves DD, II, BB\n" +
				"Valve BB has flow rate=13; tunnels lead to valves CC, AA\n" +
				"Valve CC has flow rate=2; tunnels lead to valves DD, BB\n" +
				"Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE\n" +
				"Valve EE has flow rate=3; tunnels lead to valves FF, DD\n" +
				"Valve FF has flow rate=0; tunnels lead to valves EE, GG\n" +
				"Valve GG has flow rate=0; tunnels lead to valves FF, HH\n" +
				"Valve HH has flow rate=22; tunnel leads to valve GG\n" +
				"Valve II has flow rate=0; tunnels lead to valves AA, JJ\n" +
				"Valve JJ has flow rate=21; tunnel leads to valve II\n";
		answer = 0;
		assertEquals(answer, Day16_ProboscideaVolcanium.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

	}


	@Test
	void runB() {
		String input;
		int answer;

		input = "";
		answer = 0;
		assertEquals(answer, Day16_ProboscideaVolcanium.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
	}
}