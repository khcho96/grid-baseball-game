import generator.RandomOutGenerator;
import java.util.List;
import view.GameView;

public class Application {

    public static void main(String[] args) {
        new GameView();
        List<Integer> outZoneCoordinate1 = RandomOutGenerator.generateOutZoneCoordinate(5);
        List<Integer> outZoneCoordinate2 = RandomOutGenerator.generateOutZoneCoordinate(5);
        List<Integer> outZoneCoordinate3 = RandomOutGenerator.generateOutZoneCoordinate(5);
    }
}
