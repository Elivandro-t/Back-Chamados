package br.com.Initialiizr.Informatica116.sistem.repository;

import br.com.Initialiizr.Informatica116.sistem.Models.CHAMADO_HARDWARE.LogChamado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LogRepository extends JpaRepository<LogChamado, Long> {
    @Query("SELECT p FROM LogChamado p WHERE p.card_id = :cardId ORDER BY p.id DESC")
    Page<LogChamado> findAllByIdLog(Pageable page,long cardId);
}
