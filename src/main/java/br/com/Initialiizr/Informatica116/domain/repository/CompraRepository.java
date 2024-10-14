package br.com.Initialiizr.Informatica116.domain.repository;

import br.com.Initialiizr.Informatica116.domain.Models.Compras.ComprasEServicos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepository extends JpaRepository<ComprasEServicos,Long> {
}
