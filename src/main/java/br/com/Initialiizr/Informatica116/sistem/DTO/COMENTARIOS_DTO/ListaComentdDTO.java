package br.com.Initialiizr.Informatica116.sistem.DTO.COMENTARIOS_DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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

}
