package domain;

import java.util.ArrayList;
import java.util.List;

public class OutZone {

    private final List<GridButton> outZones;

    public OutZone(List<GridButton> outZone) {
        this.outZones = outZone;
    }

    public static OutZone of(List<Integer> outCoordinate1, List<Integer> outCoordinate2, List<Integer> outCoordinate3) {
        List<GridButton> outZones = new ArrayList<>();
        outZones.add(new GridButton(outCoordinate1.get(0), outCoordinate1.get(1)));
        outZones.add(new GridButton(outCoordinate2.get(0), outCoordinate2.get(1)));
        outZones.add(new GridButton(outCoordinate3.get(0), outCoordinate3.get(1)));

        return new OutZone(outZones);
    }


    public boolean contains(GridButton gridButton) {
        for (GridButton outZone : outZones) {
            return outZone.equals(gridButton);
        }
        return false;
    }
}
