package br.com.Initialiizr.Informatica116.sistem.validators;

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
        Instant horaInicio = Instant.now();
        for (Chamado c: issueDTO.getItens()){
            Chamado chamado = modelMapper.map(c,Chamado.class);
            if(chamado.getTecnicoid()!=usuariolog){
                throw new RuntimeException("Usuario "+chamado.getTecnico_responsavel() + " já está de posse desse chamado");
            }
            if(chamado.getTecnico_responsavel()==null&&chamado.getStatus()==Status.AGUARDANDO_TECNICO){
                throw new RuntimeException("Aguardando tecnico");
            }
            break;
        }
    }

    public  void reaberto(Issue issueDTO, long usuariolog){
        for (Chamado c: issueDTO.getItens()){
            Chamado chamado = modelMapper.map(c,Chamado.class);
            if(chamado.getTecnico_responsavel()==null&&chamado.getStatus()==Status.AGUARDANDO_TECNICO){
                throw new RuntimeException("Erro: AGUARDANDO TECNICO");
            }
            break;
        }
    }
    public  void Status(Issue issueDTO){
        var user = userRepository.getReferenceById(issueDTO.getUsuarioid());
        System.out.println(user.getId());
        for (Chamado c: issueDTO.getItens()){
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
    public  void StatusJira(Issue issueDTO){
        var user = userRepository.getReferenceById(issueDTO.getUsuarioid());
        for (Chamado c: issueDTO.getItens()){
            Chamado chamado = modelMapper.map(c,Chamado.class);
            if(chamado.getStatus()==Status.AGUARDANDO_VALIDACAO){
                throw new RuntimeException("Erro: Status AGUARDANDO VALIDACAO");
            }
            else if(chamado.getStatus()==Status.FECHADO){
                throw new RuntimeException("Chamado está FECHADO!");
            }
            break;
        }
    }
    public  void Aprovador(Issue issueDTO){
        var user = userRepository.getReferenceById(issueDTO.getUsuarioid());
        for (Chamado c: issueDTO.getItens()){
            Chamado chamado = modelMapper.map(c,Chamado.class);
            if(chamado.getStatus()==Status.AGUARDANDO_VALIDACAO){
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

    public  void StatusvalidFechado(Issue issueDTO){
        var user = userRepository.getReferenceById(issueDTO.getUsuarioid());
        for (Chamado c: issueDTO.getItens()){
            Chamado chamado = modelMapper.map(c,Chamado.class);
            System.out.println(user.getId());
            if(chamado.getStatus()==Status.FECHADO){
                throw new RuntimeException("Chamado está fechado");
            }
            else if(chamado.getStatus()==Status.RE_ABERTO){
                throw new RuntimeException(" Chamado ja está aberto");
            }
            else if(chamado.getStatus()==Status.EM_ANDAMENTO){
                throw new RuntimeException("Solicite validação ao usuario");
            }
            break;
        }
    }
    public  void existeTecnico(Issue issueDTO, long id){
        for (Chamado c: issueDTO.getItens()){
            Chamado chamado = modelMapper.map(c,Chamado.class);
            if(issueDTO ==null){
                throw new RuntimeException("Chamado fechado");
            }
            if(chamado.getTecnicoid()==id){
                throw new RuntimeException("Voce ja está de posse desse chamado");
            }
            else if(issueDTO.getUsuarioid()==id){
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
    private void atualizarStatus(Issue issue) {
        System.out.println("h " +Expired());
        if (Expired()) {
            issue.getItens().forEach(e->e.setStatus(Status.FECHADO));
            issue.getItens().forEach(e->e.setAtivo(false));
            issue.getItens().forEach(e->e.setAceito(false));
            issue.getItens().forEach(e->e.setClient_feito(true));
            issue.getItens().forEach(e->e.DataFeito(LocalDateTime.now()));
            issueResposoty.save(issue);
            System.out.println("Status atualizado para 'feito'.");
        }
    }
}
