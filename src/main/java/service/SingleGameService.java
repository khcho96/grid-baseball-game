package service;

import java.util.ArrayList;
import java.util.List;
import domain.GridButtons;
import domain.OutZone;
import domain.vo.Size;
import dto.SizeDto;
import generator.RandomOutGenerator;

public class SingleGameService {

    private GridButtons gridButtons;
    private OutZone outZone;
    private Size size;

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

    public String handleGridButtonEvent(int x, int y) {
        return gridButtons.computeEventResult(x, y, outZone);
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
