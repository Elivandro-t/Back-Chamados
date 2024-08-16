package br.com.Initialiizr.Informatica116.sistem.DTO;

import br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO.ImagensDTO;
import br.com.Initialiizr.Informatica116.sistem.Models.Status;
import br.com.Initialiizr.Informatica116.sistem.Models.StatusComercial;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComprasObjectDTO {

    private long id;
    private long sistemaid;
    private String issuetype;
    private String cardId;
    private String titulo;
    private String setor;
    private  String solicitacao;
    private LocalDateTime hora_aceito;
    private String patrimonio;
    private String equipamento;
    private String status_andamento ;
    private String descricao ;
    private String usuario;
    private long tecnicoid ;
    private  Integer filial;
    // datas relacionadas ao chamado
    private String data_chamdo_feito;
    private String datacreate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private  String data;

    private boolean aceito;
    private boolean client_feito;
    private boolean done;
    private boolean ativo;
    private List<ImagensDTO> imagens;

}
