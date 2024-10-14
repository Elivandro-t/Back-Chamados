package br.com.Initialiizr.Informatica116.domain.Controler;

import br.com.Initialiizr.Informatica116.domain.DTO.HardwareDTO.Api.Api;
import br.com.Initialiizr.Informatica116.domain.Service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/send/zap")
public class ControlerApi {
    @Autowired
    ApiService api;
    @PostMapping()
    @Transactional
    public ResponseEntity<?> apiZap(@RequestBody Api api1) throws IOException, InterruptedException {
        return api.sendApi(api1);
    }
}

