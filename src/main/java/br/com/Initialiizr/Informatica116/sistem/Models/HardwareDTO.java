package br.com.Initialiizr.Informatica116.sistem.Models;

import br.com.Initialiizr.Informatica116.sistem.DTO.ChamadoDTO;
import br.com.Initialiizr.Informatica116.sistem.DTO.ImgensDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HardwareDTO {
    private long id;
    private long usuarioid;
    private String servico;
    private List<ChamadoDTO> itens;
    private List<ImgensDto> image ;
    private int TotaldeItens;


}
