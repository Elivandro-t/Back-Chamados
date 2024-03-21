package br.com.Initialiizr.Informatica116.sistem.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "imagem")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Imagens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToOne(optional = false)
    @JoinColumn(nullable = true)
    private Hardware path;

    public Imagens(String nameFile) {
        this.name = nameFile;
    }
}
