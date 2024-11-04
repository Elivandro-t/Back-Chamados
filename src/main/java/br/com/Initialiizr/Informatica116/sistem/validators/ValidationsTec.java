package br.com.Initialiizr.Informatica116.sistem.validators;

import br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO.ChamadoDTO;
import br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO.IssueDTO;
import br.com.Initialiizr.Informatica116.sistem.Models.*;
import br.com.Initialiizr.Informatica116.sistem.Models.CHAMADO_HARDWARE.Chamado;
import br.com.Initialiizr.Informatica116.sistem.Models.CHAMADO_HARDWARE.Issue;
import br.com.Initialiizr.Informatica116.sistem.repository.IssueResposoty;
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
    IssueResposoty issueResposoty;
    @Autowired
    UserRepository userRepository;

    public  void Valid(Issue issueDTO, long usuariolog){
        for (Chamado chamado: issueDTO.getItens()){
            if(chamado.getTecnicoid()!=usuariolog){
                throw new RuntimeException("Usuario "+chamado.getTecnico_responsavel() + " já está de posse desse chamado");
            }
            if(chamado.getTecnico_responsavel()==null&&chamado.getStatus()==Status.AGUARDANDO_TECNICO){
                throw new RuntimeException("Aceite o card");
            }
            break;
        }
    }

    public  void Status(Issue issue){
        for (Chamado c: issue.getItens()){
            Chamado chamado = modelMapper.map(c,Chamado.class);
            if(chamado.getStatus()==Status.FEITO){
                throw new RuntimeException("Erro ao atualizar status");
            }
            else if(chamado.getStatus()==Status.FECHADO){
                throw new RuntimeException("Chamado FECHADO");
            }
            break;
        }
    }

    public  void existeTecnico(Issue issueDTO,String email, long id){
        var usuario = userRepository.findByEmail(email);
        Issue issue = modelMapper.map(issueDTO,Issue.class);
        for (Chamado c: issue.getItens()){
            Chamado chamado = modelMapper.map(c,Chamado.class);
            if(issueDTO ==null){
                throw new RuntimeException("Chamado fechado");
            }
            if(chamado.getTecnicoid()==usuario.getId()){
                throw new RuntimeException("Voce ja está de posse desse chamado");
            }
            else if(usuario.getId()==id){
                throw new RuntimeException("Voce não pode aceitar seu propio chamado");
            }

            if(chamado.getTecnico_responsavel()!=null&&!chamado.getTecnico_responsavel().trim().isEmpty()){
                throw new RuntimeException("Erro: Tecnico "+chamado.getTecnico_responsavel() + " já está de posse desse chamado");
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
//    private void atualizarStatus(Issue issue) {
//        System.out.println("h " +Expired());
//        if (Expired()) {
//            issue.getItens().forEach(e->e.setStatus(Status.FECHADO));
//            issue.getItens().forEach(e->e.setAtivo(false));
//            issue.getItens().forEach(e->e.setAceito(false));
//            issue.getItens().forEach(e->e.setClient_feito(true));
//            issueResposoty.save(issue);
//            System.out.println("Status atualizado para 'feito'.");
//        }
//    }
public  void StatusJira(Issue issueDTO){
    for (Chamado c: issueDTO.getItens()){
        Chamado chamado = modelMapper.map(c,Chamado.class);
        if(chamado.getStatus()== Status.AGUARDANDO_VALIDACAO){
            throw new RuntimeException("Erro: Status AGUARDANDO VALIDACAO");
        }
        else if(chamado.getStatus()==Status.FECHADO){
            throw new RuntimeException("Chamado está FECHADO!");
        }
        break;
    }
}

    public  void Aprovador(Issue issueDTO){
        for (Chamado c: issueDTO.getItens()){
            Chamado chamado = modelMapper.map(c,Chamado.class);
            if(chamado.getStatus()== Status.AGUARDANDO_VALIDACAO){
                throw new RuntimeException("Erro: Status AGUARDANDO VALIDACAO");
            }
            else if(chamado.getStatus()==Status.AGUARDANDO_JIRA){
                throw new RuntimeException("Chamado está AGUARDANDO JIRA!");
            }
            else if(chamado.getStatus()==Status.FECHADO){
                throw new RuntimeException("Chamado está FECHADO!");
            }
            break;
        }
    }

    public  void reaberto(Issue issueDTO){
        for (Chamado chamado: issueDTO.getItens()){
            if(chamado.getTecnico_responsavel()==null&&chamado.getStatus()== Status.AGUARDANDO_TECNICO){
                throw new RuntimeException("Erro: AGUARDANDO TECNICO");
            }
            break;
        }
    }

    public  void StatusvalidFechado(Issue issueDTO) {
        for (Chamado c : issueDTO.getItens()) {
            Chamado chamado = modelMapper.map(c, Chamado.class);
            if (chamado.getStatus() == Status.FECHADO) {
                throw new RuntimeException("Chamado está fechado");
            } else if (chamado.getStatus() == Status.RECUSADO) {
                throw new RuntimeException("Chamado está recusado");
            } else if (chamado.getStatus() == Status.RE_ABERTO) {
                throw new RuntimeException(" Chamado ja está em aberto");
            } else if (chamado.getStatus() == Status.EM_ANDAMENTO) {
                throw new RuntimeException("Solicite validação ao usuario");
            }
            break;
        }
    }


}
