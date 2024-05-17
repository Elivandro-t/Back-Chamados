package br.com.Initialiizr.Informatica116.sistem.Controler;

import br.com.Initialiizr.Informatica116.sistem.Models.CHAMADO_HARDWARE.Issue;
import br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO.IssueDTO;
import br.com.Initialiizr.Informatica116.sistem.Service.ChamadoService;
import br.com.Initialiizr.Informatica116.sistem.Service.ChamadoService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin(origins = "*")
public class ControlerChamado {
    @Autowired
    private ChamadoService service;
    @Autowired
    private ChamadoService2 service2;

    @RequestMapping(method = RequestMethod.POST,value = "chamado")
    public ResponseEntity<IssueDTO> chamadoDT(@RequestParam ("data") String data, @RequestParam(value = "file",required = false) MultipartFile[] file){
        var response = service.registrar(data,file);
        System.out.println(data);
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/lista/{filial}")
    public Page<IssueDTO> lista(Pageable page, @RequestParam(name = "setor",required = false) String Setor,
                                @RequestParam(name = "dataAntes",required = false) String dataAntes,
                                @RequestParam(name = "dataDepois",required = false)
                                   String dataDepois, @PathVariable int filial){

        System.out.println("data "+Setor);
        return service.Listar(page,Setor,dataAntes,dataDepois,filial);
    }
    @GetMapping("/lista/aguardando/{id}")
    public Page<IssueDTO> listaValidando(@PageableDefault Pageable pageable, @PathVariable long id){
        System.out.println(id);
        return service2.listaValidados(pageable,id);
    }

    @GetMapping("/imagens/{img}")
    public ResponseEntity<Resource> pegarImg(@PathVariable String img){
        var response = service.ListaImagensId(img);
        return  response;
    }
    @GetMapping("/chamado/{id}")
    public Page<IssueDTO> pegarChamdoID(@PathVariable long id, @PageableDefault(size = 10) Pageable page){
        var response = service.pegarChamadoId(id,page);

        return response;
    }
    @GetMapping("/chamado/unit/{id}")
    public IssueDTO exibiChamado(@PathVariable long id){
        var response = service.ChamadoId(id);
        return response;
    }
    @GetMapping("/chamado/card/{card}/{id}")
    public IssueDTO exibiChamado(@PathVariable("card") String card, @PathVariable long id){
        var response = service.Card(card,id);
        return response;
    }
    @PutMapping("/chamado/ativo/{id}")
    @Transactional
    public ResponseEntity<?> pegarChmadoAtivo(@PathVariable long id,@RequestBody String data){
        var result = service.update(id,data);
        return result;
    }
    @PutMapping("chamado/concluido/{id}/chamadoCard/{chamadoCard}/{usuariologado}")
    @Transactional
    public  ResponseEntity StatusFeito(@PathVariable long id,@PathVariable("chamadoCard") String chamadoCard,@PathVariable long usuariologado){
        return  service.validaChamado(id,chamadoCard,usuariologado);
    }
    @PutMapping("chamado/validacao/{id}/chamadoCard/{chamadoCard}/fechado/{usuariologado}")
    @Transactional
    public  ResponseEntity StatusValidacao(@PathVariable long id,@PathVariable("chamadoCard") String idchamado,@PathVariable long usuariologado){

        return service.validaChamadoUSer(id,idchamado,usuariologado);

    }
    @PutMapping("chamado/validacao/{id}/chamadoCard/{chamadoCard}/aberto/{usuariologado}")
    @Transactional
    public  ResponseEntity StatusFechado(@PathVariable long id,@PathVariable("chamadoCard") String chamadoCard,@PathVariable long usuariologado){

        return service.validaChamadoReaberto(id,chamadoCard,usuariologado);

    }
    @GetMapping("/setor/lista")
    public  ResponseEntity<List<Issue>> setores(){
          var result =  service.pegaStor();
       return ResponseEntity.ok(result);
    }
}
