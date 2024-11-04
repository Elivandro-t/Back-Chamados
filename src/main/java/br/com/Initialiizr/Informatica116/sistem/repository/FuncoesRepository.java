package br.com.Initialiizr.Informatica116.sistem.repository;

import br.com.Initialiizr.Informatica116.sistem.Models.AUTH_USER.Funcoes;
import br.com.Initialiizr.Informatica116.sistem.Models.AUTH_USER.User;
import br.com.Initialiizr.Informatica116.sistem.Models.OPTIONS.Sistemas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FuncoesRepository extends JpaRepository<Funcoes,String> {
   @Query("select p from Funcoes p  where p.sistemas = :sistema and p.usuario = :usuario")
    Funcoes findOneBySistemasByUsuario(User usuario, Sistemas sistema);


   @Query("select p from Funcoes p where p.usuario = :usuario")
    List<Funcoes> findAllFuncoesByUsuario(User usuario);

    @Query("select p from Funcoes p where p.sistemas = :sistema and p.usuario = :usuario")

    Funcoes findOndeByUsuarioId(Sistemas sistema, User usuario);
}
