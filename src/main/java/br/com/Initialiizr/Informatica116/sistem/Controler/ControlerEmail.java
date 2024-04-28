package br.com.Initialiizr.Informatica116.sistem.Controler;
import br.com.Initialiizr.Informatica116.sistem.DTO.ChamadoDTO;
import br.com.Initialiizr.Informatica116.sistem.DTO.Mensagem;
import br.com.Initialiizr.Informatica116.sistem.Models.HardwareDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping
@CrossOrigin(origins = "*")
public class ControlerEmail {
    @Autowired
    private JavaMailSender javaMailSender;
    @GetMapping("/send/{email}")
    private ResponseEntity<Mensagem> send(@PathVariable String email){
     String code = gerarCode();
     try {
         SendEmil(email,code);
         return ResponseEntity.ok(new Mensagem("seu codigo de verificação foi  enviado para o email registrado"));
     }catch (Exception e){
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Mensagem("erro ao enviar email "+e.getMessage()));
     }
    }
    private int gerarrandom(){
        Random random = new Random();
        return random.nextInt(10000);
    }
    private  String gerarCode(){
        return RandomStringUtils.randomAlphabetic(6).toUpperCase();
    }
    @Async
    public void SendEmil(String email,String code) throws MessagingException {
        String cod = "<span style='color:blue;font-weight:bold;font-size:18px;'>" + code + "</span>";
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(email);
        helper.setSubject("codigo de verificação");
        helper.setText("Seu codigo de verificação e "+cod,true);
        javaMailSender.send(message);
    }
    @Async
    public void Send(String email,HardwareDTO hardwareDTO){
        var usuario = hardwareDTO.getItens().stream().map(ChamadoDTO::getChamadoid).toList();
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject("chamado foi criado com sucesso");
        msg.setText("numero do seu chamdo "+usuario);
        javaMailSender.send(msg);
    }
}
