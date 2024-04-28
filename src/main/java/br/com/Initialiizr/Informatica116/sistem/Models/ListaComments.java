package br.com.Initialiizr.Informatica116.sistem.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "lista_commentarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListaComments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String comments;
    String usuario;
    String  email ;
    @ManyToOne(optional = false)
    private Comments comment;
}
