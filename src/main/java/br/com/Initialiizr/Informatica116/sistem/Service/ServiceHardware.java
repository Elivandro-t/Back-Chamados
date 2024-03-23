package br.com.Initialiizr.Informatica116.sistem.Service;

import br.com.Initialiizr.Informatica116.sistem.Models.Hardware;
import br.com.Initialiizr.Informatica116.sistem.Models.HardwareDTO;

import br.com.Initialiizr.Informatica116.sistem.Models.Status;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
   ModelMapper modelMapper;
    public Hardware Registro(HardwareDTO hardwareDTO){
        Hardware hardware = modelMapper.map(hardwareDTO,Hardware.class);
        hardware.getItens().forEach(e->{
            e.setStatus((Status.AGUARDANDO_TECNICO));
            e.setHardware(hardware);
            e.Datas(LocalDateTime.now());
            e.setAtivo(true);
            e.setChamadoid("CARD-"+hardware.gerarCode());
        });
        hardware.setServico("hardware");
        return hardware;
    }

    public List<String> multiPart(MultipartFile[] multipartFile) {
       try{
           List<String> imagens = new ArrayList<>();
           if(multipartFile!=null){
               for(MultipartFile file:multipartFile){
                   if(file!=null){
                       String nameFile ="http://localhost:8080/imagens"+"/"+ file.getOriginalFilename();
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
