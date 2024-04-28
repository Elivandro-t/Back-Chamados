package br.com.Initialiizr.Informatica116.sistem.Models;

import br.com.Initialiizr.Informatica116.sistem.DTO.UpdateChamado;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "hardware")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hardware {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private long usuarioid;
    @NotNull
    private int filial;
    @NotBlank
    private String servico;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "hardware")
    private List<Chamado> itens;
    public String gerarCode(){
        int codigobase = 4000;

            return String.valueOf(gerarrandom(codigobase));

    }
    private int gerarrandom(int numero){
        Random random = new Random();
        for(int i = 0; i <10;i++){
            return numero+random.nextInt(10000)+i;
        }
        return 0;
    }

    public void atualiza(UpdateChamado updateChamado) {
        this.getItens().forEach(e->e.setTecnicoid(updateChamado.tecnicoid()));
        this.getItens().forEach(e->e.setTecnico_responsavel(updateChamado.tecnico_responsavel()));
    }

}
