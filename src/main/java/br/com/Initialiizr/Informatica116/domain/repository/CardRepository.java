package br.com.Initialiizr.Informatica116.domain.repository;

import br.com.Initialiizr.Informatica116.domain.Models.OPTIONS.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CardRepository extends JpaRepository<Card,Long> {
}
