package days2020;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day21_AllergenAssessmentTest {
	@Test
	void runA() {
		String input;
		int answer;

		input = "mxmxvkd kfcds sqjhc nhms (contains dairy, fish)\n" +
				"trh fvjkl sbzzf mxmxvkd (contains dairy)\n" +
				"sqjhc fvjkl (contains soy)\n" +
				"sqjhc mxmxvkd sbzzf (contains fish)";
		answer = 5;
		assertEquals(answer, Day21_AllergenAssessment.runA(input), "input = " + input + " answer: " + answer);

	}


	@Test
	void runB() {
		String input;
		String answer;

		input = "mxmxvkd kfcds sqjhc nhms (contains dairy, fish)\n" +
				"trh fvjkl sbzzf mxmxvkd (contains dairy)\n" +
				"sqjhc fvjkl (contains soy)\n" +
				"sqjhc mxmxvkd sbzzf (contains fish)";
		answer = "mxmxvkd,sqjhc,fvjkl";
		assertEquals(answer, Day21_AllergenAssessment.runB(input), "input = " + input + " answer: " + answer);
	}
}