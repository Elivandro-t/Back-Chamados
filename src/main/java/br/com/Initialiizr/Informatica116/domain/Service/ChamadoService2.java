package br.com.Initialiizr.Informatica116.domain.Service;

import br.com.Initialiizr.Informatica116.domain.DTO.HardwareDTO.IssueDTO;
import br.com.Initialiizr.Informatica116.domain.Models.CHAMADO_HARDWARE.Issue;
import br.com.Initialiizr.Informatica116.domain.Models.Status;
import br.com.Initialiizr.Informatica116.domain.repository.IssueResposoty;
import br.com.Initialiizr.Informatica116.domain.repository.UserRepository;
import br.com.Initialiizr.Informatica116.domain.validators.MSG;
import br.com.Initialiizr.Informatica116.domain.validators.ValidationsTec;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ChamadoService2 {
    @Autowired
    private IssueResposoty issueResposoty;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    ValidationsTec validationsTec;
    @Autowired
    private UserRepository userRepository;
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
        var user = userRepository.getReferenceById(UsuarioLogado);
        // validacão de tecnico
        validationsTec.StatusvalidFechado(issue);
        issue.getItens().forEach(e->{
            e.setStatus(Status.RECUSADO);
            e.setAtivo(false);
            e.setAceito(false);
            e.setClient_feito(true);
            e.setDone(true);
            e.DataFeito(LocalDateTime.now());
            e.setTecnico_responsavel(user.getName() + " "+user.getLastname());
        });
        issueResposoty.save(issue);
        return  ResponseEntity.ok().body(new MSG("status fechado"));
    }
// Listando chamados atrelado ao tecnico
    public Page<IssueDTO> ListarChamadosTecnico(Pageable page,long idTecnico, String setor,
                                                String dataAntes, String dataDepois,
                                                boolean ativo) {
        String busca = setor != null ? setor : ""; // Define a busca como setor se não for nulo
        if(dataAntes!=null&&dataDepois!=null){
            return issueResposoty.findAllDataAntesAndDataDepoisByAtivoTrueAndFalseTec(page,dataAntes,dataDepois,idTecnico,ativo)
                    .map(e->modelMapper.map(e, IssueDTO.class));
        }
        else if(setor!=null){
            return issueResposoty.findAllBySetorContainingIgnoreCaseTrueAndFalseAndTec(page,idTecnico,busca,ativo)
                    .map(e->modelMapper.map(e, IssueDTO.class));
        }
        else {

            var dados = issueResposoty.findAllByAtivoTrueAndFalseAndTecnico(page,idTecnico,ativo)
                    .map(e->modelMapper.map(e, IssueDTO.class));

            return  dados;
        }

    }

    public  String RemoverChamado(long id){
        try {
            var remove = issueResposoty.findOneById(id);
            if (remove != null) {
                issueResposoty.delete(remove);
                return "Chamado com ID " + id + " deletado com sucesso";
            } else {
                return "Chamado com ID " + id + " não encontrado";
            }
        } catch (EmptyResultDataAccessException e) {
            return "Chamado com ID " + id + " não encontrado";
        } catch (Exception e) {
            // Tratamento genérico de exceções
            return "Erro ao tentar remover o chamado: " + e.getMessage();
        }
    }
}
