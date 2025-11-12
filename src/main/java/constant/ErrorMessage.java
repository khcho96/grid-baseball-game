package constant;

import static constant.Constant.MAX_SIZE;
import static constant.Constant.MIN_SIZE;

public enum ErrorMessage {
    NUMBER_FORMAT_ERROR("[ERROR] 숫자를 입력해주세요."),
    RANGE_ERROR(String.format("[ERROR] %d 이상 %d 이하의 숫자를 입력해주세요.", MIN_SIZE, MAX_SIZE));
    private final String errorMessage;

    ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
