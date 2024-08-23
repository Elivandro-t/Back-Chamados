package br.com.Initialiizr.Informatica116.sistem.Controler;

import br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO.RelatorioDto;
import br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO.StatusOneDTO;
import br.com.Initialiizr.Informatica116.sistem.Models.CHAMADO_HARDWARE.Issue;
import br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO.IssueDTO;
import br.com.Initialiizr.Informatica116.sistem.Service.ChamadoService;
import br.com.Initialiizr.Informatica116.sistem.Service.ChamadoService2;
import br.com.Initialiizr.Informatica116.sistem.validators.MSG;
import br.com.Initialiizr.Informatica116.sistem.validators.Msg;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public ResponseEntity<IssueDTO> chamadoDT(@RequestParam ("data") @Valid String data, @RequestParam(value = "file",required = false) MultipartFile[] file){
        var response = service.registrar(data,file);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/lista/{filial}")
    public Page<IssueDTO> lista(Pageable page,
                                @RequestParam(name = "setor",required = false) String Setor,
                                @RequestParam(name = "dataAntes",required = false) String dataAntes,
                                @RequestParam(name = "dataDepois",required = false) String dataDepois,
                                @RequestParam(name = "ativo",required = false) boolean ativo,
                                @PathVariable int filial){

        return service.Listar(page,Setor,dataAntes,dataDepois,filial,ativo);
    }
    @RequestMapping(method = RequestMethod.POST,value = "/whats")
    public  ResponseEntity<?> apiWhats(@RequestParam ("data") String data){
        return  ResponseEntity.ok(data);
    }
    //Listando todo os dados do usuarios filiais retornado para o service |ListaChamadosFiliais
    @GetMapping("/lista/filiais/cds")
    public Page<IssueDTO> listaChamadosFilias(Pageable page,
                                @RequestParam(name = "setor",required = false) String Setor,
                                @RequestParam(name = "dataAntes",required = false) String dataAntes,
                                @RequestParam(name = "dataDepois",required = false) String dataDepois,
                                @RequestParam(name = "ativo",required = false) boolean ativo){

        return service.ListarChamadosFiliais(page,Setor,dataAntes,dataDepois,ativo);
    }

    @GetMapping("/relatorio")
    public Page<RelatorioDto> relatorio(Pageable page,
                                        @RequestParam(name = "setor",required = false) String Setor,
                                        @RequestParam(name = "dataAntes",required = false) String dataAntes,
                                        @RequestParam(name = "dataDepois",required = false) String dataDepois,
                                        @RequestParam(name = "ativo",required = false) boolean ativo){

        return service.Relatorio(page,Setor,dataAntes,dataDepois,ativo);
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
    @GetMapping("/chamado/usuarioid/{id}")
    public Page<IssueDTO> pegarChamdoID(@PathVariable long id, @PageableDefault(size = 10) Pageable page,@RequestParam(value = "dataAntes",required = false) String dataAntes,
                                        @RequestParam(value = "dataDepois",required = false) String dataDepois,@RequestParam(value = "descricao",required = false)  String descricao,@RequestParam(value = "ativo",required = false)  boolean ativo){
        var response = service.pegarChamadoId(id,page,dataAntes, dataDepois, descricao,ativo);
        return response;
    }
    // pegando chamado por id de usuario por filial
    @GetMapping("/chamado/tecnico/{id}")
    public Page<IssueDTO> pegarChamdoTsc(Pageable page,
                                         @PathVariable long id,
                                         @RequestParam(name = "setor",required = false) String Setor,
                                         @RequestParam(name = "dataAntes",required = false) String dataAntes,
                                         @RequestParam(name = "dataDepois",required = false) String dataDepois,
                                         @RequestParam(name = "ativo",required = false) boolean ativo){
        var response = service2.ListarChamadosTecnico(page,id,Setor,dataAntes,dataDepois,ativo);
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
    public ResponseEntity<?> pegarChmadoAtivo(@PathVariable long id,@RequestBody String data) throws IOException {
        var result = service.update(id,data);
        return result;
    }
    @PutMapping("chamado/concluido/{id}/chamadoCard/{chamadoCard}/{usuariologado}")
    @Transactional
    public  ResponseEntity StatusFeito(@PathVariable long id,@PathVariable("chamadoCard") String chamadoCard,@PathVariable long usuariologado) throws IOException {
        return  service.validaChamado(id,chamadoCard,usuariologado);
    }
    @PutMapping("chamado/validacao/{id}/chamadoCard/{chamadoCard}/fechado/{usuariologado}")
    @Transactional
    public  ResponseEntity StatusValidacao(@PathVariable long id,@PathVariable("chamadoCard") String idchamado,@PathVariable long usuariologado) throws IOException {

        return service.validaChamadoUSer(id,idchamado,usuariologado);

    }
    @PutMapping("chamado/validacao/{id}/chamadoCard/{chamadoCard}/recusado/{usuariologado}")
    @Transactional
    public  ResponseEntity StatusRecusado(@PathVariable long id,@PathVariable("chamadoCard") String idchamado,@PathVariable long usuariologado){

        return service2.validaChamadoRecusado(id,idchamado,usuariologado);

    }
    @PutMapping("chamado/validacao/{id}/chamadoCard/{chamadoCard}/jira/{usuariologado}")
    @Transactional
    public  ResponseEntity StatusJira(@PathVariable long id,@PathVariable("chamadoCard") String idchamado,@PathVariable long usuariologado) throws IOException {

        return service.StatusJira(id,idchamado,usuariologado);
    }
    @PutMapping("chamado/validacao/{id}/chamadoCard/{chamadoCard}/aprovador/{usuariologado}")
    @Transactional
    public  ResponseEntity StatusAprovador(@PathVariable long id,@PathVariable("chamadoCard") String idchamado,@PathVariable long usuariologado) throws IOException {
        return service.StatusAtorizacao(id,idchamado,usuariologado);
    }
    @PutMapping("chamado/guardando-tecnico/{id}/chamadoCard/{chamadoCard}/tec/{usuariologado}")
    @Transactional
    public  ResponseEntity StatusVoltaChamdo(@PathVariable long id,@PathVariable("chamadoCard") String idchamado,@PathVariable long usuariologado) throws IOException {
        return service.ClearTcnico(id,idchamado,usuariologado);
    }

    // api de chamado aberto de validaçao //
    @PutMapping("chamado/validacao/{id}/chamadoCard/{chamadoCard}/aberto/{usuariologado}")
    @Transactional
    public  ResponseEntity StatusFechado(@PathVariable long id,@PathVariable("chamadoCard") String chamadoCard,@PathVariable long usuariologado) throws IOException {

        return service.validaChamadoReaberto(id,chamadoCard,usuariologado);

    }
    @GetMapping("/setor/lista")
    public  ResponseEntity<List<Issue>> setores(){
          var result =  service.pegaStor();
       return ResponseEntity.ok(result);
    }
    @GetMapping("/lista/status/service/{id}/{chamadoCard}")
    public  ResponseEntity<StatusOneDTO> OneStatus(@PathVariable long id, @PathVariable("chamadoCard") String card){
        var result =  service.OneStatus(id,card);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/chamado/delete/{id}")
    public  ResponseEntity<MSG> delete(@PathVariable long id){
        String msg =service2.RemoverChamado(id);
        return ResponseEntity.ok(new MSG(msg));
    }
}
