package main.service;

import java.util.ArrayList;
import java.util.List;
import main.domain.GridButtons;
import main.domain.OutZone;
import main.generator.RandomOutGenerator;

public class GameService {

    private GridButtons gridButtons;
    private OutZone outZone;

    public void setGame() {
        gridButtons = GridButtons.from(5);
        List<List<Integer>> outCoordinates = new ArrayList<>();
        while (outCoordinates.size() < 3) {
            List<Integer> outCoordinate = RandomOutGenerator.generateOutZoneCoordinate(5);
            if (!outCoordinates.contains(outCoordinate)) {
                outCoordinates.add(outCoordinate);
            }
        }
        outZone = OutZone.of(outCoordinates);
    }

    public String processGridButtonEvent(int x, int y) {
        return gridButtons.computeEventResult(x, y, outZone);
    }
}
