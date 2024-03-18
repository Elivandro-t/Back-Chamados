package br.com.Initialiizr.Informatica116.sistem.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "imagem")
@Getter
@Setter
public class Imagens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToOne(optional = true)
    @JoinColumn(nullable = true)
    private Chamado imagens;
    public Imagens() {

    }
    public Imagens(String originalFilename) {
        this.setName(originalFilename);
    }
}
