package br.com.Initialiizr.Informatica116.sistem.validators.validaStatusFechadoETecnicoLogado;

import br.com.Initialiizr.Informatica116.sistem.Models.CHAMADO_HARDWARE.Chamado;
import br.com.Initialiizr.Informatica116.sistem.Models.CHAMADO_HARDWARE.Issue;
import br.com.Initialiizr.Informatica116.sistem.Models.Status;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaStatusFechado implements ValidaStatusFechadoELogado{
    @Autowired
    ModelMapper modelMapper;
    public  void valid(Issue issueDTO,long s){
        for (Chamado c: issueDTO.getItens()){
            if(c.getStatus() == Status.FEITO){
                throw new RuntimeException("Erro ao atualizar status");
            }
            else if(c.getStatus() == Status.FECHADO){
                throw new RuntimeException("Chamado FECHADO");
            }
            break;
        }
    }
}
