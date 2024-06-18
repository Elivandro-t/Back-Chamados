package br.com.Initialiizr.Informatica116.sistem.Models.COMENTARIOS;

import br.com.Initialiizr.Informatica116.sistem.Models.CHAMADO_HARDWARE.Imagens;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name = "lista_commentarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListaComments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(columnDefinition = "TEXT")
    private String comments;
    private String usuario;
    private String  email ;
    private String data;
    private String userImagem;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "listaComments")
    private List<ImagensComments> imagens;

    @ManyToOne(optional = false)
    private Comments comment;
    public  String Datas(LocalDateTime data){
        DateTimeFormatter pattern= DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm a");
        String dataFormat = data.format(pattern);
        return this.data = dataFormat;
    }
}
