package br.com.Initialiizr.Informatica116.sistem.Service;

import br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO.LogChamadoDTO;
import br.com.Initialiizr.Informatica116.sistem.Models.CHAMADO_HARDWARE.LogChamado;
import br.com.Initialiizr.Informatica116.sistem.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {
    @Autowired
    private LogRepository logRepository;

    public void log(LogChamadoDTO logChamadoDTO) {
        LogChamado log = new LogChamado( logChamadoDTO);
        logRepository.save(log);
    }

    public void logInfo(LogChamadoDTO chamadoDTO) {
        log( chamadoDTO);
    }

    public void logError(LogChamadoDTO chamadoDTO) {
        log(chamadoDTO);
    }
}
