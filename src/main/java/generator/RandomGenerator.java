package generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import domain.vo.Size;

public final class RandomGenerator {

    private RandomGenerator() {};

    public static List<Integer> generateOutZoneCoordinate(Size size) {
        Random random = new Random();
        List<Integer> coordinate = new ArrayList<>();
        coordinate.add(random.nextInt(size.getSize()));
        coordinate.add(random.nextInt(size.getSize()));

        return coordinate;
    }

    public static List<Integer> generateCoordinate(Size size) {
        Random random = new Random();
        List<Integer> coordinate = new ArrayList<>();
        coordinate.add(random.nextInt(size.getSize()));
        coordinate.add(random.nextInt(size.getSize()));

        return coordinate;
    }
}
