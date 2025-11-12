package domain;

import java.util.ArrayList;
import java.util.List;

public class OutZone {

    private final List<GridButton> outZones;

    private OutZone(List<GridButton> outZone) {
        this.outZones = outZone;
    }

    public static OutZone of(List<List<Integer>> outCoordinates) {
        List<GridButton> outZones = new ArrayList<>();
        for (List<Integer> outCoordinate : outCoordinates) {
            outZones.add(new GridButton(outCoordinate.get(0), outCoordinate.get(1)));
        }
        return new OutZone(outZones);
    }

    public boolean contains(GridButton gridButton) {
        for (GridButton outZone : outZones) {
            if (outZone.equals(gridButton)) {
                return true;
            }
        }
        return false;
    }
}
