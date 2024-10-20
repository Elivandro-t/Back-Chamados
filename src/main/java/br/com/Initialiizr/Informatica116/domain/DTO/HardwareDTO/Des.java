package br.com.Initialiizr.Informatica116.domain.DTO.HardwareDTO;

import br.com.Initialiizr.Informatica116.domain.Models.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Des {

    private long id;
    private long sistemaid;
    private String servico;
    private String chamadoid;
    private String titulo;
    private String setor;
    private String patrimonio;
    private  String solicitacao;
    private String equipamento;
    private String erro;
    private String sistema;
    private Status status ;
    private boolean aceito;
    private boolean client_feito;
    private boolean done;
    private String data_chamdo_feito;
    @JsonFormat(pattern = "dd-MM-yyyy")
    String data;
    private boolean ativo;
    private String descricao ;
    private String usuario;
    private long tecnicoid ;
    private String tecnico_responsavel ;
    private List<ImagensDTO> imagens;
    int total;
}
