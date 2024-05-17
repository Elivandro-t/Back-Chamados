package br.com.Initialiizr.Informatica116.sistem.DTO.COMENTARIOS_DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentsDTO {
    long id;
    long chamadoid;
    private List<ListaComentdDTO>  itens;
}
