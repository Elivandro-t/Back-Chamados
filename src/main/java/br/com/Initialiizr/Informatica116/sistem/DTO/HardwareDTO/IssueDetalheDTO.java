package br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO;

import br.com.Initialiizr.Informatica116.sistem.DTO.AUTH_DAO.DetalheUsuario;
import br.com.Initialiizr.Informatica116.sistem.DTO.AUTH_DAO.OptionsSystemaDTo;
import br.com.Initialiizr.Informatica116.sistem.Models.CHAMADO_HARDWARE.Issue;
import br.com.Initialiizr.Informatica116.sistem.Models.OPTIONS.Select;
import br.com.Initialiizr.Informatica116.sistem.Models.OPTIONS.Sistemas;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Collections;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IssueDetalheDTO {
    private long id;
//    private long usuarioid;
//    private String usuario_logado;
    private int filial;
    private  String contato;
    private OptionsSystemaDTo servico;
    private DetalheUsuarioChamado usuario;
    private List<ChamadoDTO> itens;

    public IssueDetalheDTO(Issue issue) {
        this.id = issue.getId();
        this.filial = issue.getFilial();
        this.contato = issue.getContato();
        this.servico = new OptionsSystemaDTo(issue.getServico());
        this.usuario = new DetalheUsuarioChamado(issue.getUsuario());
        this.itens = Collections.singletonList((ChamadoDTO) issue.getItens().stream().toList());
    }
}
