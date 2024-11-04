package br.com.Initialiizr.Informatica116.sistem.Models.CHAMADO_HARDWARE;

import br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO.UpdateChamado;
import br.com.Initialiizr.Informatica116.sistem.Models.AUTH_USER.User;
import br.com.Initialiizr.Informatica116.sistem.Models.COMENTARIOS.Comments;
import br.com.Initialiizr.Informatica116.sistem.Models.OPTIONS.Select;
import br.com.Initialiizr.Informatica116.sistem.Models.OPTIONS.Sistemas;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Entity
@Table(name = "issue")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private  String contato;
    @ManyToOne
    private Select servico;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "issue")
    private Set<Chamado> itens = new HashSet<>();
    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id")
    private User usuario;
    private LocalDateTime hora_aceito;
    private  LocalDateTime data_criacao;
    private int filial;
    @ManyToOne
    private  Comments comments;
    private String issueType;
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
        this.getItens().forEach(e->e.setTecnicoid(updateChamado.getTecnicoid()));
        this.getItens().forEach(e->e.setTecnico_responsavel(updateChamado.getTecnico_responsavel()));
    }

}
