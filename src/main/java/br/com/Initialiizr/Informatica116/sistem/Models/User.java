package br.com.Initialiizr.Informatica116.sistem.Models;

import br.com.Initialiizr.Informatica116.sistem.DTO.UserDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String name;
    @NotBlank
    private  String lastname;
    @NotBlank
    private String setor;
    @NotBlank
    private String email;
    @NotNull
    private int filial;
    @NotBlank
    private String password;
    private String codigo;
    private  int exp = 0;
    private String refreshToken;
    @JoinColumn(nullable = true)
    private String imagem;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user",fetch = FetchType.EAGER)
    private List<Perfil> itens;
    public User(UserDTO user) {
        this.name =user.getName();
        this.lastname =user.getLastname();
        this.setor = user.getSetor();
        this.email = user.getEmail();
    }
   public  String criptografar(String user){
      return this.password = new BCryptPasswordEncoder().encode(user);
   }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return itens;
    }
//public Collection<? extends GrantedAuthority> getAuthorities() {
//    return List.of(new SimpleGrantedAuthority("ROLE_USER"));
//}


    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void atualiza(String imagem) {
        this.imagem = imagem;
    }
    public void incrementVerificationAttempts() {
        this.exp++;
    }
    public void resetVerificationAttempts() {
        this.exp = 0;
    }
}
