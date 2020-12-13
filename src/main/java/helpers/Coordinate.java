package helpers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Coordinate implements Comparable<Coordinate>{
    public int x;
    public int y;
    public List<Coordinate> adjacentCoords;

    public int intValue;

    public long longValue;

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

    public void saveAdjacent(Map<String,Coordinate> coordinateMap) {
        adjacentCoords = new ArrayList();

        for(int x = -1; x < 2; x++ ) {
            for(int y = -1; y < 2; y++ ) { // loop adjecent acres
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
    public boolean equals(Object obj) {
        if(!(obj instanceof Coordinate)) {
            return false;
        }

        Coordinate coord = (Coordinate) obj;
        return coord.x == x && coord.y == y;
    }

    @Override
    public int compareTo(Coordinate o) {
        if(o.y == this.y) {
            return this.x - o.x;
        }
        return this.y - o.y;
    }
}
