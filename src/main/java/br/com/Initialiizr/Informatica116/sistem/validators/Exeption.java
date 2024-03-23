package br.com.Initialiizr.Informatica116.sistem.validators;

import jakarta.persistence.Entity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Exeption {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity msg(RuntimeException e){
        return ResponseEntity.ok().body(new MSG(e.getMessage()));
    }
}
