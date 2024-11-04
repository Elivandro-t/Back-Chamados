package br.com.Initialiizr.Informatica116.sistem.Models.AUTH_USER;

import br.com.Initialiizr.Informatica116.sistem.Models.OPTIONS.Sistemas;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Entity
@Table(name="funcoes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Funcoes {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private  String id;
    @ManyToOne
    private Sistemas sistemas;
    private boolean ativo;
    @ManyToOne(optional = false)
    private User usuario;
    public Funcoes(User usuario, Sistemas sistema) {
        this.sistemas = sistema;
        this.usuario = usuario;
        this.ativo = true;
    }
    public void ativaFuncao(boolean ativo) {
        this.ativo = ativo;
    }
}
