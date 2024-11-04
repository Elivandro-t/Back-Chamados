package br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO;

import br.com.Initialiizr.Informatica116.sistem.Models.OPTIONS.Sistemas;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IssueDTO {
    private long id;
//    private long usuarioid;
//    private String usuario_logado;
    @NonNull
    private int filial;
    private  String contato;
    @NonNull
    private long servico_Id;
    private List<ChamadoDTO> itens;
    private int TotaldeItens;
    private  int total_itens;
    @NotBlank
    private String emailUsuarioLogado;

}
