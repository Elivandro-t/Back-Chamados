package br.com.Initialiizr.Informatica116.sistem.Service;

import br.com.Initialiizr.Informatica116.sistem.DTO.OPTIONS_DTO.SetorDTO;
import br.com.Initialiizr.Informatica116.sistem.Models.OPTIONS.Setor;
import br.com.Initialiizr.Informatica116.sistem.repository.SetorRepository;
import br.com.Initialiizr.Informatica116.sistem.validators.MSG;
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
    public MSG registrar(SetorDTO setordto){
        var setor = modelMapper.map(setordto, Setor.class);
        if(setor!=null){
            Optional setor1 = setorRepository.getReferenceByName(setor.getName());
            if(setor1.isPresent()){
                throw new RuntimeException("erro ao criar setor, setor ja criado");
            }
             setorRepository.save(setor);
            return  new MSG("criado com sucesso");
        }
        return null;
    }
    public List<SetorDTO> listar(){
        var Setores = setorRepository.findAllOrderByDesc().stream().map(e->modelMapper.map(e,SetorDTO.class));
        return Setores.collect(Collectors.toList());
    }
}
