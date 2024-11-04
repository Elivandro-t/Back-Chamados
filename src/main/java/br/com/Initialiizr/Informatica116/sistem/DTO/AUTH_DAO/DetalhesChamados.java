package br.com.Initialiizr.Informatica116.sistem.DTO.AUTH_DAO;

import br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO.IssueDetalheDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalhesChamados {
    private long id;
    private String name;
    private  String lastname;
    private String setor;
    private int filial;
    private String email;
    private String imagem;
    private List<IssueDetalheDTO> solicitacoes;
}
