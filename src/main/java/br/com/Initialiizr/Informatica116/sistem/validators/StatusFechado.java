package br.com.Initialiizr.Informatica116.sistem.validators;

import br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO.Api.Api;
import br.com.Initialiizr.Informatica116.sistem.Models.CHAMADO_HARDWARE.Chamado;
import br.com.Initialiizr.Informatica116.sistem.Models.CHAMADO_HARDWARE.Issue;
import br.com.Initialiizr.Informatica116.sistem.Models.Status;
import br.com.Initialiizr.Informatica116.sistem.Service.ApiService;
import br.com.Initialiizr.Informatica116.sistem.Service.CommetService;
import br.com.Initialiizr.Informatica116.sistem.repository.IssueResposoty;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StatusFechado {
    @Autowired
    private IssueResposoty chamadoRepository;
    @Autowired
    private CommetService commetService;
    @Autowired
    private ApiService apiService;
    JSONObject items = new JSONObject();
    JSONObject jsonObject = new JSONObject();
    @Scheduled(cron = "0 0/1 * * * *")
    public  void validation() throws IOException, InterruptedException {
        var timer = LocalDateTime.now();
        List<Issue> chamado = chamadoRepository.findAllByAtivoStatusValidacao();
        boolean modify = false;
        for (Issue itensChamados : chamado) {
            for (Chamado c : itensChamados.getItens()) {
                LocalDateTime antesChamado = c.getData_chamdo_feito();
                if (antesChamado != null) {
                    var somaTime = Duration.between(antesChamado, timer).toDays();
                    if (somaTime >= 2) {
                        c.setStatus(Status.FECHADO);
                        c.setAtivo(false);
                        c.setAceito(false);
                        c.setClient_feito(true);
                        c.setDone(true);
                        c.setData_chamdo_feito(null);
                        c.DataFeito(LocalDateTime.now());
                        modify = true;
                        EnvioComentarios(itensChamados, c);
                        zap(itensChamados,c);
                        // Exibindo o JSON como String
                    }
                }
                if (modify == true) {
                    try {
                        chamadoRepository.save(itensChamados); // Salva a Issue
                    } catch (Exception e) {
                        e.printStackTrace(); // Trate erros de persistência
                    }
                }
            }
        }


    };
    private void EnvioComentarios(Issue itensChamados, Chamado c) throws IOException {
        jsonObject.put("chamadoid", c.getId());
        items.put("usuario", "Resposta automática\n");
        items.put("email", "suporte.dev18@gmail.com");
        items.put("userImagem", "https://back-chamados.onrender.com/Logos/assistente.jpeg");
        items.put("comments", "Olá, "  + "," + "\n" +
                "\n" +
                "Devido a falta de validação no período de 2 dias, o chamado foi encerrado.\n" +
                "\n" +
                "Abraços,\n" +
                "Suporte de TI");
        JSONArray itensArray = new JSONArray();
        itensArray.put(items);
        jsonObject.put("itens", itensArray);
        String jsonString = jsonObject.toString(4); // 4 é a indentação para uma visualização mais legível do json exibido
        commetService.comments(jsonString, null);
    }
    private void zap(Issue name,Chamado c) throws IOException, InterruptedException {
       if (name.getContato()!=null){
           String msg = "Olá," + name.getUsuario().getName()+"! \uD83D\uDC4B\n" +
                   "\n" +
                   "Esta é uma resposta automática. Devido à falta de validação, o status do seu chamado **"+c.getCardId()+"** foi alterado para **fechado** após o período de 2 dias.\n" +
                   "\n" +
                   "Detalhes do Chamado:\n" +
                   "Card: "+"**"+c.getCardId()+"**"+"\n" +
                   "Data: "+"**"+c.getData_chamdo_feito()+"**"+"\n"  +
                   "Técnico Responsável: "+"**"+c.getTecnico_responsavel()+"**"+"\n" +
                   "\n" +
                   "Caso o problema não tenha sido resolvido, pedimos que abra uma nova solicitação.\n" +
                   "\n" +
                   "Atenciosamente,  \n" +
                   "Suporte TI ⚙\uFE0F\n";
           Api api = new Api("55"+name.getContato(),msg);
           apiService.sendApi(api);
       }

    }

}
