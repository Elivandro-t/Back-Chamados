package br.com.Initialiizr.Informatica116.sistem.Models.COMENTARIOS;

import br.com.Initialiizr.Informatica116.sistem.Models.CHAMADO_HARDWARE.Chamado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comments_img")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImagensComments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToOne(optional = false)
    @JoinColumn(nullable = true)
    private ListaComments listaComments;

    public ImagensComments(String nameFile) {
        this.name = nameFile;
    }
}
