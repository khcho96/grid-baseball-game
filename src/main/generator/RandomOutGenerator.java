package main.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomOutGenerator {

    public static List<Integer> generateOutZoneCoordinate(int size) {
        Random random = new Random();
        List<Integer> coordinate = new ArrayList<>();
        coordinate.add(random.nextInt(size));
        coordinate.add(random.nextInt(size));

        return coordinate;
    }
}
