package br.com.Initialiizr.Informatica116.sistem.Service;

import br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO.IssueDTO;
import br.com.Initialiizr.Informatica116.sistem.repository.IssueResposoty;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ChamadoService2 {
    @Autowired
    private IssueResposoty issueResposoty;
    @Autowired
    private ModelMapper modelMapper;
    public Page<IssueDTO> listaValidados(Pageable page, long idUsuario){
        var name = issueResposoty.FindAllByHardwareByStatusValidacao(page,idUsuario)
                .map(e->modelMapper.map(e, IssueDTO.class));
        return name;
    }
}
