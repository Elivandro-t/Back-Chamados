package br.com.Initialiizr.Informatica116.sistem.repository;

import br.com.Initialiizr.Informatica116.sistem.Models.OPTIONS.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EquipamentoRepository extends JpaRepository<Equipamento,Long> {
    Optional getReferenceByName(String name);
}
