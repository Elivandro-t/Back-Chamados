package br.com.Initialiizr.Informatica116.sistem.repository;

import br.com.Initialiizr.Informatica116.sistem.DTO.ComprasObjectDTO;
import org.springframework.data.domain.Pageable;

public interface ModelsService {
    <T> T registrar(ComprasObjectDTO servico);
    <T> T atualizar();
    <T> T excluir();
    <T> T listar(Pageable pageable);
    <T> T PegarUnicoResgistro();
}
