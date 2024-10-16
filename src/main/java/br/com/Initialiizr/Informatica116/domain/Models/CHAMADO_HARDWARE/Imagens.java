package br.com.Initialiizr.Informatica116.domain.Models.CHAMADO_HARDWARE;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "imagens")
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
    private Chamado chamado;

    public Imagens(String nameFile) {
        this.name = nameFile;
    }
}
