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

    // 다음 클릭할 좌표 반환
    public Coordinate nextGuess() {
        Map<Coordinate, Integer> priority = new HashMap<>();

        for (Set<Coordinate> outCandidate : outCandidates) {
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

        if (!priority.isEmpty()) {
            return getNextCoordinateForCandidates(priority);
        }

        for (Coordinate unselectedCandidate : unselectedCandidates) {
            if (!priority.containsKey(unselectedCandidate)) {
                priority.put(unselectedCandidate, 0);

                Iterator<Coordinate> neighbors = unselectedCandidate.getNeighbors(size);
                while (neighbors.hasNext()) {
                    Coordinate neighbor = neighbors.next();

                    if (unselectedCandidates.contains(neighbor)) {
                        priority.put(unselectedCandidate, priority.get(unselectedCandidate) + 1);
                    }
                }
            }
        }

        return getNextCoordinateForCandidates(priority);
    }

    private Coordinate getNextCoordinateForCandidates(Map<Coordinate, Integer> priority) {
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

    // 좌표 선택 결과를 받아서 처리하는 로직
    public void recordResult(Coordinate selectedCoordinate, String result) {
        // 해당 좌표 삭제
        unselectedCandidates.remove(selectedCoordinate);

        // 아웃인 경우
        if (result.startsWith("Out")) {
            outConfirmed.add(selectedCoordinate);
            filterCandidatesFromOut(selectedCoordinate);
            return;
        }

        // 아웃이 아닌 경우
        for (Set<Coordinate> outCandidate : outCandidates) {
            outCandidate.remove(selectedCoordinate);
        }

        List<Set<Coordinate>> newOutCandidates = new ArrayList<>();

        String stripped = result.replaceAll("<[^>]*>", "").strip();
        String[] parts = stripped.split(" ");
        int strike = Integer.parseInt(parts[0].replace("S", "").strip());
        int ball = Integer.parseInt(parts[1].replace("B", "").strip());

        if (strike == 0) {
            filterCandidatesFromNoStrike(selectedCoordinate);
        } else {
            List<Set<Coordinate>> newCandidates = updateOutCandidatesFromStrike(selectedCoordinate, strike);
            newOutCandidates.addAll(newCandidates);
        }

        if (ball == 0) {
            filterCandidatesFromNoBall(selectedCoordinate);
        } else {
            List<Set<Coordinate>> newCandidates = updateOutCandidatesFromBall(selectedCoordinate, ball);
            newOutCandidates.addAll(newCandidates);
        }

        if (!newOutCandidates.isEmpty()) {
            filterIfOutCandidateSizeThree(newOutCandidates);
            mergeFinalOutCandidates(newOutCandidates);
        }
    }

    private void filterIfOutCandidateSizeThree(List<Set<Coordinate>> newOutCandidates) {
        if (outCandidates.size() == 3) {
            for (Set<Coordinate> newOutCandidate : newOutCandidates) {
                newOutCandidate.removeIf(coordinate -> !outCandidatesContain(coordinate));
            }
            unselectedCandidates.removeIf(coordinate -> !outCandidatesContain(coordinate));
        }
    }

    private List<Set<Coordinate>> updateOutCandidatesFromStrike(Coordinate strikeCoordinate, int strike) {
        List<Set<Coordinate>> newOutCandidates = new ArrayList<>();

        for (int i = 0; i < strike; i++) {
            newOutCandidates.add(new HashSet<>());
        }

        Set<Coordinate> strikeZones = strikeCoordinate.getStrikeZones(size);
        for (Coordinate strikeZone : strikeZones) {
            if (outConfirmed.contains(strikeZone)) {
                newOutCandidates.removeFirst();
            }

            if (unselectedCandidates.contains(strikeZone)) {
                for (Set<Coordinate> newOutCandidate : newOutCandidates) {
                    newOutCandidate.add(strikeZone);
                }
            }
        }

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

        Set<Coordinate> ballZones = ballCoordinate.getBallZones(size);
        for (Coordinate ballZone : ballZones) {
            if (outConfirmed.contains(ballZone)) {
                newOutCandidates.removeFirst();
            }

            if (unselectedCandidates.contains(ballZone)) {
                for (Set<Coordinate> newOutCandidate : newOutCandidates) {
                    newOutCandidate.add(ballZone);
                }
            }
        }

        if (newOutCandidates.isEmpty()) {
            for (Set<Coordinate> outCandidate : outCandidates) {
                outCandidate.removeIf(ballZones::contains);
            }
            unselectedCandidates.removeIf(ballZones::contains);
        }

        return newOutCandidates;
    }

    private void mergeFinalOutCandidates(List<Set<Coordinate>> newOutCandidates) {
        List<Set<Coordinate>> finalOutCandidates = new ArrayList<>();
        Set<List<Set<Coordinate>>> pairs = new HashSet<>();

        if (outCandidates.isEmpty()) {
            outCandidates = new ArrayList<>(newOutCandidates);
            return;
        }

        for (Set<Coordinate> outCandidate : outCandidates) {
            if (outCandidate.isEmpty()) {
                continue;
            }

            boolean disjointWithAll = newOutCandidates.stream()
                    .allMatch(newOutCandidate -> Collections.disjoint(outCandidate, newOutCandidate));
            if (disjointWithAll) {
                finalOutCandidates.add(new HashSet<>(Set.copyOf(outCandidate)));
                continue;
            }

            for (Set<Coordinate> newOutCandidate : newOutCandidates) {
                if (!Collections.disjoint(outCandidate, newOutCandidate)) {
                    pairs.add(List.of(outCandidate, newOutCandidate));
                }
            }
        }
        for (Set<Coordinate> newOutCandidate : newOutCandidates) {
            boolean disjointWithAll = outCandidates.stream()
                    .allMatch(outCandidate -> Collections.disjoint(newOutCandidate, outCandidate));
            if (disjointWithAll) {
                finalOutCandidates.add(new HashSet<>(Set.copyOf(newOutCandidate)));
            }

            for (Set<Coordinate> outCandidate : outCandidates) {
                if (!Collections.disjoint(newOutCandidate, outCandidate)) {
                    pairs.add(List.of(outCandidate, newOutCandidate));
                }
            }
        }

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

        outCandidates = finalOutCandidates;
    }

    private void filterCandidatesFromOut(Coordinate outCoordinate) {
        if (outCandidates.isEmpty() || !outCandidatesContain(outCoordinate)) {
            outCandidates.add(new HashSet<>());
            return;
        }

        for (Set<Coordinate> outCandidate : outCandidates) {
            if (outCandidate.contains(outCoordinate)) {
                outCandidate.remove(outCoordinate);
                for (Set<Coordinate> candidate : outCandidates) {
                    candidate.remove(outCoordinate);
                }

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

    private boolean outCandidatesContain(Coordinate outCoordinate) {
        return !outCandidates.stream()
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
