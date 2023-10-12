package pl.miq3l.EmpikApp.exception;

public class ErrorMessage {
    private final String code;
    private final String message;

    public ErrorMessage(ExceptionCode exceptionCode, Object... params) {
        this.code = exceptionCode.getCode();
        if (exceptionCode.isPattern()) {
            this.message = String.format(exceptionCode.getMessage(), params);
        } else {
            this.message = exceptionCode.getMessage();
        }
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
