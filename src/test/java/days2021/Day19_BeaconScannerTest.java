package days2021;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day19_BeaconScannerTest {

	@Test
	void runA() {
		String input;
		int answer;

		input = "--- scanner 0 ---\n" +
				"-618,-824,-621\n" +
				"-537,-823,-458\n" +
				"-447,-329,318\n" +
				"404,-588,-901\n" +
				"544,-627,-890\n" +
				"528,-643,409\n" +
				"-661,-816,-575\n" +
				"390,-675,-793\n" +
				"423,-701,434\n" +
				"-345,-311,381\n" +
				"459,-707,401\n" +
				"-485,-357,347" +
				"\n" +
				"--- scanner 1 ---\n" +
				"686,422,578\n" +
				"605,423,415\n" +
				"515,917,-361\n" +
				"-336,658,858\n" +
				"95,138,22\n" +
				"-476,619,847\n" +
				"-340,-569,-846\n" +
				"567,-361,727\n" +
				"-460,603,-452\n" +
				"669,-402,600\n" +
				"729,430,532\n" +
				"-500,-761,534\n" +
				"-322,571,750\n" +
				"-466,-666,-811\n" +
				"-429,-592,574\n" +
				"-355,545,-477\n" +
				"703,-491,-529\n" +
				"-328,-685,520\n" +
				"413,935,-424\n" +
				"-391,539,-444\n" +
				"586,-435,557\n" +
				"-364,-763,-893\n" +
				"807,-499,-711\n" +
				"755,-354,-619\n" +
				"553,889,-390";
		answer = 25;
		assertEquals(answer, Day19_BeaconScanner.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
	}
//
//
//	@Test
//	void runB() {
//		String input;
//		int answer;
//
//		input = "";
//		answer = 0;
//		assertEquals(answer, Day0.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
//	}
}