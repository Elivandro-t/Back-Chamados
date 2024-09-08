package br.com.Initialiizr.Informatica116.sistem.Controler;
import br.com.Initialiizr.Informatica116.sistem.DTO.OPTIONS_DTO.OptionDTO;
import br.com.Initialiizr.Informatica116.sistem.DTO.OPTIONS_DTO.SetorDTO;
import br.com.Initialiizr.Informatica116.sistem.Service.OptionsService;
import br.com.Initialiizr.Informatica116.sistem.Service.SetorService;
import br.com.Initialiizr.Informatica116.sistem.validators.MSG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class OptionsController {
    @Autowired
    private SetorService service;
    @Autowired
    private OptionsService optionsService;
    @PostMapping("setor")
    @Transactional
    public ResponseEntity<MSG> Resgistrar(@RequestBody SetorDTO setor){
        var response = service.registrar(setor);
        return  ResponseEntity.ok().body(response);
    }
    @GetMapping("lista/setor")
    public ResponseEntity<List<SetorDTO>> listar(){
        var lista = service.listar();
        return ResponseEntity.ok(lista);
    }
    //criando e listando perfil;
    @PostMapping("perfil/registro")
    @Transactional
    public ResponseEntity<MSG> ResgistrarOptions(@RequestBody OptionDTO optionDTO){
        var response = optionsService.Registrar(optionDTO);
        return  ResponseEntity.ok().body(response);
    }
    @GetMapping("lista/perfil")
    public ResponseEntity<List<OptionDTO>> listarOptions(){
        var lista = optionsService.list();
        return ResponseEntity.ok(lista);
    }
};