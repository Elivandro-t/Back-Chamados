package br.com.Initialiizr.Informatica116.domain.DTO.COMENTARIOS_DTO;

import br.com.Initialiizr.Informatica116.domain.DTO.HardwareDTO.ImagensDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListaComentdDTO {
   private long id;
    private String comments;
    private String usuario;
    private String  email ;
    private String userImagem;
    private String data;
    private List<ImagensDTO> imagens;

}
