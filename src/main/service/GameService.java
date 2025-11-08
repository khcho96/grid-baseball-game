package main.service;

import main.GridButtons;
import main.OutZone;
import main.RandomOutGenerator;
import java.util.ArrayList;
import java.util.List;

public class GameService {

    private GridButtons gridButtons;
    private OutZone outZone;

    public void setGame() {
        gridButtons = main.domain.GridButtons.from(5);
        List<List<Integer>> outCoordinates = new ArrayList<>();
        while (outCoordinates.size() < 3) {
            List<Integer> outCoordinate = RandomOutGenerator.generateOutZoneCoordinate(5);
            if (!outCoordinates.contains(outCoordinate)) {
                outCoordinates.add(outCoordinate);
            }
        }
        outZone = main.domain.OutZone.of(outCoordinates);
    }

    public String processGridButtonEvent(int x, int y) {
        return gridButtons.computeEventResult(x, y, outZone);
    }
}
