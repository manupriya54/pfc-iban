package test.manu.model;

public class ErrorResponse {

    private String errorMessage;
    private Exception exception;

    public ErrorResponse() {
    }

    public ErrorResponse(String errorMessage, Exception exception) {
        this.errorMessage = errorMessage;
        this.exception = exception;
    }

    public ErrorResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Exception getException() {
        return exception;
    }
}
