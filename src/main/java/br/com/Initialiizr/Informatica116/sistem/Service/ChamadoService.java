package br.com.Initialiizr.Informatica116.sistem.Service;
import br.com.Initialiizr.Informatica116.sistem.DTO.Status;
import br.com.Initialiizr.Informatica116.sistem.DTO.StatusDTO;
import br.com.Initialiizr.Informatica116.sistem.DTO.UpdateChamado;
import br.com.Initialiizr.Informatica116.sistem.Models.Hardware;
import br.com.Initialiizr.Informatica116.sistem.Models.HardwareDTO;
import br.com.Initialiizr.Informatica116.sistem.Models.Imagens;
import br.com.Initialiizr.Informatica116.sistem.Security.ConvertJson;
import br.com.Initialiizr.Informatica116.sistem.repository.HardwareResposoty;
import br.com.Initialiizr.Informatica116.sistem.validators.ChamadoInterface;
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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class ChamadoService implements ChamadoInterface {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private HardwareResposoty hardwareRepository;
    @Autowired
    private ConvertJson convertJson;
    //servico de registo de chamado
    @Override
    public HardwareDTO registrar(String DTO, MultipartFile[] files) {
        try {
            HardwareDTO hardwareDTO = convertJson.convertJson(DTO,HardwareDTO.class);
            Hardware chamado = modelMapper.map(hardwareDTO, Hardware.class);
            chamado.getItens().forEach(e->e.setHardware(chamado));
            chamado.getItens().forEach(e->e.Datas(LocalDateTime.now()));
            chamado.getItens().forEach(e->e.setAtivo(true));
            chamado.getItens().forEach(e->e.setStatus(String.valueOf(Status.EM_ANDAMENTO)));
            chamado.getItens().forEach(e->e.setChamadoid("CARD-"+chamado.gerarCode()));
            chamado.setServico("hardware");
            List<Imagens> imagens = new ArrayList<>();

            for(MultipartFile file:files){
                if(file!=null){

                    String nameFile ="http://localhost:8080/imagens"+"/"+ file.getOriginalFilename();
                    Imagens imagens1 = new Imagens(nameFile);
                    imagens.add(imagens1);
                    byte[] bytes = file.getBytes();
                    String pathName = "Img";
                    File path = new File(pathName);
                    String name =pathName+"/"+ file.getOriginalFilename();

                    chamado.getItens().forEach(e->e.setImage(imagens));
                    System.out.println(imagens1.getImagens());
                    if(!path.exists()){
                        path.mkdir();
                    }
                    Files.write(Paths.get(name), bytes);

                }
            }
            var res = hardwareRepository.findOneById(hardwareDTO.getId());
            if (hardwareDTO == null) {
                throw new RuntimeException("dados em nulo");
            }
            if (res == null) {
                hardwareRepository.save(chamado);
                return modelMapper.map(chamado, HardwareDTO.class);
            } else {
                res.getItens().addAll(chamado.getItens());
                hardwareRepository.save(res);
                return modelMapper.map(chamado, HardwareDTO.class);
            }


        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // pegando chamado por id;
    public HardwareDTO ChamadoId(long id){
        Hardware chamadoid = hardwareRepository.findOneById(id);
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
                var dados = hardwareRepository.findAll(page)
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
        System.out.println(updateChamado);
        System.out.println(id);
        if(lista==null){
            throw  new RuntimeException("nada encontrado");
        }
        lista.atualiza(updateChamado);
        HardwareDTO hardwareDTO = modelMapper.map(lista,HardwareDTO.class);
        System.out.println("update" +updateChamado);
        System.out.println("update banco" +hardwareDTO);
        return hardwareDTO;
    }

}
