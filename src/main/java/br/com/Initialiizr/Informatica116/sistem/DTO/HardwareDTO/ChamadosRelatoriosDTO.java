package br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO;
import br.com.Initialiizr.Informatica116.sistem.Models.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChamadosRelatoriosDTO {

    private long id;
    private long sistemaid;
    private String issuetype;
    private String cardId;
    private String titulo;
    private String setor;
    private  String solicitacao;
    private String patrimonio;
    private String equipamento;
    private Status status ;
    private boolean aceito;
    private boolean client_feito;
    private boolean done;
    private String data_chamdo_feito;
    private String datacreate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    String data;
    private boolean ativo;
    private String usuario;
    private long tecnicoid ;
    private String tecnico_responsavel ;

}
