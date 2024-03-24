package br.com.Initialiizr.Informatica116.sistem.validators;

import br.com.Initialiizr.Informatica116.sistem.Models.Chamado;
import br.com.Initialiizr.Informatica116.sistem.Models.Hardware;
import br.com.Initialiizr.Informatica116.sistem.Models.HardwareDTO;
import br.com.Initialiizr.Informatica116.sistem.Models.Status;
import br.com.Initialiizr.Informatica116.sistem.repository.HardwareResposoty;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidationsTec {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    HardwareResposoty hardwareResposoty;
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
            else if(chamado.getStatus()==Status.FECHADO){
                throw new RuntimeException("chamado fechado");
            }
            break;
        }
    }
    public  void Statusvalid(Hardware hardwareDTO){
        for (Chamado c:hardwareDTO.getItens()){
            Chamado chamado = modelMapper.map(c,Chamado.class);
            if(chamado.getStatus()==Status.AGUARDANDO_VALIDACAO){
                throw new RuntimeException(" chamado ja está em validacao");
            }
            if(chamado.getStatus()==Status.FECHADO){
                throw new RuntimeException("chamado fechado");
            }
            break;
        }
    }
    public  void StatusvalidFechado(Hardware hardwareDTO){
        for (Chamado c:hardwareDTO.getItens()){
            Chamado chamado = modelMapper.map(c,Chamado.class);
            if(chamado.getStatus()==Status.FECHADO){
                throw new RuntimeException("chamado fechado");
            }
            else if(chamado.getStatus()==Status.RE_ABERTO){
                throw new RuntimeException(" chamado ja está aberto");
            }
            else if(chamado.getStatus()==Status.EM_ANDAMENTO){
                throw new RuntimeException(" chamado ja está aberto");
            }
            break;
        }
    }
    public  void existeTecnico(Hardware hardwareDTO,long id){
        for (Chamado c:hardwareDTO.getItens()){
            Chamado chamado = modelMapper.map(c,Chamado.class);
            if(hardwareDTO==null){
                throw new RuntimeException("chamado fechado");
            }
           if(chamado.getTecnicoid()==id){
                throw new RuntimeException("voce ja está de posse desse chamado");
            }

            if(chamado.getTecnico_responsavel()!=null&&!chamado.getTecnico_responsavel().trim().isEmpty()){
                throw new RuntimeException("Erro: tecnico "+chamado.getTecnico_responsavel() + " já está de posse desse chamado");
            }
            break;
        }
    }
}
