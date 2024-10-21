package br.com.Initialiizr.Informatica116.domain.validators;

import jakarta.ws.rs.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
        return ResponseEntity.ok().body(new MSG("e-mail ou senha invalidos!"));
  }
    @ResponseBody
    @ExceptionHandler(BadRequestException.class)
    public  ResponseEntity Status400(BadRequestException e){
        return ResponseEntity.ok().body("e-mail ou senha invalidos!");
    }
}

