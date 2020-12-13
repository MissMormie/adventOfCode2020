package helpers;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class DirectionalMovingObject {
    // Should not extend coords but have it as a variable.

    public enum Direction {
        LEFT, UP, RIGHT, DOWN;
    }

    public Coordinate coordinate;
    public Direction direction;
    Direction[] directions = Direction.values();
    public Set<String> visitedCoords = new HashSet<>();


    public DirectionalMovingObject(int x, int y, Direction dir) {
        coordinate = new Coordinate(x, y);
        visitedCoords.add(coordinate.getCoords());
        direction = dir;
    }

    public void turnRight() {
        int newDirection = (direction.ordinal() + 1) % directions.length;
        direction = directions[newDirection];
    }

    public void turnLeft() {
        int newDirection = direction.ordinal() == 0 ? directions.length - 1 : (direction.ordinal() - 1);
        direction = directions[newDirection];
    }

    public void turnAround() {
        int newDirection = (direction.ordinal() + 2) % directions.length;
        direction = directions[newDirection];
    }

    public void move(int steps) {
        IntStream.range(0, steps)
                .forEach(i-> move());
    }

    public void moveBy(Coordinate coordinate) {
        moveBy(coordinate.x, coordinate.y);
    }

    public void moveBy(int x, int y){
        this.coordinate.x += x;
        this.coordinate.y += y;

    }

    public void moveInDirection(Direction dir) {
        direction = dir;
        move();
    }

    public void turnInDirection(Direction direction) {
        this.direction = direction;
    }

    public void move() {
        if(direction == Direction.UP) {
            coordinate.y++;
        } else if(direction == Direction.DOWN) {
            coordinate.y--;
        } else if(direction == Direction.LEFT) {
            coordinate.x--;
        } else if(direction == Direction.RIGHT) {
            coordinate.x++;
        }

        // Save new coordinate to path.
        visitedCoords.add(coordinate.getCoords());
    }

}
