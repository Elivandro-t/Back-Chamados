package br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO;
import br.com.Initialiizr.Informatica116.sistem.Models.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChamadoStatusDTO {
    private long id;
    private Status status ;
    @JsonFormat(pattern = "dd-MM-yyyy")
    String data;
    private boolean ativo;
    private String tecnico_responsavel ;
    private String data_fechado;

}
