package br.com.Initialiizr.Informatica116.sistem.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListaComentdDTO {
    long id;
    String comments;
    String usuario;
    String  email ;
}
