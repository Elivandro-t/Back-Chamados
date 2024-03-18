package br.com.Initialiizr.Informatica116.sistem.validators;

import br.com.Initialiizr.Informatica116.sistem.Models.HardwareDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
@Component
public interface ChamadoInterface {
   HardwareDTO registrar(String dto,MultipartFile[] files);
    HardwareDTO Card(String card,long id);


}
