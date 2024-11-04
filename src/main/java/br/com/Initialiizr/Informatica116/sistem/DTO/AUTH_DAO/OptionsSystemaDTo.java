package br.com.Initialiizr.Informatica116.sistem.DTO.AUTH_DAO;

import br.com.Initialiizr.Informatica116.sistem.Models.OPTIONS.Select;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OptionsSystemaDTo {
    private long id;
    private String name;
    private String titulo;

    public OptionsSystemaDTo(Select servico) {
        this.id = servico.getId();
        this.name = servico.getName();
        this.titulo = servico.getTitulo();
    }
}
