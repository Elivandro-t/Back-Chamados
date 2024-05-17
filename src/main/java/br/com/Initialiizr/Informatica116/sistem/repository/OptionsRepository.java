package br.com.Initialiizr.Informatica116.sistem.repository;

import br.com.Initialiizr.Informatica116.sistem.Models.AUTH_USER.OptionsPerfil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OptionsRepository extends JpaRepository<OptionsPerfil,Long> {
    Optional findByName(String name);
}
