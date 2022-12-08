package helpers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Coordinate implements Comparable<Coordinate>{
    public int x;
    public int y;
    public List<Coordinate> adjacentCoords;

    public int intValue;

    public long longValue;

    public boolean booleanValue;

    public boolean newBooleanValue;

    public BigInteger getBigIntegerValue() {
        return bigIntegerValue;
    }

    public void setBigIntegerValue(BigInteger bigIntegerValue) {
        this.bigIntegerValue = bigIntegerValue;
    }

    public BigInteger bigIntegerValue;

    public long getLongValue() {
        return longValue;
    }

    public void setLongValue(long longValue) {
        this.longValue = longValue;
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate(String xCommaY) {
        String[] split = xCommaY.split(",");
        this.x = Integer.parseInt(split[0]);
        this.y = Integer.parseInt(split[1]);
    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public int getManhattanDistance(Coordinate coords) {
        return getManhattanDistance(coords.x, coords.y);
    }

    public int getManhattanDistance(int x, int y) {
        return Math.abs(y - this.y) + Math.abs(x - this.x);
    }

    public String getCoords() {
        return makeCoordString(x, y);
    }

    public Coordinate clone() {
        Coordinate coordinate = new Coordinate(x, y);
        coordinate.adjacentCoords = adjacentCoords;
        coordinate.bigIntegerValue = bigIntegerValue;
        coordinate.intValue = intValue;
        coordinate.longValue = longValue;
        return coordinate;
    }

    public void getAdjacent(Map<String,? extends Coordinate> coordinateMap) {
        adjacentCoords = new ArrayList();

        for(int x = -1; x < 2; x++ ) {
            for(int y = -1; y < 2; y++ ) { // loop adjacent acres
                String coords = makeCoordString(this.x - x, this.y -y);
                if(!coords.equals(getCoords())) {
                    Coordinate coord = coordinateMap.get(coords);
                    if (coord != null) {
                        adjacentCoords.add(coord);
                    }
                }
            }
        }
    }

    public List<? extends Coordinate> getOctagonalAdjacentCoords(Map<String, ? extends Coordinate> coordinateMap) {
        List<String> coordString = new ArrayList<>();
        coordString.add(makeCoordString(x-1, y));
        coordString.add(makeCoordString(x+1, y));
        coordString.add(makeCoordString(x, y-1));
        coordString.add(makeCoordString(x, y+1));

        List<Coordinate> octagonalAdjacentCoords = new ArrayList<>();
        for(String singleCoordString : coordString) {
            if(coordinateMap.containsKey(singleCoordString)) {
                octagonalAdjacentCoords.add(coordinateMap.get(singleCoordString));
            }
        }
        return octagonalAdjacentCoords;
    }

    public static String makeCoordString(int x, int y) {
        return "" + x + "," + y;
    }

    public boolean sameSpace(Coordinate coord) {
        if(coord.y == this.y && coord.x == this.x) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinate that = (Coordinate) o;

        if (x != that.x) return false;
        return y == that.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public int compareTo(Coordinate o) {
        if(o.y == this.y) {
            return this.x - o.x;
        }
        return this.y - o.y;
    }

    public static void printHashForCoordinate(Map<String, ? extends Coordinate> coordinateMap) {
        printValuesForCoordinateMap(coordinateMap, coord -> "#");
    }

    public static void printIntValuesForCoordinate(Map<String, ? extends Coordinate> coordinateMap) {
        printValuesForCoordinateMap(coordinateMap, coord -> "" + coord.intValue);
    }

    public static void printValuesForCoordinateIfBooleanTrue(Map<String, ? extends Coordinate> coordinateMap) {
        printValuesForCoordinateMap(coordinateMap, coord -> {
            if(coord.booleanValue) {
                return "" + coord.intValue;
            } else {
                return " ";
            }
        });
    }


    public static void printValuesForCoordinateMap(Map<String, ? extends Coordinate> coordinateMap, Function<Coordinate, String> function) {
        Integer minX, maxX, minY, maxY;
        minX = maxX = minY = maxY = null;
        for(Map.Entry<String, ? extends Coordinate> entry: coordinateMap.entrySet()) {
            Coordinate coordinate = entry.getValue();
            if(minX == null) {
                minX = maxX = coordinate.x;
                minY = maxY = coordinate.y;
            }
            minX = coordinate.x < minX ? coordinate.x : minX;
            maxX = coordinate.x > maxX ? coordinate.x : maxX;
            minY = coordinate.y < minY ? coordinate.y : minY;
            maxY = coordinate.y > maxY ? coordinate.y : maxY;
        }

        for(int y = minY; y <= maxY; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = minX; x <= maxX; x++) {
                if(coordinateMap.containsKey(Coordinate.makeCoordString(x,y))) {
                    Coordinate coordinate = coordinateMap.get(Coordinate.makeCoordString(x, y));
                    sb.append(function.apply(coordinate));
                } else {
                    sb.append(" ");
                }
            }
            System.out.println(sb);
        }
        System.out.println("------------------------------");
    }
}
