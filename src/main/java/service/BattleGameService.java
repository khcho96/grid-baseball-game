package service;

import domain.GridButtons;
import domain.OutZone;
import domain.vo.Size;
import dto.SizeDto;
import generator.RandomGenerator;
import java.util.ArrayList;
import java.util.List;

public class BattleGameService {

    private GridButtons userGridButtons;
    private GridButtons computerGridButtons;
    private OutZone userOutZone;
    private OutZone computerOutZone;
    private Size size;
    private List<List<Integer>> outCoordinates = new ArrayList<>();

    public SizeDto setInitGame() {
        size = Size.newInstance("5");
        userGridButtons = GridButtons.from(size);
        computerGridButtons = GridButtons.from(size);
        setUserOutZones();
        return SizeDto.newInstance(Integer.parseInt("5"));
    }

    private void setUserOutZones() {
        List<List<Integer>> outCoordinates = new ArrayList<>();
        while (outCoordinates.size() < 3) {
            List<Integer> outCoordinate = RandomGenerator.generateOutZoneCoordinate(size);
            if (!outCoordinates.contains(outCoordinate)) {
                outCoordinates.add(outCoordinate);
            }
        }
        userOutZone = OutZone.of(outCoordinates);
    }

    public String handleUserGridButtonEvent(int x, int y) {
        return userGridButtons.computeEventResult(x, y, userOutZone);
    }

    public void handleComputerGridButtonEvent(int x, int y) {
        outCoordinates.add(List.of(x, y));
        if (outCoordinates.size() == 3) {
            computerOutZone = OutZone.of(outCoordinates);
        }
    }

    public SizeDto handleSizeInputEvent(String sizeInput) {
        size = Size.newInstance(sizeInput);
        userGridButtons = GridButtons.from(size);
        computerGridButtons = GridButtons.from(size);
        setUserOutZones();
        outCoordinates = new ArrayList<>();
        return SizeDto.newInstance(size.getSize());
    }

    public SizeDto handleRestartGame() {
        setUserOutZones();
        outCoordinates = new ArrayList<>();
        return SizeDto.newInstance(size.getSize());
    }

    public String computeRandomClickResult(List<Integer> coordinate) {
        return computerGridButtons.computeEventResult(coordinate.get(0), coordinate.get(1), computerOutZone);
    }
}
