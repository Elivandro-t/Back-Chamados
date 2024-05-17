package br.com.Initialiizr.Informatica116.sistem.repository;

import br.com.Initialiizr.Informatica116.sistem.Models.AUTH_USER.RefreshToken;
import br.com.Initialiizr.Informatica116.sistem.Models.AUTH_USER.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RefreshToeknRepository extends JpaRepository<RefreshToken,Long> {
    int deleteRefreshtokenByUser(User usuario);

    Optional<RefreshToken> findByRefreshtoken(String token);
    @Query("select p from RefreshToken p where p.user=:user")

    RefreshToken findByUser(User user);
}
