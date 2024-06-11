package br.com.Initialiizr.Informatica116.sistem.Service;

import br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO.IssueDTO;
import br.com.Initialiizr.Informatica116.sistem.Models.CHAMADO_HARDWARE.Issue;
import br.com.Initialiizr.Informatica116.sistem.Models.Status;
import br.com.Initialiizr.Informatica116.sistem.repository.IssueResposoty;
import br.com.Initialiizr.Informatica116.sistem.validators.MSG;
import br.com.Initialiizr.Informatica116.sistem.validators.ValidationsTec;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;

@Service
public class ChamadoService2 {
    @Autowired
    private IssueResposoty issueResposoty;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    ValidationsTec validationsTec;
    public Page<IssueDTO> listaValidados(Pageable page, long idUsuario){
        var name = issueResposoty.FindAllByHardwareByStatusValidacao(page,idUsuario)
                .map(e->modelMapper.map(e, IssueDTO.class));
        return name;
    }

    public ResponseEntity validaChamadoRecusado(long id, String cardChamado, long UsuarioLogado){
        Issue issue = issueResposoty.findOneByIdChamado(id,cardChamado);
        System.out.println(id);
        if(issue ==null){
            throw new RuntimeException("nada encontrado");
        }
        // validacão de tecnico
        validationsTec.StatusvalidFechado(issue);
        issue.getItens().forEach(e->e.setStatus(Status.RECUSADO));
        issue.getItens().forEach(e->e.setAtivo(false));
        issue.getItens().forEach(e->e.setAceito(false));
        issue.getItens().forEach(e->e.setClient_feito(true));
        issue.getItens().forEach(e->e.DataFeito(LocalDateTime.now()));
        issueResposoty.save(issue);
        return  ResponseEntity.ok().body(new MSG("status fechado"));
    }
}
