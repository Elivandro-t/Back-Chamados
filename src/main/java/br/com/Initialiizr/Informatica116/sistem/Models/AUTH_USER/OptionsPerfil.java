package br.com.Initialiizr.Informatica116.sistem.Models.AUTH_USER;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "options_perfil")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OptionsPerfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
}
