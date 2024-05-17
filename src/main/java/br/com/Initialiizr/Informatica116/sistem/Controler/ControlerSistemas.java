package br.com.Initialiizr.Informatica116.sistem.Controler;

import br.com.Initialiizr.Informatica116.sistem.DTO.AUTH_DAO.OptionsSystemaDTo;
import br.com.Initialiizr.Informatica116.sistem.DTO.OPTIONS_DTO.SistemasDTO;
import br.com.Initialiizr.Informatica116.sistem.Service.SistemasService;
import br.com.Initialiizr.Informatica116.sistem.validators.MSG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping
@CrossOrigin(origins = "*")
public class ControlerSistemas {
    @Autowired
    private SistemasService service;
    @RequestMapping(method = RequestMethod.POST,value = "botoes")
    public ResponseEntity<SistemasDTO> registrar(@RequestParam("data") String data, @RequestParam("file") MultipartFile file) throws IOException {
        var dados = service.RegistobotesChamados(data,file);
        return ResponseEntity.ok().body(dados);
    }
    @GetMapping("/lista/botoes")
    public List<SistemasDTO> listas(){
        return  service.list();
    }
    @GetMapping("/lista/search")
    public List<SistemasDTO> search( @RequestParam(name = "name",required = false) String name){
        return  service.search(name);
    }
    @GetMapping("/sistemBotao/{imagem}")

    public ResponseEntity<Resource>  lista(@PathVariable String imagem){
        return  service.ListaImagensId(imagem);
    }
    @GetMapping("/lista/botoes/itens/{id}")
    public SistemasDTO listaById(@PathVariable long id){
        return  service.listById(id);
    }
    @GetMapping("/lista/btn/{name}")
    public SistemasDTO atualizarBotoes(@PathVariable String name){
        return service.atualizarBotoes(name);
    }
    @PutMapping("/add/btn/{id}")
    @Transactional
    public MSG atualizarBtn(@PathVariable long id,@RequestBody OptionsSystemaDTo data){
        return service.AddBtn(id,data);
    }

}