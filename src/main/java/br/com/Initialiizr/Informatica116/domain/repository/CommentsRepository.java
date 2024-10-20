package br.com.Initialiizr.Informatica116.domain.repository;

import br.com.Initialiizr.Informatica116.domain.Models.COMENTARIOS.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<Comments,Long> {
    @Query("select p from Comments p where p.chamadoid=:chamadoid")
    Comments getReferenceByChamadoid(long chamadoid);
    @Query("select p from Comments p where p.chamadoid=:chamadoid")
    Comments findOneByChamadoId(long chamadoid);
}
