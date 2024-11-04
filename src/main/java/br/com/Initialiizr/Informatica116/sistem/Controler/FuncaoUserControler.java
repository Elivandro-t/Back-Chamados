package br.com.Initialiizr.Informatica116.sistem.Controler;

import br.com.Initialiizr.Informatica116.sistem.DTO.AUTH_DAO.FuncaoAd;
import br.com.Initialiizr.Informatica116.sistem.DTO.AUTH_DAO.FuncaoAtivo;
import br.com.Initialiizr.Informatica116.sistem.DTO.AUTH_DAO.FuncoesDto;
import br.com.Initialiizr.Informatica116.sistem.Service.FuncoUserService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/funcoes")
public class FuncaoUserControler {
   @Autowired
    FuncoUserService funcoUserService;
    @PostMapping("/user/{email}")
    @Transactional
    public ResponseEntity adicionarFuncao(@PathVariable String email,@RequestBody FuncaoAd name){
        try {
            funcoUserService.adicionarFuncaoAoUsuario(email,name);
            return  ResponseEntity.ok().build();

        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body("Erro ao buscar dados do banco "+e.getMessage());
        }
    }
    @GetMapping("/lista/{email}")
    public ResponseEntity<List<FuncoesDto>> listar(@PathVariable String email){
        try {
            System.out.println("meu email "+email);
            return ResponseEntity.ok().body(funcoUserService.lista(email));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("/ativo")
    @Transactional
    public ResponseEntity ativo(@RequestBody FuncaoAtivo ativo){
        try {
            funcoUserService.ativarFuncao(ativo);
            return ResponseEntity.ok().body("ok");
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/remove/{email}/{name}")
    public ResponseEntity remove(@PathVariable("email") String email,@PathVariable("name") String name){
        try {
            funcoUserService.RemoverAcesso(email,name);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}