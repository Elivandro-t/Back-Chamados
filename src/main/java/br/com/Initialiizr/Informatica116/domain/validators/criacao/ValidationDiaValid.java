package br.com.Initialiizr.Informatica116.domain.validators.criacao;

import br.com.Initialiizr.Informatica116.domain.Models.CHAMADO_HARDWARE.Issue;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidationDiaValid implements  CriacaoValidacao{
    public void  valid(Issue issue){
        var horaCriacao = issue.getData_criacao();
        var HoraValid = horaCriacao.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var horaAntes = horaCriacao.getHour() < 8;
        var horaDePois = horaCriacao.getHour() >= 18;
        if(HoraValid || horaAntes || horaDePois){
            throw new RuntimeException("Fora do horário de atendimento, que é de segunda a sábado, das 08h às 18h.");
        }
    }
}
