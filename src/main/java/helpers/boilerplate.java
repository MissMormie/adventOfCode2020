package helpers;

import java.io.IOException;
import java.util.stream.Stream;

public class boilerplate {
    public static int day = 0;
    public static int year = 2022;

    public static void main(String[] args) throws IOException {
        System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
        System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
    }

    public static int runA(Stream<String> input) {
        return 0;
    }


    public static int runB(Stream<String> input) {
        return 0;
    }

}