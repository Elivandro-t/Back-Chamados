package br.com.Initialiizr.Informatica116.sistem.Models.CHAMADO_HARDWARE;

import br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO.LogChamadoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    private long card_id;
    private String name_usuario_acess;
    private long usuario_id;
    private  String msg;
    private String timestamp;
    public LogChamado(LogChamadoDTO chamadoDTO) {
        this. name_usuario_acess = chamadoDTO.getName_usuario_acess();
        this.usuario_id = chamadoDTO.getUsuario_id();
        this.card_id = chamadoDTO.getCard_id();
        this.timestamp = Datas(LocalDateTime.now());
        this.msg = chamadoDTO.getMsg();
    }
    public  String Datas(LocalDateTime data){
        DateTimeFormatter pattern= DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm a");
        String dataFormat = data.format(pattern);
        return this.timestamp = dataFormat;
    }
}
