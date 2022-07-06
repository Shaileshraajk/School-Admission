package com.school.admisssion.exceptions;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler{

    Logger logger = LoggerFactory.getLogger(CustomGlobalExceptionHandler.class);

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(Exception ex){
        
        ErrorResponse err = new ErrorResponse();
        err.setTimestamp(LocalDateTime.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setMessage(ex.getMessage());
        logger.error("Error", ex);

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity<ErrorResponse> handleInvalidUserException(Exception ex){
        
        ErrorResponse err = new ErrorResponse();
        err.setTimestamp(LocalDateTime.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setMessage(ex.getMessage());
        logger.error("Error", ex);

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InstituteNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleInstiuteNotFoundException(Exception ex){
        ErrorResponse err = new ErrorResponse();
        err.setTimestamp(LocalDateTime.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setMessage(ex.getMessage());
        logger.error("Error", ex);

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InstituteAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleInstituteException(Exception ex){
        ErrorResponse err=new ErrorResponse();
        err.setTimestamp(LocalDateTime.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setMessage(ex.getMessage());
        logger.error("Error", ex);

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AdmittedAlreadyException.class)
    public ResponseEntity<ErrorResponse> handleAdmittedAlready(Exception ex){
        ErrorResponse err=new ErrorResponse();
        err.setTimestamp(LocalDateTime.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setMessage(ex.getMessage());
        logger.error("Error", ex);

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidAdminLogin.class)
    public ResponseEntity<ErrorResponse> handleInvalidAdminLogin(Exception ex){
        ErrorResponse err=new ErrorResponse();
        err.setTimestamp(LocalDateTime.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setMessage(ex.getMessage());
        logger.error("Error", ex);

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidOTPException.class)
    public ResponseEntity<ErrorResponse> handleInvalidOTP(Exception ex){
        ErrorResponse err=new ErrorResponse();
        err.setTimestamp(LocalDateTime.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setMessage(ex.getMessage());
        logger.error("Error", ex);

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    
}
