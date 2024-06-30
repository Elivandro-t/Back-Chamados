package br.com.Initialiizr.Informatica116.sistem.Service;

import br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO.LogChamadoDTO;
import br.com.Initialiizr.Informatica116.sistem.Models.CHAMADO_HARDWARE.LogChamado;
import br.com.Initialiizr.Informatica116.sistem.repository.LogRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private ModelMapper modelMapper;
 // logs usuario
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

    public Page<LogChamadoDTO> list(Pageable page , long cardID) {
       return logRepository.findAllByIdLog(page,cardID)
               .map(e->modelMapper.map(e,LogChamadoDTO.class));
    }
}
