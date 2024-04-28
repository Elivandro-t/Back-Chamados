package br.com.Initialiizr.Informatica116.sistem.repository;

import br.com.Initialiizr.Informatica116.sistem.Models.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentsRepository extends JpaRepository<Comments,Long> {
    @Query("select p from Comments p where p.chamadoid=:chamadoid")
    Comments getReferenceByChamadoid(long chamadoid);
    @Query("select p from Comments p where p.chamadoid=:chamadoid")
    Comments findOneByChamadoId(long chamadoid);
}
