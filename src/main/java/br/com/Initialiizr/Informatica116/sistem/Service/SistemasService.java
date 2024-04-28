package br.com.Initialiizr.Informatica116.sistem.Service;
import br.com.Initialiizr.Informatica116.sistem.Controler.ControlerEmail;
import br.com.Initialiizr.Informatica116.sistem.Controler.UsuarioControler;
import br.com.Initialiizr.Informatica116.sistem.DTO.SistemasDTO;
import br.com.Initialiizr.Informatica116.sistem.Models.Sistemas;
import br.com.Initialiizr.Informatica116.sistem.Security.ConvertJson;
import br.com.Initialiizr.Informatica116.sistem.repository.SistemasRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
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
import java.util.List;
import java.util.Optional;

@Service
public class SistemasService {
    @Autowired
    private SistemasRepository repository;
    @Autowired
    private ConvertJson convertJson;
    @Autowired
    private ModelMapper modelMapper;
    public SistemasDTO RegistobotesChamados(String data, MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        SistemasDTO optionsSystemaDTo = convertJson.convertJson(data,SistemasDTO.class);
        if(optionsSystemaDTo!=null){
            Sistemas sistemas = modelMapper.map(optionsSystemaDTo,Sistemas.class);
            sistemas.getOptions().forEach(e-> {
                        e.setSistema(sistemas);
                    }
            );
            Optional optional = repository.findByName(sistemas.getName());
            if(optional.isPresent()){
                throw new RuntimeException("ja contem um Registro");
            };
            var path = "sistemBotao";
            String name = "http://localhost:8080/sistemBotao/"+file.getOriginalFilename();
            String imagem = path+"/"+file.getOriginalFilename();
            File pathName = new File(path);
            if(!pathName.exists()){
                pathName.mkdir();
            }
            Files.write(Paths.get(imagem),bytes);
            sistemas.setImagem(name);
            var resultSistema = repository.save(sistemas);
            return modelMapper.map(resultSistema,SistemasDTO.class);
        }
          throw new RuntimeException("dados não podem ser nulos");
    }
    public List<SistemasDTO> search(String name){
        if(name!=null&&!name.trim().isEmpty()){
            var list = repository.findAllCardContainingIgnoreCase(name)
                    .stream().map(e->modelMapper.map(e,SistemasDTO.class)).toList();
            return list;
        }
        return null;
    }
    public List<SistemasDTO> list(){
        var list = repository.findAll()
                .stream().map(e->modelMapper.map(e,SistemasDTO.class)).toList();
        return list;
    }
    public SistemasDTO listById(long id){
        var list = repository.findAllByIdList(id);
        return modelMapper.map(list,SistemasDTO.class);
    }
    public ResponseEntity<Resource> ListaImagensId(String name){
        Path path = Paths.get("sistemBotao").resolve(name);
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
}
