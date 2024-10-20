package br.com.Initialiizr.Informatica116.domain.repository;

import br.com.Initialiizr.Informatica116.domain.Models.OPTIONS.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface SetorRepository extends JpaRepository<Setor,Long> {
    Optional getReferenceByName(String name);
    @Query("SELECT s FROM Setor s ORDER BY s.name asc")
    List<Setor> findAllOrderByDesc();

   Optional<Setor> findOneByName(String setor);
}
