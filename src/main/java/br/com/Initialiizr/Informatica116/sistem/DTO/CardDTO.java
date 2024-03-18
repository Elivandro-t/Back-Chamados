package br.com.Initialiizr.Informatica116.sistem.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardDTO {

    private long card_id;

    private String titulo;

    private String subtitulo;
    private String img;
}
