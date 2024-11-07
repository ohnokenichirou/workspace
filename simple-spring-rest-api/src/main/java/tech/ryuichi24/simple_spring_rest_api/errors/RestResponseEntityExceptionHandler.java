package tech.ryuichi24.simple_spring_rest_api.errors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
}
