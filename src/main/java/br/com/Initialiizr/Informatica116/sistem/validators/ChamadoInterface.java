package br.com.Initialiizr.Informatica116.sistem.validators;

import br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO.IssueDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
@Component
public interface ChamadoInterface {
   IssueDTO registrar(String dto, MultipartFile[] files);
    IssueDTO Card(String card, long id);


}
