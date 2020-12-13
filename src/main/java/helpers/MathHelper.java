package helpers;

public class MathHelper {
	public static int lcm(int number1, int number2) {
		if (number1 == 0 || number2 == 0) {
			return 0;
		}
		int absNumber1 = Math.abs(number1);
		int absNumber2 = Math.abs(number2);
		int absHigherNumber = Math.max(absNumber1, absNumber2);
		int absLowerNumber = Math.min(absNumber1, absNumber2);
		int lcm = absHigherNumber;
		while (lcm % absLowerNumber != 0) {
			lcm += absHigherNumber;
		}
		return lcm;
	}

	public static int chineseRemainedTheorem(int num[], int rem[], int k)
	{
		int x = 1; // Initialize result

		// As per the Chinese remainder theorem,
		// this loop will always break.
		while (true)
		{
			// Check if remainder of x % num[j] is
			// rem[j] or not (for all j from 0 to k-1)
			int j;
			for (j=0; j<k; j++ )
				if (x%num[j] != rem[j])
					break;

			// If all remainders matched, we found x
			if (j == k)
				return x;

			// Else try next numner
			x++;
		}

	}
}
