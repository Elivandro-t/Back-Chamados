package br.com.Initialiizr.Informatica116.sistem.Controler;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping
public class ControlerEmail {
    @Autowired
    private JavaMailSender javaMailSender;
    @GetMapping("/send/{email}")
    private ResponseEntity<String> send(@PathVariable String email){
     String code = gerarCode();
     try {
         SendEmil(email,code);
         return ResponseEntity.ok("seu codigo de veficacao foi  enviado para o email "+email);
     }catch (Exception e){
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("erro ao enviar email "+e.getMessage());
     }
    }
    private  String gerarCode(){
        return RandomStringUtils.randomAlphabetic(6).toUpperCase();
    }
    public void SendEmil(String email,String code){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject("seu codigo de verificação");
        msg.setText("Seu codigo de verificação e "+code);
        javaMailSender.send(msg);
    }
}
