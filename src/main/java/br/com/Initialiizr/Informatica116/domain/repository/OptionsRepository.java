package br.com.Initialiizr.Informatica116.domain.repository;

import br.com.Initialiizr.Informatica116.domain.Models.AUTH_USER.OptionsPerfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface OptionsRepository extends JpaRepository<OptionsPerfil,Long> {
    Optional findByName(String name);
}
