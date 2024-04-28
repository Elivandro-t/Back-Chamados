package br.com.Initialiizr.Informatica116.sistem.validators;

import br.com.Initialiizr.Informatica116.sistem.Models.*;
import br.com.Initialiizr.Informatica116.sistem.repository.HardwareResposoty;
import br.com.Initialiizr.Informatica116.sistem.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class ValidationsTec {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    HardwareResposoty hardwareResposoty;
    @Autowired
    UserRepository userRepository;
    public  void Valid(Hardware hardwareDTO,long usuariolog){
         Instant horaInicio = Instant.now();
        for (Chamado c:hardwareDTO.getItens()){
            Chamado chamado = modelMapper.map(c,Chamado.class);
            if(chamado.getTecnicoid()!=usuariolog){
                throw new RuntimeException("erro ao mudar status, voce não e o tecnico responsavel");
            }
                if(chamado.getTecnico_responsavel()==null&&chamado.getStatus()==Status.AGUARDANDO_TECNICO){
                    throw new RuntimeException("erro ao mudar status, Status: aguardando tecnico");
                }
            break;
        }
    }
    public  void reaberto(Hardware hardwareDTO,long usuariolog){
        for (Chamado c:hardwareDTO.getItens()){
            Chamado chamado = modelMapper.map(c,Chamado.class);
            if(chamado.getTecnico_responsavel()==null&&chamado.getStatus()==Status.AGUARDANDO_TECNICO){
                throw new RuntimeException("erro ao mudar status, Status: aguardando tecnico");
            }
            break;
        }
    }
    public  void Status(Hardware hardwareDTO){
        var user = userRepository.getReferenceById(hardwareDTO.getUsuarioid());
        System.out.println(user.getId());
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
    public  void StatusvalidFechado(Hardware hardwareDTO){
        var user = userRepository.getReferenceById(hardwareDTO.getUsuarioid());
        for (Chamado c:hardwareDTO.getItens()){
            Chamado chamado = modelMapper.map(c,Chamado.class);
            System.out.println(user.getId());
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
            else if(hardwareDTO.getUsuarioid()==id){
               throw new RuntimeException("voce não pode aceitar seu propio chamado");
           }

            if(chamado.getTecnico_responsavel()!=null&&!chamado.getTecnico_responsavel().trim().isEmpty()){
                throw new RuntimeException("Erro: tecnico "+chamado.getTecnico_responsavel() + " já está de posse desse chamado");
            }
            break;
        }
    }

    private Instant dataExpired() {
        return LocalDateTime.now().plusMinutes(1).toInstant(ZoneOffset.of("-03:00"));
    }
    private boolean Expired() {
        Instant agora = Instant.now();
        return agora.isAfter(dataExpired());
    }
    private void atualizarStatus(Hardware hardware) {
        System.out.println("h " +Expired());
        if (Expired()) {
            hardware.getItens().forEach(e->e.setStatus(Status.FECHADO));
            hardware.getItens().forEach(e->e.setAtivo(false));
            hardware.getItens().forEach(e->e.setAceito(false));
            hardware.getItens().forEach(e->e.setClient_feito(true));
            hardware.getItens().forEach(e->e.DataFeito(LocalDateTime.now()));
            hardwareResposoty.save(hardware);
            System.out.println("Status atualizado para 'feito'.");
        }
    }
}
