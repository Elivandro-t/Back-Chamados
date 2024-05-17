package br.com.Initialiizr.Informatica116.sistem.Service;

import br.com.Initialiizr.Informatica116.sistem.DTO.OPTIONS_DTO.EquipamentoDTO;
import br.com.Initialiizr.Informatica116.sistem.Models.OPTIONS.Equipamento;
import br.com.Initialiizr.Informatica116.sistem.repository.EquipamentoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class EquipamentoService {
    @Autowired
    EquipamentoRepository equipamentoRepository;
    @Autowired
    ModelMapper modelMapper;
    public EquipamentoDTO registrar(EquipamentoDTO equipamentoDTO){
        var equipamento = modelMapper.map(equipamentoDTO, Equipamento.class);
        if(equipamento!=null){
            Optional setor1 = equipamentoRepository.getReferenceByName(equipamentoDTO.getName());
            if(setor1.isPresent()){
                throw new RuntimeException("erro ao criar nome de equipamento, equipamento ja criado");
            }
            Equipamento result = equipamentoRepository.save(equipamento);
            return  modelMapper.map(result,EquipamentoDTO.class);
        }
        return null;
    }
    public List<EquipamentoDTO> listar(){
        var Setores = equipamentoRepository.findAll().stream().map(e->modelMapper.map(e,EquipamentoDTO.class));
        return Setores.collect(Collectors.toList());
    }
}
