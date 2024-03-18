package br.com.Initialiizr.Informatica116.sistem.Models;

import br.com.Initialiizr.Informatica116.sistem.DTO.ChamadoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HardwareDTO {
    private long id;
    private String servico;
    private List<ChamadoDTO> itens;
    private int TotaldeItens;


}
