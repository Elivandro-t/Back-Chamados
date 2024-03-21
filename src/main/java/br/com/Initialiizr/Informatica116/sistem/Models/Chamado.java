package br.com.Initialiizr.Informatica116.sistem.Models;

import br.com.Initialiizr.Informatica116.sistem.DTO.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "chamado")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Chamado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String chamadoid;
    @NotBlank
    private String titulo;
    @NotBlank
    private String setor;
    @NotBlank
    private String patrimonio;
    private String equipamento;
    @NotBlank
    private String status ;
    private boolean ativo;
    private boolean aceito;
    private boolean client_feito;
    private boolean done;
    private String data_chamdo_feito;
    private String data;
    @NotBlank
    private String descricao ;
    @NotBlank
    private String usuario;
    private long tecnicoid ;
    private String tecnico_responsavel ;
    @ManyToOne(optional = false)
    private Hardware hardware;
    public  String Datas(LocalDateTime data){
        DateTimeFormatter  pattern= DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm a");
        String dataFormat = data.format(pattern);
      return this.data = dataFormat;
    }

}
