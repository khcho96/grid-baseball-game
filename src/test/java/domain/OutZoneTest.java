package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class OutZoneTest {

    private OutZone outZone;

    @BeforeEach
    void setUp() {
        outZone = OutZone.of(List.of(
                List.of(0, 1), List.of(2, 1), List.of(2, 2)
        ));
    }

    @ParameterizedTest
    @CsvSource(value = {"0,0,false", "0,1,true", "1,1,false", "2,1,true", "2,2,true", "2,3,false"})
    void 아웃_판단_테스트(int x, int y, boolean expectedResult) {
        // given
        GridButton gridButton = new GridButton(x, y);

        // when
        boolean result = outZone.contains(gridButton);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }
}
