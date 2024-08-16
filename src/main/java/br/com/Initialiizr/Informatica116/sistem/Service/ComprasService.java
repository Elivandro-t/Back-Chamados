package br.com.Initialiizr.Informatica116.sistem.Service;

import br.com.Initialiizr.Informatica116.sistem.DTO.ComprasObjectDTO;
import br.com.Initialiizr.Informatica116.sistem.Models.Compras.ComprasEServicos;
import br.com.Initialiizr.Informatica116.sistem.repository.CompraRepository;
import br.com.Initialiizr.Informatica116.sistem.repository.ModelsService;
import br.com.Initialiizr.Informatica116.sistem.validators.ValidatorHorasDeAtendimentos;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComprasService  implements ModelsService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private ValidatorHorasDeAtendimentos validatorHorasDeAtendimentos;
    @Override
    public <T> T registrar(ComprasObjectDTO servico) {
        if(servico!=null){
            ComprasEServicos comprasEServicos = modelMapper.map(servico,ComprasEServicos.class);
            comprasEServicos.setHora_aceito(LocalDateTime.now());
            validatorHorasDeAtendimentos.horasDeAtendimento(comprasEServicos);
//            validatorHorasDeAtendimentos.horasEmMinutos(comprasEServicos);

            comprasEServicos.setStatus_andamento("EM ANDAMENTO");
            comprasEServicos.DataCreate(LocalDateTime.now());
            comprasEServicos.setCardId(comprasEServicos.gerarCode());
            comprasEServicos.setAtivo(true);
           compraRepository.save(comprasEServicos);
            return (T) "criado com sucesso";
        }
        throw new RuntimeException("Erro ao enviar dados");
    }

    @Override
    public <T> T atualizar() {
        return null;
    }


    public ComprasObjectDTO  atualizarStatus(long id,String status) {
          Optional<ComprasEServicos> comprasEServicos = compraRepository.findById(id);
          if(comprasEServicos.isPresent()){
             if(status.equals("FECHADO")){
                 throw new RuntimeException("Erro ao atualizar status");
             }
              comprasEServicos.get().setStatus_andamento(status);
            var result = compraRepository.save(comprasEServicos.get());
             return  modelMapper.map(result,ComprasObjectDTO.class);
          }

        return null;
    }

    @Override
    public <T> T excluir() {
        return null;
    }

    public Page  listar(Pageable pageable) {
        List<ComprasObjectDTO> comprasEServicos = compraRepository.findAll(pageable)
                .stream().map(e->modelMapper.map(e,ComprasObjectDTO.class)).collect(Collectors.toList());

        return new PageImpl<> (comprasEServicos);
    }

    @Override
    public <T> T PegarUnicoResgistro() {
        return null;
    }
}
