package br.com.Initialiizr.Informatica116.sistem.Models.CHAMADO_HARDWARE;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "chamado")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Chamado extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String patrimonio;
    private String equipamento;
    private String sistem_erro;
    private String erro;
    private long sistemaid;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "chamado")
    private List<Imagens> imagens;
    @ManyToOne(optional = false)
    private Issue issue;

    //
}
