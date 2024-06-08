package br.com.Initialiizr.Informatica116.sistem.DTO.AUTH_DAO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDto {
    private long id;
    private String name;
    private String lastname;
    private String contato;
}
