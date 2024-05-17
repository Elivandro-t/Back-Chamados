package br.com.Initialiizr.Informatica116.sistem.Models.COMENTARIOS;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}
