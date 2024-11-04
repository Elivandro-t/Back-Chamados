package br.com.Initialiizr.Informatica116.sistem.Service;

import br.com.Initialiizr.Informatica116.sistem.Models.CHAMADO_HARDWARE.Issue;
import br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO.IssueDTO;

import br.com.Initialiizr.Informatica116.sistem.Models.Status;
import br.com.Initialiizr.Informatica116.sistem.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceHardware {
    @Value("${endpoint}")
    private String endpoint;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserRepository userRepository;

    public Issue Registro(IssueDTO issueDTO){
        Issue issue = modelMapper.map(issueDTO, Issue.class);
        issue.getItens().forEach(e->{
            e.setStatus((Status.AGUARDANDO_TECNICO));
            e.setIssue(issue);
            e.Datas(LocalDateTime.now());
            e.setAtivo(true);
            e.setCardId("CARD-"+ issue.gerarCode());
        });
        return issue;
    }

    public List<String> multiPart(MultipartFile[] multipartFile) {
       try{
           List<String> imagens = new ArrayList<>();
           if(multipartFile!=null){
               for(MultipartFile file:multipartFile){
                   if(file!=null){
                       String nameFile =endpoint+"imagens"+"/"+ file.getOriginalFilename();
                       byte[] bytes = file.getBytes();
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
           return imagens;
       }catch (IOException e){
           throw  new RuntimeException(e);
       }
    }
}
