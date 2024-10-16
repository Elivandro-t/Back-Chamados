package br.com.Initialiizr.Informatica116.domain.DTO.HardwareDTO;
import br.com.Initialiizr.Informatica116.domain.Models.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Integer matricula;
    private String patrimonio;
    private String equipamento;
    private Status status ;
    private boolean aceito;
    private boolean client_feito;
    private boolean done;
    private String datacreate;
    private String data_fechado;
    @JsonFormat(pattern = "dd-MM-yyyy")
    String data;
    private boolean ativo;
    private String usuario;
    private long tecnicoid ;
    private String tecnico_responsavel ;

}
