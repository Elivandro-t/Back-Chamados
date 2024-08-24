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

     String url = "https://api.zapdai.dr7brazil.com/message/sendText/elivandro78";
     String apiKey = "9mjTJ46uD6cNDCCY8jbvzyMONBR75Xel";

    public ResponseEntity<String> sendApi(Api msg) throws IOException, InterruptedException {
        // Crie o HttpClient

        JsonObject json = new JsonObject();
        json.addProperty("number","55985284978");
        json.addProperty("text","ola");
        System.out.println(json.toString());
        String jsonString = "{"
                + "\"number\": \"98985720588\","
                + "\"text\": \"te amo\""
                + "}";
        System.out.println(jsonString);
        // Crie o HttpRequest com o corpo JSON e cabeçalhos
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
                .headers("apikey " + apiKey)
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(jsonString))
                .build();

        // Envie a solicitação e obtenha a resposta

        try {
            HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
            System.out.println("Response: " + response.body());
            return ResponseEntity.status(response.statusCode()).body(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}
