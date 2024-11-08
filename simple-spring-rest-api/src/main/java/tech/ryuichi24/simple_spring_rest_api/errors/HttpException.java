package tech.ryuichi24.simple_spring_rest_api.errors;

import org.springframework.http.HttpStatus;
import lombok.Getter;

@Getter
public class HttpException extends RuntimeException {
  private HttpStatus httpStatus;

  public HttpException() {}

  public HttpException(HttpStatus httpStatus) {
    this.httpStatus = httpStatus;
  }

  public HttpException(String message, HttpStatus httpStatus) {
    super(message);
    this.httpStatus = httpStatus;
  }

  public HttpException(Throwable cause, HttpStatus httpStatus) {
    super(cause);
    this.httpStatus = httpStatus;
  }

  public HttpException(String message, Throwable cause, HttpStatus httpStatus) {
    super(message, cause);
    this.httpStatus = httpStatus;
  }

  public HttpException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace, HttpStatus httpStatus) {
    super(message, cause, enableSuppression, writableStackTrace);
    this.httpStatus = httpStatus;
  }
}
