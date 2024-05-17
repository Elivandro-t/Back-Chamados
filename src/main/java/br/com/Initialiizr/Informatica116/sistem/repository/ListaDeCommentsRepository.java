package br.com.Initialiizr.Informatica116.sistem.repository;

import br.com.Initialiizr.Informatica116.sistem.Models.COMENTARIOS.ListaComments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListaDeCommentsRepository extends JpaRepository<ListaComments,Long> {
}
