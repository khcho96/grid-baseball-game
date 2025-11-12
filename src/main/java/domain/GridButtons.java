package domain;

import java.util.ArrayList;
import java.util.List;
import domain.vo.Size;

public class GridButtons {

    private final List<List<GridButton>> gridButtons;

    public GridButtons(List<List<GridButton>> gridButtons) {
        this.gridButtons = gridButtons;
    }

    public static GridButtons from(Size size) {
        List<List<GridButton>> gridButtons = new ArrayList<>();
        for (int x = 0; x < size.getSize(); x++) {
            gridButtons.add(new ArrayList<>());
            for (int y = 0; y < size.getSize(); y++) {
                gridButtons.get(x).add(new GridButton(x, y));
            }
        }

        return new GridButtons(gridButtons);
    }

    public String computeEventResult(int x, int y, OutZone outZone) {
        GridButton clickedButton = gridButtons.get(x).get(y);
        if (clickedButton.isOut(outZone)) {
            return "Out!⚾";
        }

        String strikeCount = "<html><span style='color:#D2A641;'>" + computeStrikeCount(x, y, outZone) + "S </span>";
        String ballCount = "<span style='color:#377E22;'>" + computeBallCount(x, y, outZone) + "B</span></html>";

        return strikeCount + ballCount;

    }

    private int computeStrikeCount(int x, int y, OutZone outZone) {
        int strikeCount = 0;

        // 상
        if (x > 0) {
            if (gridButtons.get(x - 1).get(y).isOut(outZone)) {
                strikeCount++;
            }
        }

        // 하
        if (x < gridButtons.size() - 1) {
            if (gridButtons.get(x + 1).get(y).isOut(outZone)) {
                strikeCount++;
            }
        }

        // 좌
        if (y > 0) {
            if (gridButtons.get(x).get(y - 1).isOut(outZone)) {
                strikeCount++;
            }
        }

        // 우
        if (y < gridButtons.size() - 1) {
            if (gridButtons.get(x).get(y + 1).isOut(outZone)) {
                strikeCount++;
            }
        }

        return strikeCount;
    }

    private int computeBallCount(int x, int y, OutZone outZone) {
        int ballCount = 0;

        // 좌상
        if (x > 0 && y > 0) {
            if (gridButtons.get(x - 1).get(y - 1).isOut(outZone)) {
                ballCount++;
            }
        }

        // 우상
        if (x > 0 && y < gridButtons.size() - 1) {
            if (gridButtons.get(x - 1).get(y + 1).isOut(outZone)) {
                ballCount++;
            }
        }

        // 좌하
        if (x < gridButtons.size() - 1 && y > 0) {
            if (gridButtons.get(x + 1).get(y - 1).isOut(outZone)) {
                ballCount++;
            }
        }

        // 우하
        if (x < gridButtons.size() - 1 && y < gridButtons.size() - 1) {
            if (gridButtons.get(x + 1).get(y + 1).isOut(outZone)) {
                ballCount++;
            }
        }

        return ballCount;
    }
}
