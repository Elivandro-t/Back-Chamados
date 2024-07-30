package br.com.Initialiizr.Informatica116.sistem.repository;

import br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO.IssueDTO;
import br.com.Initialiizr.Informatica116.sistem.Models.CHAMADO_HARDWARE.Issue;
import br.com.Initialiizr.Informatica116.sistem.Models.Status;
import io.micrometer.core.instrument.binder.db.MetricsDSLContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
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

// @Query("SELECT h FROM Issue h WHERE h.usuarioid = :usuarioid")
// Issue findOneByUsuarioid(long usuarioid);

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
@Query("select p from Issue p left join fetch p.itens s where s.datacreate between :dataAntes and :dataDepois and s.ativo=:ativo and p.filial=:filial Order by s.id DESC")
 Page findAllDataAntesAndDataDepoisByAtivoTrue(Pageable page, String dataAntes, String dataDepois, int filial,boolean ativo);
 @Query("select p from Issue p left join fetch p.itens s where s.setor =:setor and s.ativo=true")
 Page findAllBySetor(Pageable page, String setor);
 @Query("select p from Issue p left join fetch p.itens s where lower(s.setor) like lower(concat('%', :setor, '%')) and s.ativo=true and p.filial=:filial Order by s.id DESC")
 Page findAllBySetorContainingIgnoreCase(Pageable page, String setor, int filial);

 @Query("select p from Issue p left join fetch p.itens s where p.usuarioid =:idusuario and s.status = 'AGUARDANDO_VALIDACAO' Order by s.id DESC")
 Page<Issue> FindAllByHardwareByStatusValidacao(Pageable page,long idusuario);

 @Query("select p from Issue p left join fetch p.itens s where s.ativo = :ativo AND  p.filial=:filial Order by case when s.status = 'AGUARDANDO_TECNICO' then 1 else 2 end,s.id DESC")
 Page findAllByAtivo(Pageable page, int filial, boolean ativo);
 @Query("select p from Issue p left join fetch p.itens s where s.datacreate between :dataAntes and :dataDepois and s.ativo=true and p.usuarioid=:id Order by s.id DESC")
 Page<Issue> findAllDataByUserAtivoTrue(Pageable page,long id, String dataAntes, String dataDepois);


 @Query("select p from Issue p left join fetch p.itens s where s.datacreate between :dataAntes and :dataDepois and s.ativo=true and p.filial=:filial Order by s.setor ASC")

 Page findAllDataAntesAndDataDepoisByAtivoTruefilter(Pageable page, String dataAntes, String dataDepois, int filial);
 @Query("select p from Issue p left join fetch p.itens s where s.ativo = :ativo and p.filial=:filial Order by s.setor ASC")

 Page findAllByAtivoT(Pageable page, int filial, boolean ativo);

 @Query("select p from Issue p left join fetch p.itens s where s.datacreate between :dataAntes and :dataDepois and s.ativo= :ativo Order by s.id DESC")
 Page findAllDataAntesAndDataDepoisByAtivoTrueAndFalse(Pageable page, String dataAntes, String dataDepois, boolean ativo);

// @Query("select p from Issue p left join fetch p.itens s " +
//         "where lower(s.setor) like lower(concat('%', :setor, '%')) " +
//         "and s.ativo = :ativo " +
//         "Order by s.id DESC")
@Query("select p from Issue p left join fetch p.itens s " +
        "where (:busca is null or lower(s.setor) like lower(concat('%', :busca, '%')) " +
        "or lower(s.cardId) like lower(concat('%', :busca, '%'))) " +
        "and s.ativo = :ativo " +
        "Order by s.id DESC")
 Page findAllBySetorContainingIgnoreCaseTrueAndFalse(Pageable page,@Param("busca")String busca,@Param("ativo") boolean ativo);
 @Query("select p from Issue p left join fetch p.itens s " +
         "where (:busca is null or lower(s.setor) like lower(concat('%', :busca, '%')) " +
         "or lower(s.cardId) like lower(concat('%', :busca, '%'))) " +
         "and s.ativo = true " +
         "and p.filial = :filial " +
         "Order by s.id DESC")
 Page findAllBySetorContainingIgnoreCaseBusca(Pageable page,@Param("busca") String busca, int filial);

 @Query("select p from Issue p left join fetch p.itens s where s.ativo = :ativo Order by case when s.status = 'AGUARDANDO_TECNICO' then 1 else 2 end,s.id DESC")
 Page findAllByAtivoTrueAndFalse(Pageable page, boolean ativo);
 @Query("select p from Issue p left join fetch p.itens s where s.ativo = true AND  s.status=:status Order by s.id DESC")

 List<Issue> findByStatusAndDataInicioEsperaBefore(Status status);
 //buscando status do usuario que foi fechado

// @Query("select p from Issue p left join fetch p.itens s " +
//         "where p.usuarioid = :id and (:busca is null or lower(s.setor) like lower(concat('%', :busca, '%')) " +
//         "or lower(s.cardId) like lower(concat('%', :busca, '%')) ) " +
//         "or lower(s.status) like lower(concat('%', :busca, '%'))  ) " +
//         "and s.ativo = :ativo " +
//         "order by s.id DESC")
@Query("select p from Issue p left join fetch p.itens s " +
        "where p.usuarioid = :id " +
        "and (:busca is null or " +
        "    lower(s.setor) like lower(concat('%', :busca, '%')) " +
        "    or lower(s.cardId) like lower(concat('%', :busca, '%')) " +
        "    or lower(s.status) like lower(concat('%', :busca, '%')) " +
        "    or lower(s.tecnico_responsavel) like lower(concat('%', :busca, '%')) " +
        ") " +
        "and s.ativo = :ativo " +
        "order by s.id DESC")
 Page findAllBySetorContainingIgnoreCaseTrueAndFalseByUsuario(Pageable pageable, long id,@Param("busca") String busca, boolean ativo);
 @Query("select p from Issue p left join fetch p.itens s where p.usuarioid=:id and s.ativo = :ativo Order by s.id DESC")
 Page findAllByAtivoTrueAndFalseByUsuario(Pageable pageable, long id, boolean ativo);


 @Query("select p from Issue p left join fetch p.itens s where s.datacreate between :dataAntes and :dataDepois and s.ativo= :ativo and s.tecnicoid = :idTecnico Order by s.id DESC")
 Page findAllDataAntesAndDataDepoisByAtivoTrueAndFalseTec(Pageable page, String dataAntes, String dataDepois, long idTecnico, boolean ativo);

 @Query("select p from Issue p left join fetch p.itens s " +
         "where (:busca is null or lower(s.setor) like lower(concat('%', :busca, '%')) " +
         "or lower(s.cardId) like lower(concat('%', :busca, '%'))) " +
         "and s.ativo = :ativo " +
         "and s.tecnicoid = :idTecnico "+
         "order by s.id DESC")
 Page findAllBySetorContainingIgnoreCaseTrueAndFalseAndTec(Pageable page, long idTecnico,@Param("busca") String busca, boolean ativo);

 @Query("select p from Issue p left join fetch p.itens s where s.ativo = :ativo and s.tecnicoid=:idTecnico Order by case when s.status = 'EM_ANDAMENTO' then 1 else 2 end,s.id DESC")
 Page findAllByAtivoTrueAndFalseAndTecnico(Pageable page, long idTecnico, boolean ativo);
}

//
//@Query("select p from Issue p left join fetch p.itens s where s.tecnicoid = :id Order by s.id DESC ")
//
//List<Issue> findAllByUsuarioidByIdTesc(long id, Pageable pageable);