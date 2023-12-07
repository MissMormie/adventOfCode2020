package days2023;

import helpers.InputProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.stream.Stream;

public class Day6WaitForit {

  public static int day = 6;
  public static int year = 2023;

  public static void main(String[] args) throws IOException {
    System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day))); // 133 low
    System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
  }

  public static int runA(Stream<String> input) {
    return getNumberOfWaysToBeat(40, 215) *
           getNumberOfWaysToBeat(70, 1051) *
           getNumberOfWaysToBeat(98, 2147) *
           getNumberOfWaysToBeat(79, 1005);
  }

  public static int getNumberOfWaysToBeat(int time, int distance) {
    int waysToBeat = 0;
    for (int i = 0; i <= time; i++) {
      //      Your toy boat has a starting speed of zero millimeters per millisecond. For each whole millisecond you spend at
      //      the beginning of the race holding down the button, the boat's speed increases by one millimeter per millisecond.

      if (i * (time - i) > distance) {
        waysToBeat++;
      }
      else if (waysToBeat > 0) {
        return waysToBeat;
      }
    }
    return waysToBeat;
  }

  public static double runB(Stream<String> input) {
    //    getNumberOfWaysToBeat(40709879, 215_1051_2147_1005)
    // maths on paper.
    return getQuadraticDifference(1, 40709879, 215_1051_2147_1005l);

  }

  public static double getQuadraticDifference(long a, long b, long c) {
    double discriminant = Math.sqrt(Math.pow(b, 2) - 4 * a * c);
    return ((-b + discriminant) / (2 * a)) -
           ((-b - discriminant) / (2 * a));
  }

  public static BigDecimal getQuadratic(BigDecimal a, BigDecimal b, BigDecimal c) {
    BigDecimal discriminant = b.pow(2).subtract(a.multiply(c).multiply(new BigDecimal(4))).sqrt(MathContext.DECIMAL32);
    return discriminant.setScale(0, RoundingMode.FLOOR);
    //    BigDecimal lower =
    //        b.negate().add(discriminant).divide(a.multiply(new BigDecimal(2)).setScale(BigDecimal.ROUND_CEILING));
    //    BigDecimal higher =
    //        b.negate().min(discriminant).divide(a.multiply(new BigDecimal(2)).setScale(BigDecimal.ROUND_FLOOR));
    //    return higher.subtract(lower);
  }

}
