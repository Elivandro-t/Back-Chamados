package br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO.Api;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MsgApi {
    @NotBlank
    private String txt;
}
