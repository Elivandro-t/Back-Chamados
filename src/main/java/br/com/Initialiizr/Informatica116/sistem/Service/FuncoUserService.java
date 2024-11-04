package br.com.Initialiizr.Informatica116.sistem.Service;

import br.com.Initialiizr.Informatica116.sistem.DTO.AUTH_DAO.FuncaoAd;
import br.com.Initialiizr.Informatica116.sistem.DTO.AUTH_DAO.FuncaoAtivo;
import br.com.Initialiizr.Informatica116.sistem.DTO.AUTH_DAO.FuncoesDto;
import br.com.Initialiizr.Informatica116.sistem.Models.AUTH_USER.Funcoes;
import br.com.Initialiizr.Informatica116.sistem.repository.FuncoesRepository;
import br.com.Initialiizr.Informatica116.sistem.repository.SistemasRepository;
import br.com.Initialiizr.Informatica116.sistem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncoUserService {
    @Autowired
    FuncoesRepository funcoesRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SistemasRepository sistemasRepository;

    public  void adicionarFuncaoAoUsuario(String email, FuncaoAd name){
        var usuario = userRepository.findByEmail(email);
       var sistema = sistemasRepository.findOneByName(name.name());
        Funcoes funcoes = new Funcoes(usuario,sistema);
       var sistemaExist = funcoesRepository.findOneBySistemasByUsuario(usuario,sistema);
        if(sistemaExist!=null){
            throw  new RuntimeException("Existe função");
        }
        if(usuario==null){
            throw  new RuntimeException("Usuario invalido!");

        }
        if(sistema==null){
            throw  new RuntimeException("Opção invalida!");

        }
        if(usuario!=null && sistema!=null){
            funcoesRepository.save(funcoes);
        }
    }

    public List<FuncoesDto> lista(String email){
        var usuario = userRepository.findByEmail(email);
        var lista = funcoesRepository.findAllFuncoesByUsuario(usuario);
        if(usuario!=null){
            return lista.stream().map(FuncoesDto::new).toList();
        }else{
            throw new RuntimeException("erro ao buscar dados no banco");

        }
    }

    public void ativarFuncao(FuncaoAtivo ativo) {
        var usuario = userRepository.findByEmail(ativo.email());
        var sistema = sistemasRepository.findOneByName(ativo.name());

       var funcoes = funcoesRepository.findOndeByUsuarioId(sistema,usuario);
       funcoes.ativaFuncao(ativo.ativo());
    }
    public void RemoverAcesso(String email,String name) {
        var usuario = userRepository.findByEmail(email);
        var sistema = sistemasRepository.findOneByName(name);
        var funcoes = funcoesRepository.findOndeByUsuarioId(sistema,usuario);
        funcoesRepository.delete(funcoes);
    }
}
