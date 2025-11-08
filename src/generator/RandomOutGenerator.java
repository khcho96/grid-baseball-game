package generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomOutGenerator {

    public static List<Integer> generateOutZoneCoordinate(int max) {
        Random random = new Random();
        List<Integer> coordinate = new ArrayList<>();
        coordinate.add(random.nextInt(max));
        coordinate.add(random.nextInt(max));

        return coordinate;
    }
}
