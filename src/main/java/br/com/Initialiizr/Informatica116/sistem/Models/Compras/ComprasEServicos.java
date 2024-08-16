package br.com.Initialiizr.Informatica116.sistem.Models.Compras;

import br.com.Initialiizr.Informatica116.sistem.Models.CHAMADO_HARDWARE.Model;
import br.com.Initialiizr.Informatica116.sistem.Models.StatusComercial;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Random;

@Entity
@Table(name = "compras")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ComprasEServicos extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private long usuarioid;
    private  String contato;
    private  String status_andamento;
    private LocalDateTime hora_aceito;
    public String gerarCode(){
        int codigobase = 7000;

        return String.valueOf(gerarrandom(codigobase));

    }
    private int gerarrandom(int numero){
        Random random = new Random();
        for(int i = 0; i <10;i++){
            return numero+random.nextInt(20000)+i;
        }
        return 0;
    }
}
