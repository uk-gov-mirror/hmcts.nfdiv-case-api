package uk.gov.hmcts.divorce.common.exception;

public class UnAuthorisedServiceException extends RuntimeException {
    private static final long serialVersionUID = -2047737262969335485L;

    public UnAuthorisedServiceException(String message) {
        super(message);
    }
}
