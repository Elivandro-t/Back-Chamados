package br.com.Initialiizr.Informatica116.sistem.Models.COMENTARIOS;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "comments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    long chamadoid;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "comment")
    private List<ListaComments> itens;

    public Comments(long chamadoid, String usuario, String comentario, String email, String img) {
        this.chamadoid = chamadoid;
        this.itens.forEach(e->{
            e.Datas(LocalDateTime.now());
            e.setComments(comentario);
            e.setEmail(email);
            e.setComment(this);
            e.setUserImagem(img);
        });
    }
}
