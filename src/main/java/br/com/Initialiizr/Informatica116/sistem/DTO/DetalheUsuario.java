package br.com.Initialiizr.Informatica116.sistem.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalheUsuario {
    private long id;

    private String name;

    private  String lastname;

    private String setor;

    private int filial;

    private String email;

    private String imagem;
}
