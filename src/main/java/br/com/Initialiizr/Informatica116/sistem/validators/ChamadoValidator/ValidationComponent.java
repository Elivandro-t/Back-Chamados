package br.com.Initialiizr.Informatica116.sistem.validators.ChamadoValidator;

import br.com.Initialiizr.Informatica116.sistem.Models.CHAMADO_HARDWARE.Issue;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidationComponent {
  public void  validation(Issue issue){
      var dataChamadoAceito = issue.getHora_aceito();
      var dataAtual = LocalDateTime.now();
       int times = 60;
      var validaMinutos = Duration.between(dataChamadoAceito,dataAtual).toMinutes();
      if (validaMinutos< 60){
          var time = validaMinutos - times;
          if(validaMinutos==0){

              throw new RuntimeException("Chamado Bloqueado para liberação, aguarde 60 minutos  solicitar liberação");
          }
          throw new RuntimeException("Chamado Bloqueado para liberaçao , restam apenas "+ time + " minutos ");
      }
  }
}
