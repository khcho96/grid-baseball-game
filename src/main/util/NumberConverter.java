package main.util;

import static main.constant.ErrorMessage.NUMBER_FORMAT_ERROR;

public class NumberConverter {

    public static int convertStringToNumber(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new RuntimeException(NUMBER_FORMAT_ERROR.getErrorMessage());
        }
    }
}
