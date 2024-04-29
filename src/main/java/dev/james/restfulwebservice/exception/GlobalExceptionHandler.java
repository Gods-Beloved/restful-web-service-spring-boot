package dev.james.restfulwebservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleServerError(UserNotFoundException ex, WebRequest request){

        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setDetails(request.getDescription(false));
        errorDetails.setTimestamp(LocalDateTime.now());
        errorDetails.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);

    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleUserNotFound(UserNotFoundException ex, WebRequest request){

        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setDetails(request.getDescription(false));
        errorDetails.setTimestamp(LocalDateTime.now());
        errorDetails.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);

    }


}
