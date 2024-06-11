package br.com.Initialiizr.Informatica116.sistem.repository;

import br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO.IssueDTO;
import br.com.Initialiizr.Informatica116.sistem.Models.CHAMADO_HARDWARE.Issue;
import io.micrometer.core.instrument.binder.db.MetricsDSLContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface IssueResposoty extends JpaRepository<Issue,Long> {
 @Query("select p from Issue p left join fetch p.itens s where p.usuarioid = :id Order by s.id DESC ")
 List<Issue> findAllByUsuarioidById(long id, Pageable pageable);

 @Query("select p from Issue p left join fetch p.itens s where s.id = :id")
 Issue findOneByIds(long id);

 @Query("select p from Issue p left join fetch p.itens a where p.usuarioid = :id and a.cardId = :card")
 Issue findOneByCard(String card, @Param("id") long id);

 @Query("select p from Issue p join fetch p.itens s where p.id=:id and s.id=:idItens and s.ativo=true")
 Issue findOneByUsuarioidByIdAtivoTrue(@Param("id") long id, long idItens);

 @Query("SELECT h FROM Issue h WHERE h.usuarioid = :usuarioid")
 Issue findOneByUsuarioid(long usuarioid);

 Issue findOneById(long id);
//    @Transactional
//    @Modifying(clearAutomatically = true)
//    @Query("update Issue p set p.id=:id where p.itens=:lista")
//    void atualiza(long id, List<Chamado> itens);
//}

 @Query("select p from Issue p left join fetch  p.itens s where p.usuarioid =:id and s.cardId = :chamadoid")
 Issue findOneByIdChamado(long id, String chamadoid);
   @Query("select p from Issue p left join fetch p.itens s where p.id=:id and s.cardId=:chamadoid")
    Optional<Issue> getReferenceByIdFeito(long id, String chamadoid);
 @Query("select p from Issue p left join fetch p.itens s where s.ativo = true AND  p.filial=:filial Order by s.id DESC")
 Page<Issue> findAllByAtivoTrue(Pageable page, int filial);
@Query("select p from Issue p left join fetch p.itens s where s.datacreate between :dataAntes and :dataDepois and s.ativo=true and p.filial=:filial Order by s.id DESC")
 Page findAllDataAntesAndDataDepoisByAtivoTrue(Pageable page, String dataAntes, String dataDepois, int filial);
 @Query("select p from Issue p left join fetch p.itens s where s.setor =:setor and s.ativo=true")
 Page findAllBySetor(Pageable page, String setor);
 @Query("select p from Issue p left join fetch p.itens s where lower(s.setor)  or lower(s.descricao) like lower(concat('%', :texto, '%'))) like lower(concat('%', :setor, '%')) and s.ativo=true and p.filial=:filial Order by s.id DESC")
 Page findAllBySetorContainingIgnoreCase(Pageable page, String texto, int filial);

 @Query("select p from Issue p left join fetch p.itens s where p.usuarioid =:idusuario and s.status = 'AGUARDANDO_VALIDACAO' Order by s.id DESC")
 Page<Issue> FindAllByHardwareByStatusValidacao(Pageable page,long idusuario);

 @Query("select p from Issue p left join fetch p.itens s where s.ativo = :ativo AND  p.filial=:filial Order by s.id DESC")
 Page findAllByAtivo(Pageable page, int filial, boolean ativo);
 @Query("select p from Issue p left join fetch p.itens s where s.datacreate between :dataAntes and :dataDepois and s.ativo=true and p.usuarioid=:id Order by s.id DESC")
 Page<Issue> findAllDataByUserAtivoTrue(Pageable page,long id, String dataAntes, String dataDepois);

 @Query("select p from Issue p left join fetch p.itens s where lower(s.setor)  or lower(s.descricao) like lower(concat('%', :texto, '%'))) and s.ativo=true and p.usuarioid = :id Order by s.id DESC")

 Page<Issue> findAllByUserContainingIgnoreCase(Pageable pageable, String texto, long id);
 @Query("select p from Issue p left join fetch p.itens s where s.tecnicoid = :id Order by s.id DESC ")

 List<Issue> findAllByUsuarioidByIdTesc(long id, Pageable pageable);
}