package br.com.Initialiizr.Informatica116.sistem.Service;
import br.com.Initialiizr.Informatica116.sistem.DTO.AUTH_DAO.OptionsSystemaDTo;
import br.com.Initialiizr.Informatica116.sistem.DTO.OPTIONS_DTO.SistemasDTO;
import br.com.Initialiizr.Informatica116.sistem.Models.OPTIONS.Select;
import br.com.Initialiizr.Informatica116.sistem.Models.OPTIONS.Sistemas;
import br.com.Initialiizr.Informatica116.sistem.Security.ConvertJson;
import br.com.Initialiizr.Informatica116.sistem.repository.SistemasRepository;
import br.com.Initialiizr.Informatica116.sistem.validators.MSG;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${endpoint}")
    private String endpoint;
    private static final String UPLOAD_DIR = "/var/lib/data/Logos";

    public SistemasDTO RegistobotesChamados(String data, MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        SistemasDTO optionsSystemaDTo = convertJson.convertJson(data,SistemasDTO.class);
        if(optionsSystemaDTo!=null){
            Sistemas sistemas = modelMapper.map(optionsSystemaDTo, Sistemas.class);
            sistemas.getOptions().forEach(e-> {
                        e.setSistema(sistemas);
                    }
            );
            Optional optional = repository.findByName(sistemas.getName());
            if(optional.isPresent()){
                throw new RuntimeException("ja contem um Registro");
            };
            String name = endpoint+UPLOAD_DIR+"/"+file.getOriginalFilename();
            String imagem = UPLOAD_DIR+"/"+file.getOriginalFilename();
            File pathName = new File(UPLOAD_DIR);
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
        Path path = Paths.get(UPLOAD_DIR).resolve(name);
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
    public  SistemasDTO atualizarBotoes(String name){
        var result = repository.findByNameContainingIgnoreCase(name);
        if(result!=null){
            return modelMapper.map(result,SistemasDTO.class);

        }
       throw new RuntimeException("nada encontrado!");
    }
    public MSG AddBtn(long id,OptionsSystemaDTo data){
        var result = repository.getReferenceById(id);
        var p = modelMapper.map(data,Select.class);
          for(Select opt:result.getOptions()){
              if(opt.getName().equals(p.getName())){
                  throw new RuntimeException("ja existe um card");
              }
          }
        if(data!=null){
            result.getOptions().add(p);
            result.getOptions().forEach(e->e.setSistema(result));
            repository.save(result);
            return new MSG("criado com sucesso");
        }
      return  null;
    }
}
