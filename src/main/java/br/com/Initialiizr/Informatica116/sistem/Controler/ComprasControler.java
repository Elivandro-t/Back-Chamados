package br.com.Initialiizr.Informatica116.sistem.Controler;

import br.com.Initialiizr.Informatica116.sistem.DTO.ComprasObjectDTO;
import br.com.Initialiizr.Informatica116.sistem.Service.ComprasService;
import br.com.Initialiizr.Informatica116.sistem.repository.ModelsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("compras")
public class ComprasControler  {
    @Autowired
    private ComprasService service;
    @PostMapping
    @Transactional
    public <T> T registrar(@RequestBody @Valid ComprasObjectDTO servico) {
        var compras = service.registrar(servico);
        return (T) compras;
    }
    @PutMapping("/status/{status}/{id}")
    public ComprasObjectDTO atualizar(@PathVariable String status,@PathVariable  long id) {
        var compras = service.atualizarStatus(id,status);
        return compras;
    }

    public <T> T excluir() {
        return null;
    }
    @GetMapping("/lista")
    public Page  listar(@PageableDefault Pageable pageable) {
        var compras = service.listar(pageable);
        return compras;
    }

    public <T> T PegarUnicoResgistro() {
        return null;
    }
}
