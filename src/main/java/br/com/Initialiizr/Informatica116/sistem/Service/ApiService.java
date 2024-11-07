package br.com.Initialiizr.Informatica116.sistem.Service;

import br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO.Api.Api;

import com.google.gson.JsonObject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ApiService {

    private final String url = "https://api.zapdai.dr7brazil.com/message/sendText/elivandro78";
    private final String apiKey = "9mjTJ46uD6cNDCCY8jbvzyMONBR75Xel";

    public ResponseEntity<String> sendApi(Api msg) throws IOException, InterruptedException {
        // Crie o HttpClient

         JsonObject geson = new JsonObject();
         geson.addProperty("number",msg.getNumber());
         geson.addProperty("text",msg.getTextMessage());
        // Crie o HttpRequest com o corpo JSON e cabeçalhos

        // Envie a solicitação e obtenha a resposta


            HttpClient httpClient = HttpClient.newBuilder().build();
            HttpRequest request = HttpRequest.newBuilder()
                     .uri(URI.create(url))
                    .header("apikey", apiKey)
                    .header("Content-Type", "application/json")
                    .POST(BodyPublishers.ofString(geson.toString()))
                    .build();
            HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
            System.out.println("Response: " + response.body());
            System.out.println(geson.toString());
            return ResponseEntity.status(response.statusCode()).body(response.body());
    }

}
