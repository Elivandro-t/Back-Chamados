package br.com.Initialiizr.Informatica116.domain.Controler;

import br.com.Initialiizr.Informatica116.domain.DTO.OPTIONS_DTO.EquipamentoDTO;
import br.com.Initialiizr.Informatica116.domain.Service.EquipamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class EquipamentoControler {

    @Autowired
    private EquipamentoService service;
    @PostMapping("equipamentos")
    @Transactional
    public ResponseEntity<EquipamentoDTO> Resgistrar(@RequestBody EquipamentoDTO setor){
        var response = service.registrar(setor);
        return  ResponseEntity.ok().body(response);
    }
    @GetMapping("equipamentos/lista")
    public ResponseEntity<List<EquipamentoDTO>> listar(){
        var lista = service.listar();
        return ResponseEntity.ok(lista);
    }
}
