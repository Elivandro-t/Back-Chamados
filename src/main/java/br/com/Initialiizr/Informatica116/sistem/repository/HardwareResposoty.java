package br.com.Initialiizr.Informatica116.sistem.repository;

import br.com.Initialiizr.Informatica116.sistem.Models.Hardware;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HardwareResposoty extends JpaRepository<Hardware,Long> {
 @Query("select p from Hardware p left join fetch p.itens s where p.usuarioid = :id Order by s.id DESC ")
 List<Hardware> findAllByUsuarioidById(long id, Pageable pageable);

 @Query("select p from Hardware p left join fetch p.itens s where s.id = :id")
 Hardware findOneByIds(long id);

 @Query("select p from Hardware p left join fetch p.itens a where p.usuarioid = :id and a.chamadoid = :card")
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

 @Query("select p from Hardware p left join fetch  p.itens s where p.usuarioid =:id and s.chamadoid = :chamadoid")
 Hardware findOneByIdChamado(long id, String chamadoid);
   @Query("select p from Hardware p left join fetch p.itens s where p.id=:id and s.chamadoid=:chamadoid")
    Optional<Hardware> getReferenceByIdFeito(long id, String chamadoid);
@Query("select p from Hardware p left join fetch p.itens s where s.ativo=true and p.filial=:filial Order by s.data DESC")
 Page findAllByAtivoTrue(Pageable page, int filial);
@Query("select p from Hardware p left join fetch p.itens s where s.data between :dataAntes and :dataDepois and s.ativo=true and p.filial=:filial Order by s.id DESC")
 Page findAllDataAntesAndDataDepoisByAtivoTrue(Pageable page, String dataAntes, String dataDepois, int filial);
 @Query("select p from Hardware p left join fetch p.itens s where s.setor =:setor and s.ativo=true")
 Page findAllBySetor(Pageable page, String setor);
 @Query("select p from Hardware p left join fetch p.itens s where lower(s.setor) like lower(concat('%', :setor, '%')) and s.ativo=true and p.filial=:filial Order by s.id DESC")

 Page findAllBySetorContainingIgnoreCase(Pageable page, String setor, int filial);

     @Query("select p from Hardware p left join fetch p.itens s where p.usuarioid =:idusuario and s.status = 'AGUARDANDO_VALIDACAO' Order by s.id DESC")
    Page FindAllByHardwareByStatusValidacao(Pageable page,long idusuario);
}