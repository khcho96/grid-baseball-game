package util;

import static constant.ErrorMessage.NUMBER_FORMAT_ERROR;

public final class NumberConverter {

    private NumberConverter() {}

    public static int convertStringToNumber(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new RuntimeException(NUMBER_FORMAT_ERROR.getErrorMessage());
        }
    }
}
