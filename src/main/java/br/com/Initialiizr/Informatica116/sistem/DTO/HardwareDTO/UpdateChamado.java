package br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO;

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
