package org.truecodes.DigitalLibrary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.truecodes.DigitalLibrary.service.TxnService;

@ControllerAdvice
public class ControllerExceptionAdvice {
    @ExceptionHandler(value = TxnException.class)
    public ResponseEntity<Object> handle(TxnException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
