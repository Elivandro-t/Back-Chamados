package br.com.Initialiizr.Informatica116.sistem.Controler;

import br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO.LogChamadoDTO;
import br.com.Initialiizr.Informatica116.sistem.Service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@CrossOrigin(origins = "*")
public class ApiLogControler {
    @Autowired
    private LogService logService;

    @PostMapping("/info")
    @Transactional
    public void logInfo(@RequestBody LogChamadoDTO chamadoDTO) {
        logService.logInfo(chamadoDTO);
    }

    @PostMapping("/error")
    public void logError(@RequestBody LogChamadoDTO chamadoDTO) {
        logService.logError(chamadoDTO);
    }
}
