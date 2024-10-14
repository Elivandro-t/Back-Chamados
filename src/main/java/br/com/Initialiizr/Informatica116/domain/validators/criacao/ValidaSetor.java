package br.com.Initialiizr.Informatica116.domain.validators.criacao;

import br.com.Initialiizr.Informatica116.domain.Models.CHAMADO_HARDWARE.Chamado;
import br.com.Initialiizr.Informatica116.domain.Models.CHAMADO_HARDWARE.Issue;
import br.com.Initialiizr.Informatica116.domain.Models.OPTIONS.Setor;
import br.com.Initialiizr.Informatica116.domain.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class ValidaSetor implements CriacaoValidacao{
    @Autowired
    private SetorRepository setorRepository;
    public void valid(Issue s){
        for (Chamado c: s.getItens()){
            Optional<Setor> setor  = setorRepository.findOneByName(c.getSetor());
            if(!setor.isPresent()){
                throw new RuntimeException("Busque um setor valido para continuar...");
            }
        }
    }
}
