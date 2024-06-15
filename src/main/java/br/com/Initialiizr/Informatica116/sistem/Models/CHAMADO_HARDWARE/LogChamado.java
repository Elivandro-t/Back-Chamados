package br.com.Initialiizr.Informatica116.sistem.Models.CHAMADO_HARDWARE;

import br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO.LogChamadoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "log")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogChamado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name_usuario_acess;
    private long usuario_id;
    private  String msg;
    private LocalDateTime timestamp;
    public LogChamado(LogChamadoDTO chamadoDTO) {
        this. name_usuario_acess = chamadoDTO.getName_usuario_acess();
        this.usuario_id = chamadoDTO.getUsuario_id();
        this.timestamp = LocalDateTime.now();
        this.msg = msg;
    }
}
