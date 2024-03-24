package br.com.Initialiizr.Informatica116.sistem.Controler;

import br.com.Initialiizr.Informatica116.sistem.Models.HardwareDTO;
import br.com.Initialiizr.Informatica116.sistem.Service.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping
public class ControlerChamado {
    @Autowired
    private ChamadoService service;
    @RequestMapping(method = RequestMethod.POST,value = "chamado")
    public ResponseEntity<HardwareDTO> chamadoDT( @RequestParam ("data") String data, @RequestParam(value = "file",required = false) MultipartFile[] file){
        var response = service.registrar(data,file);
        System.out.println(data);
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/lista")
    public Page<HardwareDTO> lista(Pageable page){
        var response = service.Listar(page);
        return  response;
    }
    @GetMapping("/imagens/{img}")
    public ResponseEntity<Resource> pegarImg(@PathVariable String img){
        var response = service.ListaImagensId(img);
        return  response;
    }
    @GetMapping("/chamado/{id}")
    public Page<HardwareDTO> pegarChamdoID(@PathVariable long id,@PageableDefault(size = 10) Pageable page){
        var response = service.pegarChamadoId(id,page);

        return response;
    }
    @GetMapping("/chamado/unit/{id}")
    public HardwareDTO exibiChamado(@PathVariable long id){
        var response = service.ChamadoId(id);
        return response;
    }
    @GetMapping("/chamado/card/{card}/{id}")
    public HardwareDTO exibiChamado(@PathVariable("card") String card,@PathVariable long id){
        var response = service.Card(card,id);
        return response;
    }
    @PutMapping("/chamado/ativo/{id}")
    @Transactional
    public ResponseEntity<?> pegarChmadoAtivo(@PathVariable long id,@RequestBody String data){
        var result = service.update(id,data);
        return ResponseEntity.ok(result);
    }
    @PutMapping("chamado/concluido/{id}/chamadoCard/{chamadoCard}")
    @Transactional
    public  ResponseEntity StatusFeito(@PathVariable long id,@PathVariable("chamadoCard") String chamadoCard){
        return  service.validaChamado(id,chamadoCard);
    }
    @PutMapping("chamado/validacao/{id}/chamadoCard/{chamadoCard}/fechado")
    @Transactional
    public  ResponseEntity StatusValidacao(@PathVariable long id,@PathVariable("chamadoCard") String idchamado){

        return service.validaChamadoUSer(id,idchamado);

    }
    @PutMapping("chamado/validacao/{id}/chamadoCard/{chamadoCard}/aberto")
    @Transactional
    public  ResponseEntity StatusFechado(@PathVariable long id,@PathVariable("chamadoCard") String chamadoCard){

        return service.validaChamadoReaberto(id,chamadoCard);

    }
}
