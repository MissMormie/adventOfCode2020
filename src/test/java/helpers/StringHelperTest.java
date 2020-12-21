package helpers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringHelperTest {

	@Test
	void flipString() {
	}

	@Test
	void flipBlock() {
		String input =
				"123\n" +
				"456\n" +
				"789";

		String result =
				"321\n" +
				"654\n" +
				"987";

		assertEquals(result, StringHelper.flipBlock(input));

	}

	@Test
	void rotateBlockRight() {
		String input =
				"123\n" +
				"456\n" +
				"789";

		String result =
				"741\n" +
				"852\n" +
				"963";

		assertEquals(result, StringHelper.rotateBlockLeft(input));

	}
}