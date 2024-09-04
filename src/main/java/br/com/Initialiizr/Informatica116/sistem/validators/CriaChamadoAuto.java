package br.com.Initialiizr.Informatica116.sistem.validators;

import br.com.Initialiizr.Informatica116.sistem.Service.ChamadoService;
import br.com.Initialiizr.Informatica116.sistem.repository.IssueResposoty;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class CriaChamadoAuto {
    @Autowired
  private ChamadoService chamadoService;
    @Autowired
    private IssueResposoty issueResposoty;
    private boolean chamado = false;
    @Scheduled(cron = "0 0 9,14 * * *")
    public void CriaChamado(){
        try {
           if(chamado) return;
            JSONObject object = new JSONObject();
            JSONObject itens = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            object.put("usuarioid", 173);
            object.put("filial", 116);
            object.put("usuario_logado", "Suporte Automatico");
            object.put("servico", "Hardware");
            itens.put("usuario", "Suporte Automatico");
            itens.put("issuetype","Hardware");
            itens.put("sistemaid",1);
            itens.put("equipamento","IMPRESSORAS");
            itens.put("setor", "INFORMATICA");
            itens.put("titulo", "Hardware");
            itens.put("patrimonio","00000");
            itens.put("solicitacao", "Verificação de impressoras");
            itens.put("descricao", "<b>Aviso de Manutenção - Impressoras</b><br>" +
                    "<br>" +
                    "Olá,<br>" +
                    "<br>" +
                    "Este é um aviso automático para informar que as impressoras das cidades e da externa precisam ser verificadas. Por favor, tome as medidas necessárias para garantir que estejam funcionando corretamente.<br>" +
                    "<br>" +
                    "Obrigado pela atenção!<br>" +
                    "<br>" +
                    "<strong>Atenciosamente</strong>,");
        jsonArray.put(itens);
        object.put("itens", jsonArray);
        String jsonString = object.toString(4);
            var Hora = LocalDateTime.now();
            var manha = Hora.getHour() == 9;
            var tarde = Hora.getHour() == 14;
                if (manha||tarde) {
                    chamadoService.registrar(jsonString, null);
                    chamado = true;
                }
        }catch (RuntimeException e){

            }

    }
}
