package br.com.Initialiizr.Informatica116.sistem.DTO.OPTIONS_DTO;

import br.com.Initialiizr.Informatica116.sistem.DTO.AUTH_DAO.OptionsSystemaDTo;
import br.com.Initialiizr.Informatica116.sistem.Models.OPTIONS.Sistemas;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SistemasDTO {
    private long id;
    private String name;
    private String titulo;
    private String imagem;
    private List<OptionsSystemaDTo> options;

    public SistemasDTO(Sistemas sistemas) {
        this.id = sistemas.getId();
        this.name = sistemas.getName();
    }
}
