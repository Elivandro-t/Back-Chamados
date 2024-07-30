package br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IssueDTO {
    private long id;
    private long usuarioid;
    private String usuario_logado;
    private int filial;
    private  String contato;
    private String servico;
    private List<ChamadoDTO> itens;
    private int TotaldeItens;
    private  int total_itens;


}
