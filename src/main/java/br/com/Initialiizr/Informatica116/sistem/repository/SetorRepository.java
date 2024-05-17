package br.com.Initialiizr.Informatica116.sistem.repository;

import br.com.Initialiizr.Informatica116.sistem.Models.OPTIONS.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface SetorRepository extends JpaRepository<Setor,Long> {
    Optional getReferenceByName(String name);
    @Query("SELECT s FROM Setor s ORDER BY s.name asc")
    List<Setor> findAllOrderByDesc();
}
