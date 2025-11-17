package domain;

import domain.vo.Coordinate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class SmartComputer {

    private final int size;
    private final Set<Coordinate> unselectedCandidates;
    private final List<Set<Coordinate>> outCandidates;

    public SmartComputer(int size) {
        this.size = size;
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
        Map<Coordinate, List<Integer>> outCandidatesPriority = new HashMap<>();
        Map<Coordinate, Integer> unselectedCandidatePriority = new HashMap<>();

        for (Set<Coordinate> outCandidate : outCandidates) {
            if (outCandidate != null) {
                for (Coordinate coordinate : outCandidate) {
                    outCandidatesPriority.put(coordinate, new ArrayList<>(List.of(0, 0)));

                    Iterator<Coordinate> neighbors = coordinate.getNeighbors(size);
                    while (neighbors.hasNext()) {
                        Coordinate neighbor = neighbors.next();

                        // 아웃 후보 리스트 요소인지 확인
                        for (Set<Coordinate> candidate : outCandidates) {
                            if (candidate.contains(neighbor)) {
                                List<Integer> list = outCandidatesPriority.get(coordinate);
                                list.set(0, list.get(0) + 1);
                                break;
                            }
                        }

                        // 미선택 리스트 요소인지 확인
                        if (unselectedCandidates.contains(neighbor)) {
                            List<Integer> list = outCandidatesPriority.get(coordinate);
                            list.set(1, list.get(1) + 1);
                        }
                    }
                }
            }
        }

        Coordinate nextCoordinate = getNextCoordinateForOutCandidates(outCandidatesPriority);
        if (nextCoordinate != null) {
            return nextCoordinate;
        }

        for (Coordinate unselectedCandidate : unselectedCandidates) {
            unselectedCandidatePriority.put(unselectedCandidate, 0);

            Iterator<Coordinate> neighbors = unselectedCandidate.getNeighbors(size);
            while (neighbors.hasNext()) {
                Coordinate neighbor = neighbors.next();

                // 미선택 리스트 요소인지 확인
                if (unselectedCandidates.contains(neighbor)) {
                    unselectedCandidatePriority.put(
                            unselectedCandidate,
                            unselectedCandidatePriority.get(unselectedCandidate) + 1
                    );
                }
            }
        }

        return getNextCoordinateForUnselectedCandidates(unselectedCandidatePriority);
    }

    private Coordinate getNextCoordinateForOutCandidates(Map<Coordinate, List<Integer>> priority) {
        Coordinate nextCoordinate = null;
        int maxOutCandidatesCount = 0;
        int maxUnselectedCandidatesCount = 0;
        for (Entry<Coordinate, List<Integer>> coordinateIntegerEntry : priority.entrySet()) {
            Coordinate key = coordinateIntegerEntry.getKey();
            List<Integer> value = coordinateIntegerEntry.getValue();
            if (maxOutCandidatesCount < value.get(0)) {
                maxOutCandidatesCount = value.get(0);
                nextCoordinate = key;
            }

            if (maxUnselectedCandidatesCount < value.get(1)) {
                maxUnselectedCandidatesCount = value.get(1);
                nextCoordinate = key;
            }
        }
        return nextCoordinate;
    }

    private Coordinate getNextCoordinateForUnselectedCandidates(Map<Coordinate, Integer> priority) {
        Coordinate nextCoordinate = null;
        int maxUnselectedCandidatesCount = 0;

        for (Entry<Coordinate, Integer> coordinateIntegerEntry : priority.entrySet()) {
            Coordinate key = coordinateIntegerEntry.getKey();
            Integer value = coordinateIntegerEntry.getValue();
            if (maxUnselectedCandidatesCount < value) {
                maxUnselectedCandidatesCount = value;
                nextCoordinate = key;
            }
        }

        return nextCoordinate;
    }
}
