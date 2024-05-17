package br.com.Initialiizr.Informatica116.sistem.Models.OPTIONS;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name="setor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Setor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
}
