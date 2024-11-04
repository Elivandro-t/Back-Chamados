package br.com.Initialiizr.Informatica116.sistem.validators.ChamadoValidator;

import br.com.Initialiizr.Informatica116.sistem.Models.CHAMADO_HARDWARE.Issue;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
public class ValidationDiaValid {
    public void  validation(Issue issue){
        var horaCriacao = issue.getData_criacao();
        var HoraValid = horaCriacao.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var horaAntes = horaCriacao.getHour() < 8;
        var horaDePois = horaCriacao.getHour() >= 19;
        if( horaAntes || horaDePois || HoraValid){
            throw new RuntimeException("Fora do horário de atendimento, que é de segunda a sábado, das 08h às 18h.");
        }
    }
}
