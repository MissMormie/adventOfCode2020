package helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Coordinate3D extends Coordinate {
    int z;

    List<String> surroundingCoords;
    List<String> octagonalSurroundingCoords;

    public Coordinate3D(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    public Coordinate3D(String xCommaYCommaZ) {
        super(xCommaYCommaZ);
        String[] split = xCommaYCommaZ.split(",");
        this.z = Integer.parseInt(split[2]);
    }

    public int getManhattanDistance(Coordinate3D coords) {
        int distance = Math.abs(coords.y - y) + Math.abs(coords.x - x) + Math.abs(coords.z - z);
        return distance;
    }

    @Override
    public String toString() {
        return "Coordinate3D{" +
                "z=" + z +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public String getCoords() {
        return makeCoordString(x, y , z);
    }

    public static String makeCoordString(int x, int y, int z) {
        return "" + x + "," + y + "," + z;
    }

    /**
     * Gets the coord strings for the six sides that octagonally surround the coord
     */
    public List<String> getOctagonalSurroundingCoords() {
        if(this.octagonalSurroundingCoords == null) {
            octagonalSurroundingCoords = new ArrayList<>();
            octagonalSurroundingCoords.add(makeCoordString(x, y, z-1));
            octagonalSurroundingCoords.add(makeCoordString(x, y, z+1));
            octagonalSurroundingCoords.add(makeCoordString(x, y -1, z));
            octagonalSurroundingCoords.add(makeCoordString(x, y +1, z));
            octagonalSurroundingCoords.add(makeCoordString(x -1, y, z));
            octagonalSurroundingCoords.add(makeCoordString(x +1, y, z));
        }
        return octagonalSurroundingCoords;
    }

    public List<String> getSurroundingCoords() {
        if(this.surroundingCoords == null) {
            surroundingCoords = new ArrayList<>();
            surroundingCoords = IntStream.rangeClosed(-1, 1)
                    .mapToObj(x -> IntStream.rangeClosed(-1, 1)
                            .mapToObj(y -> IntStream.rangeClosed(-1, 1)
                                    .mapToObj(z -> makeCoordString(this.x + x, this.y + y, this.z + z))
                                    .collect(Collectors.toList()))
                            .flatMap(List::stream)
                            .collect(Collectors.toList()))
                    .flatMap(List::stream)
                    .collect(Collectors.toList());
        }
        // removing itself.
        surroundingCoords.remove(makeCoordString(x, y, z));
        return surroundingCoords;
    }
}
