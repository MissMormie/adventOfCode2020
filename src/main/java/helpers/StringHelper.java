package helpers;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringHelper {
	public static String sortCharsInStringAlphabetically(String inputString) {
		// convert input string to char array
		char[] tempArray = inputString.toCharArray();

		// sort tempArray
		Arrays.sort(tempArray);

		// return new sorted string
		return new String(tempArray);
	}

	public static List<Integer> getListOfNumbersSeperatedBy(String input, String regex) {
		return Arrays.stream(input.split(regex)).map(Integer::parseInt).collect(Collectors.toList());
	}

	public static List<BigInteger> getListOfBigIntegerSeperatedBy(String input, String regex) {
		input = input.replace(" ", "");
		return Arrays.stream(input.split(regex)).map(stringNum -> {
					// Big ints dont like negatives too well.
					if(stringNum.indexOf('-') > -1) {
						return new BigInteger(stringNum.substring(1)).negate();
					} else {
						return new BigInteger(stringNum);
					}
				}
		).collect(Collectors.toList());
	}

	public static String flipString(String string) {
		char[] chars = string.toCharArray();
		StringBuilder sb = new StringBuilder();
		IntStream.range(0, chars.length).forEach(i -> sb.append(chars[chars.length-1 - i]));
		return sb.toString();
	}

	/**
	 * Flip a block of strings that are separated by \n
	 * @param string
	 * @return
	 */
	public static String flipBlock(String string) {
		return Arrays.stream(string.split("\n"))
		.map(StringHelper::flipString)
		.collect(Collectors.joining("\n"));
	}

	public static String rotateBlockLeft(String block) {
		String[] split = block.split("\n");
		List<StringBuilder> stringBuilders = new ArrayList<>();
		IntStream.range(0, split[0].length()).forEach(i -> stringBuilders.add(new StringBuilder()));

		IntStream.range(0, split.length).forEach(i -> {
			String row = split[split.length -1 -i];
			IntStream.range(0, stringBuilders.size()).forEach(j -> {
				stringBuilders.get(j).append(row.charAt(j));
			});
		});

		return stringBuilders.stream().map(sb -> sb.toString())
				.collect(Collectors.joining("\n"));
	}

	public static String numberToStringWithXPositions(int i) {
		StringBuilder s = new StringBuilder(String.valueOf(i));
		while (s.length() < 6) {
			s.append("0");
		}
		return s.toString();
	}
	public static List<Integer> getNumbersFromStringOnePerLine(String input) {
		String[] split = input.split("\n");
		return Arrays.stream(split).map(Integer::parseInt).collect(Collectors.toList());
	}

	public static List<Integer> getNumbersFromStringTabSeperated(String input) {
		String[] split = input.split("\t");
		return Arrays.stream(split).map(Integer::parseInt).collect(Collectors.toList());
	}

//	public static CircularLinkedList<Integer> getCircularLinkedListNumbersFromStringTabSeperated(String input) {
//		List<Integer> numbers = getNumbersFromStringTabSeperated(input);
//		CircularLinkedList<Integer> linkedList = new CircularLinkedList<>();
//		numbers.forEach(linkedList::addObject);
//		return linkedList;
//	}
}