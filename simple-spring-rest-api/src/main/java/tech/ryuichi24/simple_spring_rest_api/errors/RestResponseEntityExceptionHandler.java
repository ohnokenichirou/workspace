package tech.ryuichi24.simple_spring_rest_api.errors;

import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {NotFoundException.class, BadRequestException.class})
  protected ResponseEntity<Object> handleHttpException(HttpException ex, WebRequest request) {
    ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ex.getHttpStatus());
    return this.handleExceptionInternal(ex, errorResponse, new HttpHeaders(), ex.getHttpStatus(),
        request);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    BindingResult bindingResult = ex.getBindingResult();
    List<FieldError> fieldErrors = bindingResult.getFieldErrors();
    String fieldError = fieldErrors.get(0).getDefaultMessage();
    ErrorResponse errorResponse = new ErrorResponse(fieldError, HttpStatus.BAD_REQUEST);
    return this.handleExceptionInternal(ex, errorResponse, headers, HttpStatus.BAD_REQUEST,
        request);
  }
}
