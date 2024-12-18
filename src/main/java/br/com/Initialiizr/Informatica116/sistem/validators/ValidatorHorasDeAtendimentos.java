package br.com.Initialiizr.Informatica116.sistem.validators;

import br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO.ChamadoDTO;
import br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO.IssueDTO;
import br.com.Initialiizr.Informatica116.sistem.Models.Compras.ComprasEServicos;
import br.com.Initialiizr.Informatica116.sistem.repository.CompraRepository;
import org.glassfish.jersey.internal.guava.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidatorHorasDeAtendimentos {
    @Autowired
   private CompraRepository compraRepository;
    public void  horasDeAtendimento(ComprasEServicos chamadoDTO){
        var dataChamado = chamadoDTO.getHora_aceito();
        boolean domingo = dataChamado.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var dataAbertura = dataChamado.getHour() <=7;
        var finalAbertura = dataChamado.getHour() >= 23;
        if(domingo || dataAbertura || finalAbertura){
            throw new RuntimeException("Fora do horario de atendimento!");
        }

    }
    public void  validaHoraDiaFechado(){
        var dataChamado = LocalDateTime.now();
        boolean domingo = dataChamado.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var dataAbertura = dataChamado.getHour() <=7;
        var finalAbertura = dataChamado.getHour() >= 23;
        if(domingo || dataAbertura || finalAbertura){
            throw new RuntimeException("Fora do horario de atendimento!");
        }

    }
}
