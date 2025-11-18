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

    public SmartComputer(int size) {
        this.size = size;
        outConfirmed = new HashSet<>();
        unselectedCandidates = generateAllCandidates();
        outCandidates = new ArrayList<>();
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
            if (!outCandidate.isEmpty()) {
                for (Coordinate coordinate : outCandidate) {
                    priority.put(coordinate, 0);

                    Iterator<Coordinate> neighbors = coordinate.getNeighbors(size);
                    while (neighbors.hasNext()) {
                        Coordinate neighbor = neighbors.next();

                        // 미선택 리스트 요소인지 확인
                        if (unselectedCandidates.contains(neighbor)) {
                            priority.put(
                                    coordinate,
                                    priority.get(coordinate) + 1
                            );
                        }
                    }
                }
            }
        }

        if (!priority.isEmpty()) {
            return getNextCoordinateForUnselectedCandidates(priority);
        }

        for (Coordinate unselectedCandidate : unselectedCandidates) {
            priority.put(unselectedCandidate, 0);

            Iterator<Coordinate> neighbors = unselectedCandidate.getNeighbors(size);
            while (neighbors.hasNext()) {
                Coordinate neighbor = neighbors.next();

                // 미선택 리스트 요소인지 확인
                if (unselectedCandidates.contains(neighbor)) {
                    priority.put(
                            unselectedCandidate,
                            priority.get(unselectedCandidate) + 1
                    );
                }
            }
        }

        return getNextCoordinateForUnselectedCandidates(priority);
    }

    private Coordinate getNextCoordinateForUnselectedCandidates(Map<Coordinate, Integer> priority) {
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

        for (Set<Coordinate> outCandidate : outCandidates) {
            outCandidate.remove(selectedCoordinate);
        }

        // 아웃이 아닌 경우
        // HTML 태그를 제거하고 "1S 2B"와 같은 형태로 파싱
        String stripped = result.replaceAll("<[^>]*>", "").strip();
        String[] parts = stripped.split(" ");
        int strike = Integer.parseInt(parts[0].replace("S", "").strip());
        int ball = Integer.parseInt(parts[1].replace("B", "").strip());
        if (strike == 0) {
            filterCandidatesFromNoStrike(selectedCoordinate);
        } else {
            updateOutCandidatesFromStrike(selectedCoordinate, strike);
        }
        if (ball == 0) {
            filterCandidatesFromNoBall(selectedCoordinate);
        } else {
            updateOutCandidatesFromBall(selectedCoordinate, ball);
        }
    }

    private void updateOutCandidatesFromStrike(Coordinate selectedCoordinate, int strike) {
        List<Set<Coordinate>> newOutCandidates = new ArrayList<>();
        for (int i = 0; i < strike; i++) {
            newOutCandidates.add(new HashSet<>());
        }

        Set<Coordinate> strikeZones = selectedCoordinate.getStrikeZones(size);
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

        mergeFinalOutCandidates(newOutCandidates);
    }

    private void mergeFinalOutCandidates(List<Set<Coordinate>> newOutCandidates) {
        List<Set<Coordinate>> finalOutCandidates = new ArrayList<>();
        Set<List<Set<Coordinate>>> pairs = new HashSet<>();

        for (Set<Coordinate> outCandidate : outCandidates) {
            if (outCandidate.isEmpty()) continue;

            boolean disjointWithAll = newOutCandidates.stream()
                    .allMatch(newOutCandidate -> Collections.disjoint(outCandidate,
                            newOutCandidate)); // 모든 newOutCandidate와 비겹치는지 확인
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
                    .allMatch(outCandidate -> Collections.disjoint(newOutCandidate,
                            outCandidate)); // 모든 outCandidate와 비겹치는지 확인
            if (disjointWithAll) {
                finalOutCandidates.add(new HashSet<>(Set.copyOf(newOutCandidate)));
            }

            for (Set<Coordinate> outCandidate : outCandidates) {
                if (!Collections.disjoint(newOutCandidate, outCandidate)) {
                    pairs.add(List.of(outCandidate, newOutCandidate));
                }
            }
        }

//        for (List<Set<Coordinate>> pair : pairs) {
//            Set<Coordinate> pre = pair.get(0);
//            Set<Coordinate> next = pair.get(1);
//
//
//        }

//        outCandidates = new ArrayList<>(finalOutCandidates);
    }

    private void updateOutCandidatesFromBall(Coordinate selectedCoordinate, int ball) {
        List<Set<Coordinate>> newOutCandidates = new ArrayList<>();
        for (int i = 0; i < ball; i++) {
            newOutCandidates.add(new HashSet<>());
        }

        Set<Coordinate> ballZones = selectedCoordinate.getBallZones(size);
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
    }

    private void filterCandidatesFromOut(Coordinate selectedCoordinate) {
        if (outCandidates.isEmpty() || !outCandidatesContain(selectedCoordinate)) {
            outCandidates.add(new HashSet<>());
            return;
        }

        for (Set<Coordinate> outCandidate : outCandidates) {
            outCandidate.remove(selectedCoordinate);
            outCandidate.removeIf(coordinate -> outCandidatesContainCountOf(coordinate) > 1);
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

    private boolean outCandidatesContain(Coordinate selectedCoordinate) {
        return !outCandidates.stream()
                .filter(outCandidate -> outCandidate.contains(selectedCoordinate))
                .toList()
                .isEmpty();
    }

    private int outCandidatesContainCountOf(Coordinate selectedCoordinate) {
        return outCandidates.stream()
                .filter(outCandidate -> outCandidate.contains(selectedCoordinate))
                .toList()
                .size();
    }
}
