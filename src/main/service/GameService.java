package main.service;

import static main.constant.Constant.SIZE;

import java.util.ArrayList;
import java.util.List;
import main.domain.GridButtons;
import main.domain.OutZone;
import main.generator.RandomOutGenerator;

public class GameService {

    private GridButtons gridButtons;
    private OutZone outZone;

    public void setGame() {
        gridButtons = GridButtons.from(SIZE);
        List<List<Integer>> outCoordinates = new ArrayList<>();
        while (outCoordinates.size() < 3) {
            List<Integer> outCoordinate = RandomOutGenerator.generateOutZoneCoordinate(SIZE);
            if (!outCoordinates.contains(outCoordinate)) {
                outCoordinates.add(outCoordinate);
            }
        }
        outZone = OutZone.of(outCoordinates);
    }

    public String handleGridButtonEvent(int x, int y) {
        return gridButtons.computeEventResult(x, y, outZone);
    }
}
