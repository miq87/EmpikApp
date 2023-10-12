package pl.miq3l.EmpikApp.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static pl.miq3l.EmpikApp.exception.ExceptionCode.GITHUB_CONNECTION;

@ControllerAdvice
public class CustomExceptionHandler
    extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {DomainException.class})
    protected ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, error(GITHUB_CONNECTION, ex.getMessage()),
            new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    private ErrorMessage error(ExceptionCode exceptionCode, Object... params) {
        return new ErrorMessage(exceptionCode, params);
    }
}
