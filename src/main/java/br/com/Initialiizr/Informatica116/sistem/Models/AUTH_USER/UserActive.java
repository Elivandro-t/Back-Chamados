package br.com.Initialiizr.Informatica116.sistem.Models.AUTH_USER;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "UserActive")
@Getter
@Setter
@NoArgsConstructor
public class UserActive {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique = true)
    private String userName;
    private boolean onlineUsuario;
    private LocalDateTime timestamp;
    private String ipAdress;
    @Version
    private Long version;
    public UserActive(String username, LocalDateTime lastActivity,boolean online,String ip) {
        this.userName = username;
        this.timestamp = lastActivity;
        this.onlineUsuario = online;
        this.ipAdress = ip;
    }

    public void savaDadosUsuario(LocalDateTime now, boolean b) {
        this.onlineUsuario=b;
        this.timestamp = now;
    }
}
