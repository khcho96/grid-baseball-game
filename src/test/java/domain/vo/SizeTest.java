package domain.vo;

import static constant.ErrorMessage.NUMBER_FORMAT_ERROR;
import static constant.ErrorMessage.RANGE_ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class SizeTest {

    @ParameterizedTest
    @ValueSource(strings = {"1a", "a"})
    void 숫자가_아닌_형식의_입력값이면_예외를_발생시킨다(String value) {
        assertThatThrownBy(() -> Size.newInstance(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NUMBER_FORMAT_ERROR.getErrorMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "2", "9"})
    void 일정한_범위의_입력값이_아니면_예외를_발생시킨다(String value) {
        assertThatThrownBy(() -> Size.newInstance(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(RANGE_ERROR.getErrorMessage());
    }

    @ParameterizedTest
    @CsvSource(value = {"3,3", "5,5", "8,8"})
    void 사이즈_객체_정상적으로_생성(String value, int expectedSize) {
        // given
        Size size = Size.newInstance(value);

        // when
        int result = size.getSize();

        // then
        assertThat(result).isEqualTo(expectedSize);
    }
}
