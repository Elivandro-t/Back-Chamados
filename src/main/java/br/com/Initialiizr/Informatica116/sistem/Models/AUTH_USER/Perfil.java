package br.com.Initialiizr.Informatica116.sistem.Models.AUTH_USER;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "perfil")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Perfil implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String name;
    private boolean ativo;
    @ManyToOne(optional = false)
    private User user;
    @Override
    public String getAuthority() {
        return this.getName();
    }
}
