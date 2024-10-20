package br.com.Initialiizr.Informatica116.domain.Models.OPTIONS;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="card")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long card_id;
    @NotBlank
    private String titulo;
    @NotBlank
    private String subtitulo;
    private String img;
}
