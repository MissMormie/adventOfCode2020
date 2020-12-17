package helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Coordinate4D extends Coordinate3D {
    int t; // time

    public Coordinate4D(int x, int y, int z, int t) {
        super(x, y, z);
        this.t = t;
    }

    public int getManhattanDistance(Coordinate4D coords) {
        int distance = Math.abs(coords.y - y)
                     + Math.abs(coords.x - x)
                     + Math.abs(coords.z - z)
                     + Math.abs(coords.t - t);
        return distance;
    }

    public List<String> getSurroundingCoords() {
        if(this.surroundingCoords == null) {
            surroundingCoords = new ArrayList<>();
            surroundingCoords = IntStream.rangeClosed(-1, 1)
                    .mapToObj(x -> IntStream.rangeClosed(-1, 1)
                            .mapToObj(y -> IntStream.rangeClosed(-1, 1)
                                    .mapToObj(z -> IntStream.rangeClosed(-1, 1)
                                            .mapToObj(t -> makeCoordString(this.x + x, this.y + y, this.z + z, this.t + t))
                                            .collect(Collectors.toList()))
                                    .flatMap(List::stream)
                                    .collect(Collectors.toList()))
                            .flatMap(List::stream)
                            .collect(Collectors.toList()))
                    .flatMap(List::stream)
                    .collect(Collectors.toList());
        }
        // removing itself.
        surroundingCoords.remove(makeCoordString(x, y, z, t));
        return surroundingCoords;
    }

    public static String makeCoordString(int x, int y, int z, int t) {
        return "" + x + "," + y + "," + z + "," + t;
    }

    @Override
    public String getCoords() {
        return makeCoordString(x, y , z, t);
    }


}
