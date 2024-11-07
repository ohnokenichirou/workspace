package tech.ryuichi24.simple_spring_rest_api.errors;

import org.springframework.http.HttpStatus;

public class BadRequestException extends HttpException {

  public BadRequestException() {}

  public BadRequestException(String message) {
    super(message, HttpStatus.BAD_REQUEST);
  }

  public BadRequestException(Throwable cause) {
    super(cause, HttpStatus.BAD_REQUEST);
  }

  public BadRequestException(String message, Throwable cause) {
    super(message, cause, HttpStatus.BAD_REQUEST);
  }

  public BadRequestException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace, HttpStatus.BAD_REQUEST);
  }

}
