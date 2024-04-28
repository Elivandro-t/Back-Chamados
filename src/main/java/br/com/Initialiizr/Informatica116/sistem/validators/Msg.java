package br.com.Initialiizr.Informatica116.sistem.validators;

import org.springframework.http.ResponseEntity;

public class Msg extends RuntimeException{
    public  Msg(String msg){
        super(msg);
    }
}
