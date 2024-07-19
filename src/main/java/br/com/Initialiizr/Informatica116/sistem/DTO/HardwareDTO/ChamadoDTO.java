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
public class ChamadoDTO {

    private long id;
    private long sistemaid;
    private String issuetype;
    private String cardId;
    private String titulo;
    private String setor;
    private  String solicitacao;
    private String patrimonio;
    private String equipamento;
    private String erro;
    private String sistem_erro;
    private Status status ;
    private boolean aceito;
    private boolean client_feito;
    private boolean done;
    private String data_chamdo_feito;
    private String datacreate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    String data;
    private boolean ativo;
    private String descricao ;
    private String gmid;
    private String usuario;
    private long tecnicoid ;
    private String cpf;
    private String nome_mae;
    private String data_admin;
    private String data_nasc;
    private  Integer filial;
    private String funcao;
    private String tecnico_responsavel;
    private String tell;
    private String centro_de_custo;
    private List<ImagensDTO> imagens;
    private String emailGestorAprovador;
    private String nomeDogestor;
    private String emailUsuario;

}
