package main.domain;

import java.util.ArrayList;
import java.util.List;

public class OutZone {

    private final List<main.domain.GridButton> outZones;

    public OutZone(List<main.domain.GridButton> outZone) {
        this.outZones = outZone;
    }

    public static OutZone of(List<List<Integer>> outCoordinates) {
        List<main.domain.GridButton> outZones = new ArrayList<>();
        for (List<Integer> outCoordinate : outCoordinates) {
            outZones.add(new main.domain.GridButton(outCoordinate.get(0), outCoordinate.get(1)));
        }
        return new OutZone(outZones);
    }

    public boolean contains(main.domain.GridButton gridButton) {
        for (main.domain.GridButton outZone : outZones) {
            if (outZone.equals(gridButton)) {
                return true;
            }
        }
        return false;
    }
}
