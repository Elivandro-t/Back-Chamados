package br.com.Initialiizr.Informatica116.sistem.repository;

import br.com.Initialiizr.Informatica116.sistem.Models.Options;
import br.com.Initialiizr.Informatica116.sistem.Models.Sistemas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface SistemasRepository extends JpaRepository<Sistemas,Long> {
    Optional findByName(String name);
@Query("select p from Sistemas p where p.id=:id")
   Sistemas findAllByIdList(long id);

    @Query("select p from Sistemas p left join fetch p.options s where lower(s.name) like lower(concat('%', :name, '%'))")
    List<Sistemas> findAllCardContainingIgnoreCase(String name);

}
