package service;

import domain.GridButtons;
import domain.OutZone;
import domain.vo.Size;
import dto.SizeDto;
import generator.RandomOutGenerator;
import java.util.ArrayList;
import java.util.List;

public class BattleGameService {

    private GridButtons gridButtons;
    private OutZone outZone;
    private Size size;
    private final List<List<Integer>> outCoordinates = new ArrayList<>();

    public SizeDto setInitGame() {
        size = Size.newInstance("5");
        gridButtons = GridButtons.from(size);
        setOutZones();
        return SizeDto.newInstance(Integer.parseInt("5"));
    }

    private void setOutZones() {
        List<List<Integer>> outCoordinates = new ArrayList<>();
        while (outCoordinates.size() < 3) {
            List<Integer> outCoordinate = RandomOutGenerator.generateOutZoneCoordinate(size);
            if (!outCoordinates.contains(outCoordinate)) {
                outCoordinates.add(outCoordinate);
            }
        }
        outZone = OutZone.of(outCoordinates);
    }

    public String handleUserGridButtonEvent(int x, int y) {
        return gridButtons.computeEventResult(x, y, outZone);
    }

    public void handleComputerGridButtonEvent(int x, int y) {
        outCoordinates.add(List.of(x, y));
        if (outCoordinates.size() == 3) {
            outZone = OutZone.of(outCoordinates);
        }
    }

    public SizeDto handleSizeInputEvent(String sizeInput) {
        size = Size.newInstance(sizeInput);
        changeSize();
        return SizeDto.newInstance(size.getSize());
    }

    private void changeSize() {
        gridButtons = GridButtons.from(size);
        setOutZones();
    }

    public SizeDto handleRestartGame() {
        setOutZones();
        return SizeDto.newInstance(size.getSize());

    }
}
