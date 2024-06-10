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
public class StatusOneDTO {
    private long id;
    private long usuarioid;
    private int filial;
    private String servico;
    private List<ChamadoStatusDTO> itens;
    private  int total_itens;
    private int TotaldeItens;


}
