package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.vo.Size;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class GridButtonsTest {

    private final OutZone outZone = OutZone.of(List.of(
            List.of(0, 1), List.of(2, 1), List.of(2, 2)
    ));

    @ParameterizedTest
    @CsvSource(value = {"0,0,1S,0B", "1,0,0S,2B", "1,1,2S,1B", "1,2,1S,2B", "1,3,0S,1B", "3,2,1S,1B", "3,3,0S,1B", "3,4,0S,0B"})
    void 스트라이크_개수_반환_테스트(int x, int y, String expectStrikeCount, String expectedBallCount) {
        // given
        Size size = Size.newInstance("5");
        GridButtons gridButtons = GridButtons.from(size);

        // when
        String result = gridButtons.computeEventResult(x, y, outZone);

        // then
        assertThat(result).contains(expectStrikeCount).contains(expectedBallCount);
    }
}
