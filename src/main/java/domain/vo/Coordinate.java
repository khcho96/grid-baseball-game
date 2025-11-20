package domain.vo;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Coordinate {

    private final int x;
    private final int y;
    private final List<List<Integer>> strikeZones = List.of(
            List.of(-1, 0), // 상
            List.of(1, 0), // 하
            List.of(0, -1), // 좌
            List.of(0, 1) // 우
    );
    private final List<List<Integer>> ballZones = List.of(
            List.of(-1, -1), // 좌상
            List.of(-1, 1), // 우상
            List.of(1, -1), // 좌하
            List.of(1, 1) // 우하
    );

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Iterator<Coordinate> getNeighbors(int size) {
        Set<Coordinate> neighbors = new HashSet<>();
        neighbors.addAll(getStrikeZones(size));
        neighbors.addAll(getBallZones(size));

        return neighbors.iterator();
    }

    public Set<Coordinate> getStrikeZones(int size) {
        return getZones(size, strikeZones);
    }

    public Set<Coordinate> getBallZones(int size) {
        return getZones(size, ballZones);
    }

    private Set<Coordinate> getZones(int size, List<List<Integer>> zones) {
        Set<Coordinate> neighbors = new HashSet<>();

        for (List<Integer> zone : zones) {
            int x = this.x + zone.get(0);
            int y = this.y + zone.get(1);
            if (x < 0 || y < 0 || x >= size || y >= size) {
                continue;
            }
            neighbors.add(new Coordinate(x, y));
        }

        return neighbors;
    }
}
