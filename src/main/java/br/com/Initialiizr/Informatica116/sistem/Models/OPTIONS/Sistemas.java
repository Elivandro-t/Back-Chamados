package br.com.Initialiizr.Informatica116.sistem.Models.OPTIONS;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Entity
@Table(name ="sistemas" )
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sistemas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String name;
    @NotBlank
    private String titulo;
    @NotBlank
    private String imagem;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "sistema")
    List<Select> options;
}
