package br.com.Initialiizr.Informatica116.sistem.validators.ValidaSetor;

import br.com.Initialiizr.Informatica116.sistem.Models.CHAMADO_HARDWARE.Chamado;
import br.com.Initialiizr.Informatica116.sistem.Models.CHAMADO_HARDWARE.Issue;
import br.com.Initialiizr.Informatica116.sistem.Models.OPTIONS.Setor;
import br.com.Initialiizr.Informatica116.sistem.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class ValidaSetor {
    @Autowired
    private SetorRepository setorRepository;
    public void validaSetor(Issue s){
        for (Chamado c: s.getItens()){
            Optional<Setor> setor  = setorRepository.findOneByName(c.getSetor());
            if(!setor.isPresent()){
                throw new RuntimeException("Busque um setor valido para continuar...");
            }
        }
    }
}
