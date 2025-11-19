package domain;

import domain.vo.Coordinate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public int computeContainingOutCount(Set<Coordinate> candidates) {
        Set<Coordinate> outZones = this.outZones.stream()
                .map(GridButton::getCoordinate)
                .collect(Collectors.toSet());
       outZones.retainAll(candidates);

       return outZones.size();
    }
}
