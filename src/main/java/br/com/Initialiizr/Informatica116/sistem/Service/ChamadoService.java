package br.com.Initialiizr.Informatica116.sistem.Service;
import br.com.Initialiizr.Informatica116.sistem.DTO.*;
import br.com.Initialiizr.Informatica116.sistem.Models.*;
import br.com.Initialiizr.Informatica116.sistem.Security.ConvertJson;
import br.com.Initialiizr.Informatica116.sistem.repository.HardwareResposoty;
import br.com.Initialiizr.Informatica116.sistem.validators.ChamadoInterface;
import br.com.Initialiizr.Informatica116.sistem.validators.MSG;
import br.com.Initialiizr.Informatica116.sistem.validators.Msg;
import br.com.Initialiizr.Informatica116.sistem.validators.ValidationsTec;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ChamadoService implements ChamadoInterface {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private HardwareResposoty hardwareRepository;
    @Autowired
    private ConvertJson convertJson;
    @Autowired
    ServiceHardware serviceHardware;
    @Autowired
    ValidationsTec validationsTec;
    //servico de registo de chamado
    @Override
    public HardwareDTO registrar(String DTO, MultipartFile[] files) {
        try{;
            List<String> itens = new ArrayList<>();
            HardwareDTO hardwareDTO = convertJson.convertJson(DTO,HardwareDTO.class);
            Hardware chamado = modelMapper.map(hardwareDTO,Hardware.class);
            chamado.getItens().forEach(e->{
                e.setStatus(Status.AGUARDANDO_TECNICO);
                e.setHardware(chamado);
                e.Datas(LocalDateTime.now());
                e.setAtivo(true);
                e.setChamadoid("CARD-"+chamado.gerarCode());
            });
            chamado.setServico("hardware");
            if(files!=null){
                for(MultipartFile file:files){
                    if(file!=null){
                        String nameFile = "http://localhost:8080/imagens"+"/"+  file.getOriginalFilename();
                        byte[] bytes = file.getBytes();
                        chamado.getItens().forEach(e->e.setImagens(itens));
                       itens.add(nameFile);
                        String pathName = "Img";
                        File path = new File(pathName);
                        String name =pathName+"/"+ file.getOriginalFilename();
                        if(!path.exists()){
                            path.mkdir();
                        }
                        Files.write(Paths.get(name), bytes);
                    }
                }
            }


            var chamadoItens = hardwareRepository.findOneById(hardwareDTO.getId());
            if(chamadoItens!=null) {
                chamadoItens.getItens().addAll(chamado.getItens());
                hardwareRepository.save(chamadoItens);
                return modelMapper.map(chamado, HardwareDTO.class);
            } else {
                Hardware hardwareSalvo = hardwareRepository.save(chamado);
                return modelMapper.map(hardwareSalvo, HardwareDTO.class);
            } }catch (IOException e){
            throw  new RuntimeException(e);
        }
    }
    // pegando chamado por id;
    public HardwareDTO ChamadoId(long id){
        Hardware chamadoid = hardwareRepository.findOneByIds(id);
        if(chamadoid==null){throw new RuntimeException("nada encontrado");}
        HardwareDTO chamado = modelMapper.map(chamadoid,HardwareDTO.class);
        return  chamado;
    }
    // pegando chamado chamado por card
    @Override
    public HardwareDTO Card(String card,long id) {
        Hardware chamadoid = hardwareRepository.findOneByCard(card,id);
        if(chamadoid==null){throw new RuntimeException("nada encontrado");}
        HardwareDTO chamado = modelMapper.map(chamadoid,HardwareDTO.class);
        return  chamado;
    }
    public Page<HardwareDTO> Listar(Pageable page) {
        var dados = hardwareRepository.findAllByAtivoTrue(page)
                        .map(e->modelMapper.map(e,HardwareDTO.class));

        return  dados;
    }
    public ResponseEntity<Resource> ListaImagensId(String name){
        Path path = Paths.get("Img").resolve(name);
        try{
            Resource resource = new UrlResource(path.toUri());
            if(resource.exists()||resource.isReadable()){
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resource);
            }
            return  ResponseEntity.notFound().build();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
    public Page<HardwareDTO> pegarChamadoId(long id,Pageable pageable){
        List<Hardware> lista = hardwareRepository.findAllByUsuarioidById(id,pageable );
        List<HardwareDTO> lis = new ArrayList<>();
        for (Hardware d:lista){
            var map = modelMapper.map(d,HardwareDTO.class);
           lis.add(map);
        }
        return new PageImpl<>( lis);
    }
    public HardwareDTO update(long id,String data){
        UpdateChamado updateChamado = convertJson.convertJson(data,UpdateChamado.class);
        Hardware lista = hardwareRepository.findOneByUsuarioidByIdAtivoTrue(id,updateChamado.id());
        validationsTec.existeTecnico(lista,updateChamado.tecnicoid());
        System.out.println(updateChamado);
        System.out.println(id);
        lista.atualiza(updateChamado);
        lista.getItens().forEach(e->e.setStatus(Status.EM_ANDAMENTO));
        HardwareDTO hardwareDTO = modelMapper.map(lista,HardwareDTO.class);
        System.out.println("update" +updateChamado);
        System.out.println("update banco" +hardwareDTO);
        return hardwareDTO;
    }
    public ResponseEntity validaChamado(long id,String cardChamado){
        Hardware hardware = hardwareRepository.findOneByIdChamado(id,cardChamado);
        if(hardware==null){
            throw new RuntimeException("nada encontrado");
        }
       validationsTec.Valid(hardware);
        validationsTec.Status(hardware);
        hardware.getItens().forEach(e->e.setStatus(Status.AGUARDANDO_VALIDACAO));
        hardware.getItens().forEach(e->e.setAceito(true));
        hardwareRepository.save(hardware);
        return ResponseEntity.ok().body(new MSG("status atualizado"));
    }
    // precisa ser feito validacao para o chamado nao ser aberto
    public ResponseEntity validaChamadoUSer(long id, String cardChamado){
        Hardware hardware = hardwareRepository.findOneByIdChamado(id,cardChamado);
        if(hardware==null){
            throw new RuntimeException("nada encontrado");
        }
        // validacão de tecnico
        validationsTec.Valid(hardware);
        validationsTec.StatusvalidFechado(hardware);
        hardware.getItens().forEach(e->e.setStatus(Status.FECHADO));
        hardware.getItens().forEach(e->e.setAtivo(false));
        hardware.getItens().forEach(e->e.setAceito(false));
        hardware.getItens().forEach(e->e.setClient_feito(true));
        hardware.getItens().forEach(e->e.DataFeito(LocalDateTime.now()));
        hardwareRepository.save(hardware);
        return  ResponseEntity.ok().body(new MSG("status fechado"));
    }
    public ResponseEntity validaChamadoReaberto(long id,String cardChamado){
        Hardware hardware = hardwareRepository.findOneByIdChamado(id,cardChamado);
        if(hardware==null){
            throw new RuntimeException("nada encontrado");
        }
        validationsTec.Valid(hardware);
        // validacão de tecnico
        validationsTec.StatusvalidFechado(hardware);
        hardware.getItens().forEach(e->e.setStatus(Status.RE_ABERTO));
        hardware.getItens().forEach(e->e.setAceito(false));
        hardwareRepository.save(hardware);
        return  ResponseEntity.ok().body(new MSG("chamado reaberto"));
    }

}
