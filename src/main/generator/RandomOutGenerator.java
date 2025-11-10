package main.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import main.domain.Size;

public class RandomOutGenerator {

    public static List<Integer> generateOutZoneCoordinate(Size size) {
        Random random = new Random();
        List<Integer> coordinate = new ArrayList<>();
        coordinate.add(random.nextInt(size.getSize()));
        coordinate.add(random.nextInt(size.getSize()));

        return coordinate;
    }
}
