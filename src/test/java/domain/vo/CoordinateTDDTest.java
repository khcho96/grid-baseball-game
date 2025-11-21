package domain.vo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class CoordinateTDDTest {

    @ParameterizedTest
    @CsvSource(value = {"1,2,1,2,true", "1,2,3,4,false"})
    void 좌표_비교_테스트(int x1, int y1, int x2, int y2, boolean expectedResult) {
        // given
        CoordinateTDD coordinate1 = new CoordinateTDD(x1, y1);
        CoordinateTDD coordinate2 = new CoordinateTDD(x2, y2);

        // when
        boolean result = coordinate1.equals(coordinate2);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void 좌표_집합_중복_판단_테스트() {
        // given
        Set<CoordinateTDD> testSet = new HashSet<>();
        CoordinateTDD coordinate1 = new CoordinateTDD(1, 1);
        CoordinateTDD coordinate2 = new CoordinateTDD(1, 1);

        // when
        testSet.add(coordinate1);
        testSet.add(coordinate2);
        int size = testSet.size();

        // then
        assertThat(size).isEqualTo(1);
    }

    @ParameterizedTest
    @MethodSource("strikeCoordinatesProvider")
    void 스트라이크존_좌표_반환_테스트(CoordinateTDD coordinate, Set<CoordinateTDD> expectedStrikeZones) {
        // when
        Set<CoordinateTDD> strikeZones = coordinate.getStrikeZones(5);

        // then
        assertThat(strikeZones).containsExactlyInAnyOrderElementsOf(expectedStrikeZones);
    }

    static Stream<Arguments> strikeCoordinatesProvider() {
        return Stream.of(
                Arguments.of(new CoordinateTDD(0, 0),
                        Set.of(new CoordinateTDD(0, 1), new CoordinateTDD(1, 0))),
                Arguments.of(new CoordinateTDD(1, 0),
                        Set.of(new CoordinateTDD(0, 0), new CoordinateTDD(1, 1),
                                new CoordinateTDD(2, 0))),
                Arguments.of(new CoordinateTDD(0, 1),
                        Set.of(new CoordinateTDD(0, 0), new CoordinateTDD(1, 1),
                                new CoordinateTDD(0, 2))),
                Arguments.of(new CoordinateTDD(1, 1),
                        Set.of(new CoordinateTDD(0, 1), new CoordinateTDD(2, 1),
                                new CoordinateTDD(1, 0), new CoordinateTDD(1, 2)))
        );
    }

    @ParameterizedTest
    @MethodSource("ballCoordinatesProvider")
    void 볼존_좌표_반환_테스트(CoordinateTDD coordinate, Set<CoordinateTDD> expectedBallZones) {
        // when
        Set<CoordinateTDD> ballZones = coordinate.getBallZones(5);

        // then
        assertThat(ballZones).containsExactlyInAnyOrderElementsOf(expectedBallZones);
    }

    static Stream<Arguments> ballCoordinatesProvider() {
        return Stream.of(
                Arguments.of(new CoordinateTDD(0, 0),
                        Set.of(new CoordinateTDD(1, 1))),
                Arguments.of(new CoordinateTDD(1, 0),
                        Set.of(new CoordinateTDD(0, 1), new CoordinateTDD(2, 1))),
                Arguments.of(new CoordinateTDD(0, 1),
                        Set.of(new CoordinateTDD(1, 0), new CoordinateTDD(1, 2))),
                Arguments.of(new CoordinateTDD(1, 1),
                        Set.of(new CoordinateTDD(0, 0), new CoordinateTDD(0, 2),
                                new CoordinateTDD(2, 0), new CoordinateTDD(2, 2)))
        );
    }

    @ParameterizedTest
    @MethodSource("neighborsProvider")
    void 모든_인접_좌표_반환_테스트(CoordinateTDD coordinate, Set<CoordinateTDD> expectedNeighbors) {
        // when
        Set<CoordinateTDD> neighbors = coordinate.getNeighbors(5);

        // then
        assertThat(neighbors).containsExactlyInAnyOrderElementsOf(expectedNeighbors);
    }

    static Stream<Arguments> neighborsProvider() {
        return Stream.of(
                Arguments.of(new CoordinateTDD(0, 0),
                        Set.of(new CoordinateTDD(0, 1), new CoordinateTDD(1, 0), new CoordinateTDD(1, 1))),
                Arguments.of(new CoordinateTDD(1, 0),
                        Set.of(new CoordinateTDD(0, 1), new CoordinateTDD(2, 1),
                                new CoordinateTDD(0, 0), new CoordinateTDD(1, 1),
                                new CoordinateTDD(2, 0))),
                Arguments.of(new CoordinateTDD(0, 1),
                        Set.of(new CoordinateTDD(1, 0), new CoordinateTDD(1, 2),
                                new CoordinateTDD(0, 0), new CoordinateTDD(1, 1),
                                new CoordinateTDD(0, 2))),
                Arguments.of(new CoordinateTDD(1, 1),
                        Set.of(new CoordinateTDD(0, 0), new CoordinateTDD(0, 2),
                                new CoordinateTDD(2, 0), new CoordinateTDD(2, 2),
                                new CoordinateTDD(0, 1), new CoordinateTDD(2, 1),
                                new CoordinateTDD(1, 0), new CoordinateTDD(1, 2)))
        );
    }
}
