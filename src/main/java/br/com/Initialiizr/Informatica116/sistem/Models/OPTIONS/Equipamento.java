package br.com.Initialiizr.Informatica116.sistem.Models.OPTIONS;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "equipamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Equipamento{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
}

