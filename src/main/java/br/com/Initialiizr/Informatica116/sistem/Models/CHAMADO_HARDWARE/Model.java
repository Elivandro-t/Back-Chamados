package br.com.Initialiizr.Informatica116.sistem.Models.CHAMADO_HARDWARE;

import br.com.Initialiizr.Informatica116.sistem.Models.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long sistemaid;
    private  String solicitacao;
    private String cardId;
    private String issuetype;
    private String titulo;
    private String setor;
    @Enumerated(EnumType.STRING)
    private Status status ;
    @Column(columnDefinition = "TEXT")
    private String descricao ;
    private String usuario;
    private long tecnicoid ;
    private String tecnico_responsavel ;
    private boolean ativo;
    private boolean aceito;
    private boolean client_feito;
    private boolean done;
    private String gmid;
    private  Integer filial;
    private Integer matricula;
    private String tell;
    private String cpf;
    private String emailGestorAprovador;
    private String nomeDogestor;
    private String emailUsuario;
    private String data_admin;
    private String data_nasc;
    private String centro_de_custo;
    private String funcao;
    @Column(name = "data_chamdo_feito", nullable = true) // Permite null
    private LocalDateTime data_chamdo_feito;
    private String data;
    private String datacreate;
    private LocalDateTime dataTecnicoAceito;
    private String data_fechado;
    public  String Datas(LocalDateTime data){
        DateTimeFormatter pattern= DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm a");
        String dataFormat = data.format(pattern);
        return this.data = dataFormat;
    }
    public  String DataFeito(LocalDateTime data){
        DateTimeFormatter  pattern= DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm a");
        String dataFormat = data.format(pattern);
        return this.data_fechado = dataFormat;
    }
    public  String DataCreate(LocalDateTime data){
        DateTimeFormatter  pattern= DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dataFormat = data.format(pattern);
        return this.datacreate= dataFormat;
    }

}
