package util;

import static constant.ErrorMessage.NUMBER_FORMAT_ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class NumberConverterTest {

    @ParameterizedTest
    @CsvSource(value = {"-1,-1", "0,0", "5,5"})
    void 문자열_정수_변환_테스트(String value, int expectedResult) {
        // given, when
        int result = NumberConverter.convertStringToNumber(value);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1a", "a", "\n"})
    void 숫자_형식이_아니면_예외를_발생시킨다(String value) {
        assertThatThrownBy(() -> NumberConverter.convertStringToNumber(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NUMBER_FORMAT_ERROR.getErrorMessage());
    }
}