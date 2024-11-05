package br.com.Initialiizr.Informatica116.sistem.repository;

import br.com.Initialiizr.Informatica116.sistem.Models.AUTH_USER.UserActive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
public interface UserActiveRepository extends JpaRepository<UserActive,String> {

//    @Modifying
//    @Transactional
//    @Query("UPDATE UserActive u SET u.timestamp = :lastActivity WHERE u.userName = :username")
//    void updateLastActivity(String username, LocalDateTime lastActivity);

    @Query("SELECT u.timestamp FROM UserActive u WHERE u.userName = :username")
    LocalDateTime getLastActivity(String username);

    UserActive findByUserName(String username);
}
