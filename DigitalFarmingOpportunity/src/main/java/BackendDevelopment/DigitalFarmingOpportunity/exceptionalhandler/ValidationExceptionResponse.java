package BackendDevelopment.DigitalFarmingOpportunity.exceptionalhandler;

import java.util.Map;

public class ValidationExceptionResponse {
    private String message;
    private Map<String, String> details;

    public ValidationExceptionResponse(String message, Map<String, String> details) {
        this.message = message;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getDetails() {
        return details;
    }

    public void setDetails(Map<String, String> details) {
        this.details = details;
    }
}