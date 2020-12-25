package days;

public class Day25_ComboBreaker {

	public static void main(String[] args) {
		System.out.println("answer A: " + runA(8335663, 8614349));
	}

	public static long runA(long cardKey, long doorKey) {
		long cardLoopSize = findLoopSize(cardKey);
		long value = 1;
		for (int i = 0; i < cardLoopSize; i++) {
			value = transform(value, doorKey);
		}
		return value;
	}

	public static long transform(long value, long subjectNumber) {
		return (value * subjectNumber) % 20201227;
	}

	private static long subjectNumber = 7;
	public static long findLoopSize(long key) {
		long value = 1;

		for(long i = 1; true; i++) {
			value = transform(value, subjectNumber);
			if(value == key) {
				return i;
			}
		}

	}

}
