package br.com.Initialiizr.Informatica116.domain.validators.validaStatusFechadoETecnicoLogado;

import br.com.Initialiizr.Informatica116.domain.Models.CHAMADO_HARDWARE.Chamado;
import br.com.Initialiizr.Informatica116.domain.Models.CHAMADO_HARDWARE.Issue;
import br.com.Initialiizr.Informatica116.domain.Models.Status;
import org.springframework.stereotype.Component;

@Component
public class ValidationStatusExisteTecnico implements  ValidaStatusFechadoELogado{
    public  void valid(Issue issueDTO, long usuariolog){
        for (Chamado chamado: issueDTO.getItens()){
            if(chamado.getTecnicoid()!=usuariolog){
                throw new RuntimeException("Usuario "+chamado.getTecnico_responsavel() + " já está de posse desse chamado");
            }
            if(chamado.getTecnico_responsavel()==null&&chamado.getStatus()== Status.AGUARDANDO_TECNICO){
                throw new RuntimeException("Aceite o card");
            }
            break;
        }
    }
}
