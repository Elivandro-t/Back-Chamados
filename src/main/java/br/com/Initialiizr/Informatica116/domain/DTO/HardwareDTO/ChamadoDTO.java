package br.com.Initialiizr.Informatica116.domain.DTO.HardwareDTO;
import br.com.Initialiizr.Informatica116.domain.Models.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private long sistemaid;
    private String issuetype;
    private String cardId;
    @NotBlank
    private String titulo;
    @NotBlank
    private String setor;
    @NotBlank
    private  String solicitacao;
    private String patrimonio;
    private String equipamento;
    private String erro;
    private String sistem_erro;
    private Status status ;
    private boolean aceito;
    private boolean client_feito;
    private boolean done;
    private String datacreate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    String data;
    private boolean ativo;
    private String descricao ;
    private String gmid;
    @NotBlank
    private String usuario;
    private long tecnicoid ;
    private String cpf;
    private String data_admin;
    private String data_nasc;
    private  Integer filial;
    private String funcao;
    private String tecnico_responsavel;
    private Integer matricula;
    private String tell;
    private String centro_de_custo;
    private List<ImagensDTO> imagens;
    private String emailGestorAprovador;
    private String nomeDogestor;
    private String emailUsuario;
    private String data_fechado;



}
