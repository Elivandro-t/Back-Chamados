package br.com.Initialiizr.Informatica116.sistem.repository;

import br.com.Initialiizr.Informatica116.sistem.Models.Hardware;
import br.com.Initialiizr.Informatica116.sistem.Models.Status;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface HardwareResposoty extends JpaRepository<Hardware,Long> {
 @Query("select p from Hardware p where p.usuarioid = :id")
 List<Hardware> findAllByUsuarioidById(long id, Pageable pageable);

 @Query("select p from Hardware p left join fetch p.itens s where s.id = :id")
 Hardware findOneByIds(long id);

 @Query("select p from Hardware p left join fetch p.itens a where p.id = :id and a.chamadoid = :card")
 Hardware findOneByCard(String card, @Param("id") long id);

 @Query("select p from Hardware p join fetch p.itens s where p.id=:id and s.id=:idItens and s.ativo=true")
 Hardware findOneByUsuarioidByIdAtivoTrue(@Param("id") long id, long idItens);

 @Query("SELECT h FROM Hardware h WHERE h.usuarioid = :usuarioid")
 Hardware findOneByUsuarioid(long usuarioid);

 Hardware findOneById(long id);
//    @Transactional
//    @Modifying(clearAutomatically = true)
//    @Query("update Hardware p set p.id=:id where p.itens=:lista")
//    void atualiza(long id, List<Chamado> itens);
//}

 @Query("select p from Hardware p left join fetch  p.itens s where p.usuarioid = :id and s.chamadoid = :chamadoid")
 Hardware findOneByIdChamado(long id, String chamadoid);
   @Query("select p from Hardware p left join fetch p.itens s where p.id=:id and s.chamadoid=:chamadoid")
    Optional<Hardware> getReferenceByIdFeito(long id, String chamadoid);
@Query("select p from Hardware p left join fetch p.itens s where s.ativo=true")
 Page findAllByAtivoTrue(Pageable page);
}