package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.vo.Size;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GridButtonsTest {

    private OutZone outZone;
    private GridButtons gridButtons;

    @BeforeEach
    void setUp() {
        outZone = OutZone.of(List.of(
                List.of(0, 1), List.of(2, 1), List.of(2, 2)
        ));
        Size size = Size.newInstance("5");
        gridButtons = GridButtons.from(size);
    }

    @ParameterizedTest
    @CsvSource(value = {"0,0,1S,0B", "1,0,0S,2B", "1,1,2S,1B", "1,2,1S,2B", "1,3,0S,1B", "3,2,1S,1B", "3,3,0S,1B", "3,4,0S,0B"})
    void 스트라이크_또는_볼_개수_반환_테스트(int x, int y, String expectStrikeCount, String expectedBallCount) {
        // when
        String result = gridButtons.computeEventResult(x, y, outZone);

        // then
        assertThat(result).contains(expectStrikeCount).contains(expectedBallCount);
    }

    @ParameterizedTest
    @CsvSource(value = {"0,1", "2,1", "2,2"})
    void 아웃_판단_테스트(int x, int y) {
        // when
        String result = gridButtons.computeEventResult(x, y, outZone);

        // then
        assertThat(result).isEqualTo("Out!⚾");
    }
}
