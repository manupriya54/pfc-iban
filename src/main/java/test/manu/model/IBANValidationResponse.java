package test.manu.model;

public class IBANValidationResponse {

    private ErrorResponse errorResponse;
    private final boolean valid;

    public IBANValidationResponse() {
        this.valid = true;
    }

    public IBANValidationResponse(ErrorResponse errorResponse) {
        this.valid = false;
        this.errorResponse = errorResponse;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public boolean isValid() {
        return valid;
    }
}
