package service;

import constant.Constant;
import domain.GridButtons;
import domain.OutZone;
import domain.SmartComputer;
import domain.vo.Coordinate;
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
    private List<List<Integer>> outCoordinates;
    private SmartComputer smartComputer;

    public SizeDto setInitGame() {
        size = Size.newInstance(Constant.INITIAL_SIZE);
        userGridButtons = GridButtons.from(size);
        computerGridButtons = GridButtons.from(size);
        outCoordinates = new ArrayList<>();
        setUserOutZones();
        return SizeDto.newInstance(Integer.parseInt(Constant.INITIAL_SIZE));
    }

    private void setUserOutZones() {
        List<List<Integer>> outCoordinates = new ArrayList<>();
        while (outCoordinates.size() < Constant.MAX_OUT_COUNT) {
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
        if (outCoordinates.size() == Constant.MAX_OUT_COUNT) {
            computerOutZone = OutZone.of(outCoordinates);
            smartComputer = new SmartComputer(size.getSize(), computerOutZone);
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

    public Coordinate computeSmartCoordinate() {
        return smartComputer.nextGuess();
    }

    public String computeSmartClickResult(Coordinate coordinate) {
        int x = coordinate.getX();
        int y = coordinate.getY();
        String result = computerGridButtons.computeEventResult(x, y, computerOutZone);
        smartComputer.recordResult(coordinate, result);
        return result;
    }
}
