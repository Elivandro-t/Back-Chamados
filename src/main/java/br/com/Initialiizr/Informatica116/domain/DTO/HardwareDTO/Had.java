package br.com.Initialiizr.Informatica116.domain.DTO.HardwareDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Had {
    private long id;
    private long usuarioid;
    private int filial;
    private String servico;

    private List<Des> itens;
    private  int total_itens;
    private int TotaldeItens;
}
