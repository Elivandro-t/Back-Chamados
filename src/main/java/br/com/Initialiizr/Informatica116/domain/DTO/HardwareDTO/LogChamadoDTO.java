package br.com.Initialiizr.Informatica116.domain.DTO.HardwareDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogChamadoDTO {
    private long id;
    private long card_id;
    private String name_usuario_acess;
    private long usuario_id;
    private String msg;
    private String timestamp;
}
