package br.com.Initialiizr.Informatica116.sistem.Controler;

import br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO.Api.Api;
import br.com.Initialiizr.Informatica116.sistem.Service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.crypto.MacSpi;
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

