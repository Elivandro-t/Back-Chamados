package br.com.Initialiizr.Informatica116.sistem.Controler;

import br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO.LogChamadoDTO;
import br.com.Initialiizr.Informatica116.sistem.Service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
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
    @GetMapping("/logs/{cardId}")
    public Page<LogChamadoDTO> logs(Pageable page, @PathVariable long cardId) {
       return logService.list(page,cardId);
    }
}
