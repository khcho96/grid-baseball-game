package main.domain;

import static main.constant.Constant.MAX_SIZE;
import static main.constant.Constant.MIN_SIZE;
import static main.constant.ErrorMessage.NUMBER_FORMAT_ERROR;
import static main.constant.ErrorMessage.RANGE_ERROR;

import main.util.NumberConverter;

public class Size {

    private final int size;

    public Size(String sizeInput) {
        validateNumber(sizeInput);
        int size = NumberConverter.convertStringToNumber(sizeInput);
        validateRange(size);
        this.size = size;
    }

    public static Size newInstance(String sizeInput) {
        return new Size(sizeInput);
    }

    private void validateNumber(String size) {
        if (!size.matches("\\d+")) {
            throw new IllegalArgumentException(NUMBER_FORMAT_ERROR.getErrorMessage());
        }
    }

    private void validateRange(int size) {
        if (MIN_SIZE > size || size > MAX_SIZE) {
            throw new IllegalArgumentException(RANGE_ERROR.getErrorMessage());
        }
    }

    public int getSize() {
        return size;
    }
}
