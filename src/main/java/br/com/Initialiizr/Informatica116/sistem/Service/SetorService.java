package br.com.Initialiizr.Informatica116.sistem.Service;

import br.com.Initialiizr.Informatica116.sistem.DTO.SetorDTO;
import br.com.Initialiizr.Informatica116.sistem.Models.Setor;
import br.com.Initialiizr.Informatica116.sistem.repository.SetorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SetorService {
    @Autowired
    private SetorRepository setorRepository;
    @Autowired
    private ModelMapper modelMapper;
    public SetorDTO registrar(SetorDTO setordto){
        var setor = modelMapper.map(setordto,Setor.class);
        if(setor!=null){
            Setor result = setorRepository.save(setor);
            return  modelMapper.map(result,SetorDTO.class);
        }
        return null;
    }
    public SetorDTO listar(){
        var Setores = setorRepository.findAll();

        var res = modelMapper.map(Setores,SetorDTO.class);

        return res;
    }
}
