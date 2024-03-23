package br.com.Initialiizr.Informatica116.sistem.validators;

import br.com.Initialiizr.Informatica116.sistem.Models.Chamado;
import br.com.Initialiizr.Informatica116.sistem.Models.Hardware;
import br.com.Initialiizr.Informatica116.sistem.Models.HardwareDTO;
import br.com.Initialiizr.Informatica116.sistem.Models.Status;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationsTec {
    @Autowired
    ModelMapper modelMapper;
    public  void Valid(Hardware hardwareDTO){
        for (Chamado c:hardwareDTO.getItens()){
            Chamado chamado = modelMapper.map(c,Chamado.class);
            if(chamado.getTecnico_responsavel()==null&&chamado.getStatus()==Status.AGUARDANDO_TECNICO){
                throw new RuntimeException("erro ao mudar status, Status: aguardando tecnico");
            }

            break;
        }
    }
    public  void Status(Hardware hardwareDTO){
        for (Chamado c:hardwareDTO.getItens()){
            Chamado chamado = modelMapper.map(c,Chamado.class);
            if(chamado.getStatus()==Status.FEITO){
                throw new RuntimeException("erro ao atualizar status");
            }
            break;
        }
    }
}
