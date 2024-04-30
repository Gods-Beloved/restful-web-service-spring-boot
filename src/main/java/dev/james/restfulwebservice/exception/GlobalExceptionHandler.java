package dev.james.restfulwebservice.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorDetails> handleServerError(Exception ex, WebRequest request){
//
//        ErrorDetails errorDetails = new ErrorDetails();
//        errorDetails.setDetails(request.getDescription(false));
//        errorDetails.setTimestamp(LocalDateTime.now());
//        errorDetails.setMessage(ex.getMessage());
//
//        return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
//
//    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setDetails(request.getDescription(false));
        errorDetails.setTimestamp(LocalDateTime.now());
        errorDetails.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setDetails(request.getDescription(false));
        errorDetails.setTimestamp(LocalDateTime.now());
        errorDetails.setMessage(Objects.requireNonNull(ex.getFieldError()).getDefaultMessage());

        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }

    //
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request){
//
//        ErrorDetails errorDetails = new ErrorDetails();
//        errorDetails.setDetails(request.getDescription(false));
//        errorDetails.setTimestamp(LocalDateTime.now());
//        errorDetails.setMessage(ex.getMessage());
//
//        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
//
//    }





    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleUserNotFound(UserNotFoundException ex, WebRequest request){

        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setDetails(request.getDescription(false));
        errorDetails.setTimestamp(LocalDateTime.now());
        errorDetails.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);

    }


}
