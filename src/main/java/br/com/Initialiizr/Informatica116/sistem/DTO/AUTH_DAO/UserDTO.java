package br.com.Initialiizr.Informatica116.sistem.DTO.AUTH_DAO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private long id;
    @NotBlank
    private String name;
    @NotBlank
    private  String lastname;
    @NotBlank
    private String setor;
    @NotNull
    private int filial;
    private String codigo;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    private boolean accountLocked;
}
