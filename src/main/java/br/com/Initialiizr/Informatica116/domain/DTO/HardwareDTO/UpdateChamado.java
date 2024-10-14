package br.com.Initialiizr.Informatica116.domain.DTO.HardwareDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateChamado{
        long id;
        String tecnico_responsavel;
        long tecnicoid;
        boolean done;

}
