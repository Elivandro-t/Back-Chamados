package br.com.Initialiizr.Informatica116.sistem.validators;

import br.com.Initialiizr.Informatica116.sistem.Models.CHAMADO_HARDWARE.Issue;
import br.com.Initialiizr.Informatica116.sistem.Service.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FecharChamados {
    @Autowired
    private ChamadoService chamadoService;
    @Autowired
    public  FecharChamados(ChamadoService issue){
        this.chamadoService = issue;
    }
    @Scheduled(fixedDelay = 120000) // Executa a cada 2 minutos (2 * 60 * 1000 milissegundos)
    public void verificarChamadosParaFechamento() {
        // Aqui você chama o método que valida e fecha os chamados automaticamente
        chamadoService.verificarEFecharChamadosAguardandoValidacao();
    }
}
