package service;

import domain.GridButtons;
import domain.OutZone;
import generator.RandomOutGenerator;
import java.util.ArrayList;
import java.util.List;

public class GameService {

    private GridButtons gridButtons;
    private OutZone outZone;

    public void setGame() {
        gridButtons = GridButtons.from(5);
        List<Integer> outCoordinate1 = RandomOutGenerator.generateOutZoneCoordinate(5);
        List<Integer> outCoordinate2 = RandomOutGenerator.generateOutZoneCoordinate(5);
        List<Integer> outCoordinate3 = RandomOutGenerator.generateOutZoneCoordinate(5);
        outZone = OutZone.of(outCoordinate1, outCoordinate2, outCoordinate3);
    }

    public String processGridButtonEvent(int x, int y) {
        return gridButtons.computeEventResult(x, y, outZone);
    }
}
