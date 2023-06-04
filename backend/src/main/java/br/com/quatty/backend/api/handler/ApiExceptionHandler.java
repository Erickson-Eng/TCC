package br.com.quatty.backend.api.handler;

import br.com.quatty.backend.business.service.exception.MembershipException;
import br.com.quatty.backend.business.service.exception.DatabaseViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        List<String> errors = new ArrayList<>();
        for (FieldError error: ex.getBindingResult().getFieldErrors()){
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error: ex.getBindingResult().getGlobalErrors()){
            errors.add(error.getObjectName() + ": "+ error.getDefaultMessage());
        }
        ApiError apiError =  ApiError.builder()
                .httpStatus(badRequest)
                .message(ex.getLocalizedMessage())
                .timeStamp(ZonedDateTime.now(ZoneId.of("Z")))
                .errors(errors)
                .build();
        return handleExceptionInternal(ex, apiError, headers, badRequest, request);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    protected ResponseEntity<Object> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiError apiError = ApiError.builder()
                .httpStatus(badRequest)
                .message(ex.getMessage())
                .timeStamp(ZonedDateTime.now(ZoneId.of("Z")))
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiError apiError = ApiError.builder()
                .httpStatus(badRequest)
                .message(e.getMessage())
                .timeStamp(ZonedDateTime.now(ZoneId.of("Z")))
                .build();

        return new ResponseEntity<>(apiError, badRequest);
    }

    @ExceptionHandler(value = {DatabaseViolationException.class})
    protected ResponseEntity<ApiError> handleDatabaseViolationException(DatabaseViolationException e){
        HttpStatus badRequest = HttpStatus.CONFLICT;
        ApiError apiError = ApiError.builder()
                .httpStatus(badRequest)
                .message(e.getMessage())
                .timeStamp(ZonedDateTime.now(ZoneId.of("Z")))
                .build();

        return new ResponseEntity<>(apiError, badRequest);
    }

    @ExceptionHandler(value = {MembershipException.class})
    protected ResponseEntity<ApiError> handleCommunityException(MembershipException e){
        HttpStatus badRequest = HttpStatus.FORBIDDEN;
        ApiError apiError = ApiError.builder()
                .httpStatus(badRequest)
                .message(e.getMessage())
                .timeStamp(ZonedDateTime.now(ZoneId.of("Z")))
                .build();

        return new ResponseEntity<>(apiError, badRequest);
    }

    @ExceptionHandler(value = {HttpClientErrorException.class})
    protected ResponseEntity<ApiError> handleHttpClientErrorException(HttpClientErrorException e){
        HttpStatus badRequest = HttpStatus.FORBIDDEN;
        ApiError apiError = ApiError.builder()
                .httpStatus(HttpStatus.valueOf(e.getStatusCode().value()))
                .message(e.getMessage())
                .timeStamp(ZonedDateTime.now(ZoneId.of("Z")))
                .build();

        return new ResponseEntity<>(apiError, badRequest);
    }
}
