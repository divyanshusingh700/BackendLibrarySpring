package org.truecodes.DigitalLibrary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.truecodes.DigitalLibrary.service.TxnService;

@ControllerAdvice
public class ControllerExceptionAdvice {
    @ExceptionHandler(value = TxnException.class)
    public ResponseEntity<Object> handle(TxnException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = UserException.class)
    public ResponseEntity<Object> handle(UserException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handle(MethodArgumentNotValidException e){
        return new ResponseEntity<>(e.getBindingResult().getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
    }
}
