package br.com.Initialiizr.Informatica116.sistem.DTO;

import jakarta.validation.constraints.NotBlank;
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
}
