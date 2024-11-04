package br.com.Initialiizr.Informatica116.sistem.DTO.AUTH_DAO;

import br.com.Initialiizr.Informatica116.sistem.DTO.OPTIONS_DTO.SistemasDTO;
import br.com.Initialiizr.Informatica116.sistem.Models.AUTH_USER.Funcoes;
import br.com.Initialiizr.Informatica116.sistem.Models.AUTH_USER.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FuncoesDto {
    private  String id;
    private SistemasDTO sistemas;
    private boolean ativo;

    public FuncoesDto(Funcoes lista) {
        this.id = lista.getId();
        this.sistemas = new SistemasDTO(lista.getSistemas());
        this.ativo = lista.isAtivo();
    }
}
