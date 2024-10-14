package br.com.Initialiizr.Informatica116.domain.repository;

import br.com.Initialiizr.Informatica116.domain.Models.OPTIONS.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamento,Long> {
    Optional getReferenceByName(String name);
}
