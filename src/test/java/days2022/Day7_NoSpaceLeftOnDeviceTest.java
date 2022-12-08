package days2022;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day7_NoSpaceLeftOnDeviceTest {

	@Test
	void runA() {
		String input;
		int answer;

		input = "$ cd /\n" +
				"$ ls\n" +
				"dir a\n" +
				"14848514 b.txt\n" +
				"8504156 c.dat\n" +
				"dir d\n" +
				"$ cd a\n" +
				"$ ls\n" +
				"dir e\n" +
				"29116 f\n" +
				"2557 g\n" +
				"62596 h.lst\n" +
				"$ cd e\n" +
				"$ ls\n" +
				"584 i\n" +
				"$ cd ..\n" +
				"$ cd ..\n" +
				"$ cd d\n" +
				"$ ls\n" +
				"4060174 j\n" +
				"8033020 d.log\n" +
				"5626152 d.ext\n" +
				"7214296 k\n";
		answer = 95437;
		assertEquals(answer, Day7_NoSpaceLeftOnDevice.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

	}


	@Test
	void runB() {
		String input;
		int answer;

		input = "$ cd /\n" +
				"$ ls\n" +
				"dir a\n" +
				"14848514 b.txt\n" +
				"8504156 c.dat\n" +
				"dir d\n" +
				"$ cd a\n" +
				"$ ls\n" +
				"dir e\n" +
				"29116 f\n" +
				"2557 g\n" +
				"62596 h.lst\n" +
				"$ cd e\n" +
				"$ ls\n" +
				"584 i\n" +
				"$ cd ..\n" +
				"$ cd ..\n" +
				"$ cd d\n" +
				"$ ls\n" +
				"4060174 j\n" +
				"8033020 d.log\n" +
				"5626152 d.ext\n" +
				"7214296 k\n";
		answer = 24933642;
		assertEquals(answer, Day7_NoSpaceLeftOnDevice.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
	}
}