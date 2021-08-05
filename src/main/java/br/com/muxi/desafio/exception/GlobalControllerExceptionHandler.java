package br.com.muxi.desafio.exception;

import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.validation.UnexpectedTypeException;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import br.com.muxi.desafio.data.dto.ApiErrorResponseDTO;
import br.com.muxi.desafio.utils.Constants;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {
	
	@ExceptionHandler(value = { APIException.class } )
	public ResponseEntity<ApiErrorResponseDTO> apiException(APIException ex) {
		return response(ex.getStatusCode(), ex.getMessage());
	}
	
	@ExceptionHandler(value = { HttpMessageNotReadableException.class, InvalidDataAccessApiUsageException.class, IllegalArgumentException.class})
	public ResponseEntity<ApiErrorResponseDTO> paramInvalidException() {
		return response(HttpStatus.BAD_REQUEST, Constants.PARAMETERS_INVALID_ERROR_MSG);
	}
	
	@ExceptionHandler(value = { MethodArgumentTypeMismatchException.class})
	public ResponseEntity<ApiErrorResponseDTO> convertNumberException(MethodArgumentTypeMismatchException ex) {
		String errorMessage = String.format(Constants.PARAMETER_MISMATCH_ERROR_MSG, ex.getName(), ex.getValue(), ex.getRequiredType());
		return response(HttpStatus.BAD_REQUEST, errorMessage);
	}
	
	@ExceptionHandler(value = { MissingServletRequestParameterException.class })
	public ResponseEntity<ApiErrorResponseDTO> parameterRequiredIsNotPresent(MissingServletRequestParameterException ex) {
		String errorMessage = String.format(Constants.PARAMETER_ISNOTPRESENT_ERROR_MSG, ex.getParameterName(), "Informe um valor");
		return response(HttpStatus.BAD_REQUEST, errorMessage);
	}
	
	@ExceptionHandler(value = { MethodArgumentNotValidException.class})
	public ResponseEntity<ApiErrorResponseDTO> argumentNotValidException(MethodArgumentNotValidException ex) {
		String errorMessage = String.format(Constants.PARAMETER_ISNOTPRESENT_ERROR_MSG, ex.getBindingResult().getFieldError().getField(), ex.getBindingResult().getFieldError().getDefaultMessage());
		return response(HttpStatus.BAD_REQUEST, errorMessage);
	}
	
	@ExceptionHandler(value = { UnexpectedTypeException.class })
	public ResponseEntity<ApiErrorResponseDTO> parameterValidation(UnexpectedTypeException ex) {
		return response(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
	}
	
	@ExceptionHandler(value = { ConstraintViolationException.class })
	public ResponseEntity<ApiErrorResponseDTO> parameterValidation(ConstraintViolationException ex) {
		Path propertyPath = ex.getConstraintViolations().iterator().next().getPropertyPath();
		String message = ex.getConstraintViolations().iterator().next().getMessage();
		return response(HttpStatus.BAD_REQUEST, String.format(Constants.PARAMETER_ISNOTPRESENT_ERROR_MSG, propertyPath, message));
	}
	
	@ExceptionHandler(value = { HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<ApiErrorResponseDTO> notSupportedException(HttpRequestMethodNotSupportedException ex) {
		return response(HttpStatus.FORBIDDEN, ex.getMessage());
    }
	
	@ExceptionHandler(value = { HttpMediaTypeNotSupportedException.class })
    public ResponseEntity<ApiErrorResponseDTO> notSupportedException(HttpMediaTypeNotSupportedException ex) {
		return response(HttpStatus.METHOD_NOT_ALLOWED, ex.getMessage());
    }
	
	@ExceptionHandler(value = { Exception.class })
    public ResponseEntity<ApiErrorResponseDTO> unknownException() {
        return response(HttpStatus.INTERNAL_SERVER_ERROR, Constants.INTERNAL_SERVER_ERROR_MESSAGE);
    }

	private ResponseEntity<ApiErrorResponseDTO> response(HttpStatus status, String message) {
		String statusCode = String.format(Constants.STATUS_CODE_FORMAT, status.value(), status.name());
		ApiErrorResponseDTO response = new ApiErrorResponseDTO(statusCode, message);
		return new ResponseEntity<>(response, status);
	}
	
	

}
