package br.com.Initialiizr.Informatica116.domain.validators.criacao;

import br.com.Initialiizr.Informatica116.domain.Models.CHAMADO_HARDWARE.Chamado;
import br.com.Initialiizr.Informatica116.domain.Models.CHAMADO_HARDWARE.Issue;
import br.com.Initialiizr.Informatica116.domain.Service.CommetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class EnvioComentarios {
    @Autowired
    private CommetService commetService;
   public  void envio(Issue issue) throws IOException {
       for (Chamado c:issue.getItens()){
//                    botService.enviarNotificacaoChamado(c.getCardId(),chamado.getUsuario_logado(),chamado.getUsuarioid(),c.getId(),c.getDatacreate(),c.getTitulo());
           commetService.EnvioComentarios(c.getId(), "\n" +
                   "✅ Sua solicitação foi recebida com sucesso!\n" +
                   "\n" +
                   "\n" +
                   "\n" +
                   "⛔Para solicitações que requerem aprovação do gestor, caso não sejam aprovadas em até 2 dias, o chamado será cancelado.\n" +
                   "\n" +
                   "\n" +
                   "\n" +
                   "\uD83D\uDCAC Caso precise adicionar informações após abrir o chamado, utilize o campo de comentários para entrar em contato com o nosso time de suporte.");
       }
   }
}
