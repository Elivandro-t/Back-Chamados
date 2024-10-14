package br.com.Initialiizr.Informatica116.domain.DTO.AUTH_DAO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserComment {
    private String name;
    private  String lastname;
    private String email;
    private String data;
    private List<PerfilDTo> itens;

}
