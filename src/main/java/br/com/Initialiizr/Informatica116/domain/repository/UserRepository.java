package br.com.Initialiizr.Informatica116.domain.repository;

import br.com.Initialiizr.Informatica116.domain.Models.AUTH_USER.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("select s from User s where s.email = :email")
    Optional pegandoUsuarioExistente(String email);

    User findByEmail(String email);
    @Query("select s from User s where s.id = :id")
    User getReferenceById(long id);

}
