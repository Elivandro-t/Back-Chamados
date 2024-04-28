package br.com.Initialiizr.Informatica116.sistem.Service;

import br.com.Initialiizr.Informatica116.sistem.DTO.SetorDTO;
import br.com.Initialiizr.Informatica116.sistem.Models.Setor;
import br.com.Initialiizr.Informatica116.sistem.repository.SetorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class SetorService {
    @Autowired
    private SetorRepository setorRepository;
    @Autowired
    private ModelMapper modelMapper;
    public SetorDTO registrar(SetorDTO setordto){
        var setor = modelMapper.map(setordto,Setor.class);
        if(setor!=null){
            Optional setor1 = setorRepository.getReferenceByName(setor.getName());
            if(setor1.isPresent()){
                throw new RuntimeException("erro ao criar setor, setor ja criado");
            }
            Setor result = setorRepository.save(setor);
            return  modelMapper.map(result,SetorDTO.class);
        }
        return null;
    }
    public List<SetorDTO> listar(){
        var Setores = setorRepository.findAll().stream().map(e->modelMapper.map(e,SetorDTO.class));
        return Setores.collect(Collectors.toList());
    }
}
