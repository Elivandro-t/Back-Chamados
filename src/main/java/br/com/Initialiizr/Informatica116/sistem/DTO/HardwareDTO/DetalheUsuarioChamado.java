package br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO;

import br.com.Initialiizr.Informatica116.sistem.DTO.AUTH_DAO.FuncoesDto;
import br.com.Initialiizr.Informatica116.sistem.Models.AUTH_USER.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalheUsuarioChamado {
    private long id;
    private String name;
    private  String lastname;
    private String setor;
    private int filial;
    private String email;
    private String contato;
    private String imagem;

    public DetalheUsuarioChamado(User usuario) {
        this.name = getName();
        this.imagem = usuario.getImagem();
    }
}
