package domain;

import domain.vo.Coordinate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class SmartComputer {

    private final int size;
    private final Set<Coordinate> outConfirmed;
    private final Set<Coordinate> unselectedCandidates;
    private List<Set<Coordinate>> outCandidates;
    private final OutZone outZone;

    public SmartComputer(int size, OutZone outZone) {
        this.size = size;
        outConfirmed = new HashSet<>();
        unselectedCandidates = generateAllCandidates();
        outCandidates = new ArrayList<>();
        this.outZone = outZone;
    }

    private Set<Coordinate> generateAllCandidates() {
        Set<Coordinate> all = new HashSet<>();
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                all.add(new Coordinate(x, y));
            }
        }
        return all;
    }

    public Coordinate nextGuess() {
        Map<Coordinate, Integer> priority = new HashMap<>();

        for (Set<Coordinate> outCandidate : outCandidates) {
            calculatePriority(outCandidate, priority);
        }

        if (!priority.isEmpty()) {
            return decideNextCoordinateFromPriority(priority);
        }

        calculatePriority(unselectedCandidates, priority);

        return decideNextCoordinateFromPriority(priority);
    }

    private void calculatePriority(Set<Coordinate> outCandidate, Map<Coordinate, Integer> priority) {
        for (Coordinate coordinate : outCandidate) {
            if (!priority.containsKey(coordinate)) {
                priority.put(coordinate, 0);

                Iterator<Coordinate> neighbors = coordinate.getNeighbors(size);
                while (neighbors.hasNext()) {
                    Coordinate neighbor = neighbors.next();

                    if (unselectedCandidates.contains(neighbor)) {
                        priority.put(coordinate, priority.get(coordinate) + 1);
                    }
                }
            }
        }
    }

    private Coordinate decideNextCoordinateFromPriority(Map<Coordinate, Integer> priority) {
        List<Coordinate> nextCoordinates = new ArrayList<>();
        int maxUnselectedCandidatesCount = 0;

        for (Entry<Coordinate, Integer> coordinateIntegerEntry : priority.entrySet()) {
            Coordinate key = coordinateIntegerEntry.getKey();
            Integer value = coordinateIntegerEntry.getValue();
            
            if (maxUnselectedCandidatesCount == value) {
                nextCoordinates.add(key);
                continue;
            }
            
            if (maxUnselectedCandidatesCount < value) {
                maxUnselectedCandidatesCount = value;
                nextCoordinates = new ArrayList<>();
                nextCoordinates.add(key);
            }
        }

        Collections.shuffle(nextCoordinates);
        return nextCoordinates.getFirst();
    }

    public void recordResult(Coordinate selectedCoordinate, String result) {
        unselectedCandidates.remove(selectedCoordinate);

        if (result.startsWith("Out")) {
            outConfirmed.add(selectedCoordinate);
            filterCandidatesFromOut(selectedCoordinate);
            return;
        }

        RemoveInOutCandidates(selectedCoordinate);

        List<Set<Coordinate>> newOutCandidates = new ArrayList<>();
        String stripped = result.replaceAll("<[^>]*>", "").strip();
        String[] parts = stripped.split(" ");
        int strike = Integer.parseInt(parts[0].replace("S", "").strip());
        int ball = Integer.parseInt(parts[1].replace("B", "").strip());

        updateFromStrike(selectedCoordinate, strike, newOutCandidates);
        updateFromBall(selectedCoordinate, ball, newOutCandidates);

        if (!newOutCandidates.isEmpty()) {
            filterIfOutCandidateSizeThree(newOutCandidates);
            mergeFinalOutCandidates(newOutCandidates);
        }
    }

    private void RemoveInOutCandidates(Coordinate selectedCoordinate) {
        for (Set<Coordinate> outCandidate : outCandidates) {
            outCandidate.remove(selectedCoordinate);
        }
    }

    private void updateFromStrike(Coordinate selectedCoordinate, int strike, List<Set<Coordinate>> newOutCandidates) {
        if (strike == 0) {
            filterCandidatesFromNoStrike(selectedCoordinate);
        } else {
            List<Set<Coordinate>> newCandidates = updateOutCandidatesFromStrike(selectedCoordinate, strike);
            newOutCandidates.addAll(newCandidates);
        }
    }

    private void updateFromBall(Coordinate selectedCoordinate, int ball, List<Set<Coordinate>> newOutCandidates) {
        if (ball == 0) {
            filterCandidatesFromNoBall(selectedCoordinate);
        } else {
            List<Set<Coordinate>> newCandidates = updateOutCandidatesFromBall(selectedCoordinate, ball);
            newOutCandidates.addAll(newCandidates);
        }
    }

    private void filterIfOutCandidateSizeThree(List<Set<Coordinate>> newOutCandidates) {
        if (outCandidates.size() == 3) {
            for (Set<Coordinate> newOutCandidate : newOutCandidates) {
                newOutCandidate.removeIf(this::outCandidatesDoNotContain);
            }
            unselectedCandidates.removeIf(this::outCandidatesDoNotContain);
        }
    }

    private List<Set<Coordinate>> updateOutCandidatesFromStrike(Coordinate strikeCoordinate, int strike) {
        List<Set<Coordinate>> newOutCandidates = new ArrayList<>();

        for (int i = 0; i < strike; i++) {
            newOutCandidates.add(new HashSet<>());
        }

        Set<Coordinate> strikeZones = getCoordinates(strikeCoordinate.getStrikeZones(size), newOutCandidates);

        if (newOutCandidates.isEmpty()) {
            for (Set<Coordinate> outCandidate : outCandidates) {
                outCandidate.removeIf(strikeZones::contains);
            }
            unselectedCandidates.removeIf(strikeZones::contains);
        }

        return newOutCandidates;
    }

    private List<Set<Coordinate>> updateOutCandidatesFromBall(Coordinate ballCoordinate, int ball) {
        List<Set<Coordinate>> newOutCandidates = new ArrayList<>();

        for (int i = 0; i < ball; i++) {
            newOutCandidates.add(new HashSet<>());
        }

        Set<Coordinate> ballZones = getCoordinates(ballCoordinate.getBallZones(size), newOutCandidates);

        if (newOutCandidates.isEmpty()) {
            for (Set<Coordinate> outCandidate : outCandidates) {
                outCandidate.removeIf(ballZones::contains);
            }
            unselectedCandidates.removeIf(ballZones::contains);
        }

        return newOutCandidates;
    }

    private Set<Coordinate> getCoordinates(Set<Coordinate> coordinates, List<Set<Coordinate>> newOutCandidates) {
        for (Coordinate coordinate : coordinates) {
            if (outConfirmed.contains(coordinate)) {
                newOutCandidates.removeFirst();
            }

            if (unselectedCandidates.contains(coordinate)) {
                for (Set<Coordinate> newOutCandidate : newOutCandidates) {
                    newOutCandidate.add(coordinate);
                }
            }
        }
        return coordinates;
    }

    private void mergeFinalOutCandidates(List<Set<Coordinate>> newOutCandidates) {
        List<Set<Coordinate>> finalOutCandidates = new ArrayList<>();
        Set<List<Set<Coordinate>>> pairs = new HashSet<>();

        if (outCandidates.isEmpty()) {
            outCandidates = new ArrayList<>(newOutCandidates);
            return;
        }

        excludeNoIntersection(newOutCandidates, finalOutCandidates);

        calculateComparedPairs(newOutCandidates, pairs);

        decideFinalOutCandidates(pairs, finalOutCandidates);

        outCandidates = finalOutCandidates;
    }

    private void decideFinalOutCandidates(Set<List<Set<Coordinate>>> pairs, List<Set<Coordinate>> finalOutCandidates) {
        for (List<Set<Coordinate>> pair : pairs) {
            Set<Coordinate> pre = pair.get(0);
            Set<Coordinate> cur = pair.get(1);

            Set<Coordinate> preDiff = new HashSet<>(pre);
            Set<Coordinate> curDiff = new HashSet<>(cur);
            Set<Coordinate> intersect = new HashSet<>(pre);

            preDiff.removeAll(cur);
            curDiff.removeAll(pre);
            intersect.retainAll(cur);

            int preDiffOutCount = outZone.computeContainingOutCount(preDiff);
            int curDiffOutCount = outZone.computeContainingOutCount(curDiff);
            int intersectOutCount = outZone.computeContainingOutCount(intersect);

            for (int i = 0; i < preDiffOutCount; i++) {
                finalOutCandidates.add(new HashSet<>(preDiff));
            }

            for (int i = 0; i < curDiffOutCount; i++) {
                finalOutCandidates.add(new HashSet<>(curDiff));
            }

            for (int i = 0; i < intersectOutCount; i++) {
                finalOutCandidates.add(new HashSet<>(intersect));
            }
        }
    }

    private void calculateComparedPairs(List<Set<Coordinate>> newOutCandidates, Set<List<Set<Coordinate>>> pairs) {
        for (Set<Coordinate> outCandidate : outCandidates) {
            for (Set<Coordinate> newOutCandidate : newOutCandidates) {
                if (!Collections.disjoint(outCandidate, newOutCandidate)) {
                    pairs.add(List.of(outCandidate, newOutCandidate));
                }
            }
        }
    }

    private void excludeNoIntersection(List<Set<Coordinate>> newOutCandidates, List<Set<Coordinate>> finalOutCandidates) {
        for (Set<Coordinate> outCandidate : outCandidates) {
            boolean disjointWithAll = newOutCandidates.stream()
                    .allMatch(newOutCandidate -> Collections.disjoint(outCandidate, newOutCandidate));
            if (disjointWithAll) {
                finalOutCandidates.add(new HashSet<>(Set.copyOf(outCandidate)));
            }
        }

        for (Set<Coordinate> newOutCandidate : newOutCandidates) {
            boolean disjointWithAll = outCandidates.stream()
                    .allMatch(outCandidate -> Collections.disjoint(newOutCandidate, outCandidate));
            if (disjointWithAll) {
                finalOutCandidates.add(new HashSet<>(Set.copyOf(newOutCandidate)));
            }
        }
    }

    private void filterCandidatesFromOut(Coordinate outCoordinate) {
        if (outCandidates.isEmpty() || outCandidatesDoNotContain(outCoordinate)) {
            outCandidates.add(new HashSet<>());
            return;
        }

        for (Set<Coordinate> outCandidate : outCandidates) {
            if (outCandidate.contains(outCoordinate)) {
                outCandidate.remove(outCoordinate);
                RemoveInOutCandidates(outCoordinate);

                for (Coordinate coordinate : outCandidate) {
                    if (outCandidatesContainCountOf(coordinate) <= 1) {
                        unselectedCandidates.remove(coordinate);
                    }
                }
                outCandidate.clear();
                break;
            }
        }
    }

    private void filterCandidatesFromNoStrike(Coordinate selectedCoordinate) {
        Set<Coordinate> strikeZones = selectedCoordinate.getStrikeZones(size);
        for (Set<Coordinate> outCandidate : outCandidates) {
            outCandidate.removeIf(strikeZones::contains);
        }
        unselectedCandidates.removeIf(strikeZones::contains);
    }

    private void filterCandidatesFromNoBall(Coordinate selectedCoordinate) {
        Set<Coordinate> ballZones = selectedCoordinate.getBallZones(size);
        for (Set<Coordinate> outCandidate : outCandidates) {
            outCandidate.removeIf(ballZones::contains);
        }
        unselectedCandidates.removeIf(ballZones::contains);
    }

    private boolean outCandidatesDoNotContain(Coordinate outCoordinate) {
        return outCandidates.stream()
                .filter(outCandidate -> outCandidate.contains(outCoordinate))
                .toList()
                .isEmpty();
    }

    private int outCandidatesContainCountOf(Coordinate selectedCoordinate) {
        return (int) outCandidates.stream()
                .filter(outCandidate -> outCandidate.contains(selectedCoordinate))
                .count();
    }
}
