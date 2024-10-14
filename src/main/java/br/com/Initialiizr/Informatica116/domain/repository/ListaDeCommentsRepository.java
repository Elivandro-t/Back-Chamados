package br.com.Initialiizr.Informatica116.domain.repository;

import br.com.Initialiizr.Informatica116.domain.Models.COMENTARIOS.ListaComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListaDeCommentsRepository extends JpaRepository<ListaComments,Long> {
}
