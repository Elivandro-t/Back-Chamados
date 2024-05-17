package br.com.Initialiizr.Informatica116.sistem.repository;

import br.com.Initialiizr.Informatica116.sistem.Models.AUTH_USER.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRespository extends JpaRepository<Perfil,Long> {
    Perfil findOneById(long id);
}
