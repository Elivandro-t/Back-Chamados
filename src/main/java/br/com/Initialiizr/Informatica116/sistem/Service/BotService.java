//package br.com.Initialiizr.Informatica116.sistem.Service;
//
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.api.objects.Message;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//
//import java.util.List;
//import java.util.concurrent.CompletableFuture;
//
//public class BotService extends TelegramLongPollingBot{
//    private  String botNames ;
//    private String chatId = "1793278602";
//    private String key = "-1002229122839";
//    public BotService(String botName, String botToken){
//    super(botToken);
//    this.botNames = botName;
//}
//    @Override
//    public void onUpdateReceived(Update update) {
//         Message message = update.getMessage();
//    }
//    public void enviarNotificacaoChamado(String numeroChamado, String nomeUsuario,long UsuarioId,long idChamado,String data,String titulo) {
//        String mensagem = "🔴 Alerta Chamado Aberto 🔴\n\n";
//        mensagem += "Um novo chamado foi aberto pelo usuário *";
//        mensagem += nomeUsuario + "*\n\n";
//        mensagem += "Detalhes do Chamado:\n";
//        mensagem += "- Número: " + numeroChamado + "\n";
//        mensagem += "- Categoria: "+ titulo+"\n";
//        mensagem += "- Data: "+data+"\n\n";
//        mensagem += "- Link: " + "https://suporteinformatic.com.br" + "/chamado/" + numeroChamado + "/" + UsuarioId + "/" + idChamado + "/admin" + "\n\n";
//        SendMessage message = new SendMessage();
//        message.setChatId(key);
//        message.setText(mensagem);
//        message.enableMarkdown(true); // Habilita o uso de formatação Markdown na mensagem
//
//        // Envia a mensagem de forma assíncrona
//        CompletableFuture.runAsync(() -> {
//            try {
//                execute(message);
//            } catch (TelegramApiException e) {
//            }
//        });
//    }
//    @Override
//    public String getBotUsername() {
//        return this.botNames;
//    }
//}
