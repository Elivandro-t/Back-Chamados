package br.com.Initialiizr.Informatica116.sistem.Controler;
import br.com.Initialiizr.Informatica116.sistem.DTO.SetorDTO;
import br.com.Initialiizr.Informatica116.sistem.Service.SetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping
public class SetorController {
    @Autowired
    private SetorService service;
    @PostMapping("setor")
    @Transactional
    public ResponseEntity<SetorDTO> Resgistrar(@RequestBody SetorDTO setor){
        var response = service.registrar(setor);
        return  ResponseEntity.ok().body(response);
    }
    @GetMapping("/lista/setor")
    public SetorDTO listar(){
        var lista = service.listar();
        return lista;
    }
};