package br.com.Initialiizr.Informatica116.sistem.Service;

import br.com.Initialiizr.Informatica116.sistem.Models.OPTIONS.Sistemas;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class BotService extends TelegramLongPollingBot{
    private  String botNames ;
    private String chatId = "1793278602";
public BotService(String botName, String botToken){
    super(botToken);
    this.botNames = botName;
}
    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
    System.out.println("id group "+ message.getChatId());
//
//        if(update.hasMessage() && update.getMessage().hasText()){
//            Message message = update.getMessage();
////
////            log.info("mesagem recebida: {}",message);
////            this.chatId = message.getChatId();
////            var mesagemTest = message.getText();
////
////            log.info("mesagem de dados: {}", message.getText());
////            try {
////                execute(new SendMessage(chatId.toString(),"hello world"));
////            } catch (TelegramApiException e) {
////                throw new RuntimeException(e);
////            }
    }
    public void enviarNotificacaoChamado(String numeroChamado, String nomeUsuario,long UsuarioId,long idChamado,String data,String titulo) {
        String mensagem = "🔴 Novo Chamado Aberto 🔴\n\n";
        mensagem += "Um novo chamado foi aberto pelo usuário *";
        mensagem += nomeUsuario + "*\n\n";
        mensagem += "Detalhes do Chamado:\n";
        mensagem += "- Número:" + numeroChamado + "\n";
        mensagem += "- Categoria: "+ titulo+"\n";
        mensagem += "- Data: "+data+"\n\n";
        mensagem += "- Link: " + "https://back-chamados.onrender.com" + "/chamado/" + numeroChamado + "/" + UsuarioId + "/" + idChamado + "/admin" + "\n\n";
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(mensagem);
        message.enableMarkdown(true); // Habilita o uso de formatação Markdown na mensagem

//        "\uD83D\uDD34\n";

        // Envia a mensagem de forma assíncrona
        CompletableFuture.runAsync(() -> {
            try {
                execute(message);
            } catch (TelegramApiException e) {
                log.error("Erro ao enviar mensagem para o Telegram", e);
            }
        });
    }
    @Override
    public String getBotUsername() {
        return this.botNames;
    }
}
