package br.com.Initialiizr.Informatica116.domain.validators;

import br.com.Initialiizr.Informatica116.domain.DTO.HardwareDTO.IssueDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
@Component
public interface ChamadoInterface {
   IssueDTO registrar(String dto, MultipartFile[] files);
    IssueDTO Card(String card, long id);


}
