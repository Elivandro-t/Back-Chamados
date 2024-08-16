package br.com.Initialiizr.Informatica116.sistem.repository;

import br.com.Initialiizr.Informatica116.sistem.Models.Compras.ComprasEServicos;
import br.com.Initialiizr.Informatica116.sistem.Service.ComprasService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepository extends JpaRepository<ComprasEServicos,Long> {
}
