package br.com.Initialiizr.Informatica116.sistem.Service;

import br.com.Initialiizr.Informatica116.sistem.DTO.OPTIONS_DTO.OptionDTO;
import br.com.Initialiizr.Informatica116.sistem.Models.AUTH_USER.OptionsPerfil;
import br.com.Initialiizr.Informatica116.sistem.repository.OptionsRepository;
import br.com.Initialiizr.Informatica116.sistem.validators.MSG;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OptionsService {
    @Autowired
    private OptionsRepository optionsRepository;
    @Autowired
    private ModelMapper modelMapper;
    public  MSG Registrar(OptionDTO perfilDTo){
        Optional option = optionsRepository.findByName(perfilDTo.getName());

        if(option.isPresent()){
            throw new RuntimeException("perfil ja existe");
        }
        if(perfilDTo!=null){
            OptionsPerfil perfil = modelMapper.map(perfilDTo,OptionsPerfil.class);
            optionsRepository.save(perfil);
            return new MSG("criado com sucesso!");
        }
        return new MSG("erro ao criar perfil");
    }
    public List<OptionDTO> list(){
        var  perfil = optionsRepository.findAll()
                .stream().map(e->modelMapper.map(e,OptionDTO.class)).toList();
        return  perfil;

    }

}
