package br.com.Initialiizr.Informatica116.sistem.repository;

import br.com.Initialiizr.Informatica116.sistem.Models.AUTH_USER.RefreshToken;
import br.com.Initialiizr.Informatica116.sistem.Models.AUTH_USER.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RefreshToeknRepository extends JpaRepository<RefreshToken,Long> {
    void deleteRefreshtokenByUser(User usuario);

    Optional<RefreshToken> findByRefreshtoken(String token);
    @Query("select p from RefreshToken p where p.user=:user")
    RefreshToken findByUser(User user);
}
