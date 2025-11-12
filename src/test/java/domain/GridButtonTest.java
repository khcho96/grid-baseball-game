package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GridButtonTest {

    @ParameterizedTest
    @CsvSource(value = {"1,2,true", "1,1,false"})
    void 선택한_칸이_아웃인지_판단한다(int x, int y, boolean expectedResult) {
        // given
        OutZone outZone = OutZone.of(List.of(List.of(1, 2), List.of(3, 3), List.of(3, 4)));
        GridButton gridButton = new GridButton(x, y);

        // when
        boolean result = gridButton.isOut(outZone);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource(value = {"1,1,1,1,true", "1,1,1,2,false"})
    void 버튼이_서로_같은지_다른지_판단한다(int x1, int y1, int x2, int y2, boolean expectedResult) {
        // given
        GridButton gridButton1 = new GridButton(x1, y1);
        GridButton gridButton2 = new GridButton(x2, y2);

        // when
        boolean result = gridButton1.equals(gridButton2);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }
}