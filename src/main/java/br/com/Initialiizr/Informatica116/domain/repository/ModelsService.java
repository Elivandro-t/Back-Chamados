package br.com.Initialiizr.Informatica116.domain.repository;

import br.com.Initialiizr.Informatica116.domain.DTO.ComprasObjectDTO;
import org.springframework.data.domain.Pageable;

public interface ModelsService {
    <T> T registrar(ComprasObjectDTO servico);
    <T> T atualizar();
    <T> T excluir();
    <T> T listar(Pageable pageable);
    <T> T PegarUnicoResgistro();
}
