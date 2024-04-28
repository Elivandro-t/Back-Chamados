package br.com.Initialiizr.Informatica116.sistem.Service;

import br.com.Initialiizr.Informatica116.sistem.DTO.OptionDTO;
import br.com.Initialiizr.Informatica116.sistem.Models.HardwareDTO;
import br.com.Initialiizr.Informatica116.sistem.repository.HardwareResposoty;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ChamadoService2 {
    @Autowired
    private HardwareResposoty hardwareResposoty;
    @Autowired
    private ModelMapper modelMapper;
    public Page<HardwareDTO> listaValidados(Pageable page,long idUsuario){
        var name = hardwareResposoty.FindAllByHardwareByStatusValidacao(page,idUsuario)
                .map(e->modelMapper.map(e,HardwareDTO.class));
        return name;
    }
}
