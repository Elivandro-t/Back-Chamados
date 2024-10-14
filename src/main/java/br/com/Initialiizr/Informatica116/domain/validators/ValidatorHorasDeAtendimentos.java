package br.com.Initialiizr.Informatica116.domain.validators;

import br.com.Initialiizr.Informatica116.domain.Models.Compras.ComprasEServicos;
import br.com.Initialiizr.Informatica116.domain.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
public class ValidatorHorasDeAtendimentos {
    @Autowired
   private CompraRepository compraRepository;
    public void  horasDeAtendimento(ComprasEServicos chamadoDTO){
        var dataChamado = chamadoDTO.getHora_aceito();
        boolean domingo = dataChamado.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var dataAbertura = dataChamado.getHour() <=8;
        var finalAbertura = dataChamado.getHour() >= 18;
        System.out.println("data em hora "+dataChamado.getHour());
        if(domingo || dataAbertura || finalAbertura){
            throw new RuntimeException("Fora do horario de atendimento!");
        }

    }
    public void  validaHoraDiaFechado(){
        var dataChamado = LocalDateTime.now();
        boolean domingo = dataChamado.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var dataAbertura = dataChamado.getHour() <=8;
        var finalAbertura = dataChamado.getHour() >= 18;
        if(domingo || dataAbertura || finalAbertura){
            throw new RuntimeException("Fora do horario de atendimento!");
        }

    }
}
