package br.com.Initialiizr.Informatica116.sistem.validators;

import jakarta.persistence.Entity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Exeption {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity msg(RuntimeException e){
        return ResponseEntity.ok().body(new MSG(e.getMessage()));
    }
    @ResponseBody
    @ExceptionHandler(BadCredentialsException.class)
    public  ResponseEntity validUser(BadCredentialsException e){
        return ResponseEntity.badRequest().body(new MSG("Email ou senha invalidos!"));
  }
}

