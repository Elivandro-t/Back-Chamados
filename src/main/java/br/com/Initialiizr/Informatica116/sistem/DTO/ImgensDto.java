package br.com.Initialiizr.Informatica116.sistem.DTO;

import br.com.Initialiizr.Informatica116.sistem.Models.Imagens;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImgensDto {
    private long id;
    private String name;

    public ImgensDto(String nameFile) {
        this.name = nameFile;
    }
}
