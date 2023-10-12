package pl.miq3l.EmpikApp.exception;

public enum ExceptionCode {
    GITHUB_CONNECTION("GH-001", "Github problem, msg: '%s'", true);

    private final String code;
    private final String message;
    private final boolean pattern;

    ExceptionCode(String code, String message, boolean pattern) {
        this.code = code;
        this.message = message;
        this.pattern = pattern;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isPattern() {
        return pattern;
    }
}
