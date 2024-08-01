package br.com.Initialiizr.Informatica116.sistem.Service;

import br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO.Api.Api;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Service
public class ApiService {

    private final String url = "https://api.zapdai.dr7brazil.com/message/sendText/elivandro78";
    private final String apiKey = "9mjTJ46uD6cNDCCY8jbvzyMONBR75Xel";

    public ResponseEntity<String> sendApi(Api msg) throws IOException, InterruptedException {
        // Crie o HttpClient
        HttpClient httpClient = HttpClient.newBuilder().build();

        // Converta o objeto Api para JSON usando Jackson
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writeValueAsString(msg);

        // Crie o HttpRequest com o corpo JSON e cabeçalhos
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .headers("apikey " + apiKey) // Adicione o cabeçalho Authorization
                .POST(HttpRequest.BodyPublishers.ofString(String.valueOf(msg), StandardCharsets.UTF_8)) // Envia o JSON no corpo
                .build();

        // Envie a solicitação e obtenha a resposta
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Retorne a resposta como um ResponseEntity
        return ResponseEntity.status(response.statusCode())
                .body(response.body());
    }
}
