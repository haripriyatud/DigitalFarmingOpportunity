package BackendDevelopment.DigitalFarmingOpportunity.exceptionalhandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<Object> handleResourceNotFoundException(Exception ex, WebRequest request) {

		ResourceNotExceptionResponse response = new ResourceNotExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		ValidationExceptionResponse errorResponse = new ValidationExceptionResponse("Validation failed", errors);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
}
