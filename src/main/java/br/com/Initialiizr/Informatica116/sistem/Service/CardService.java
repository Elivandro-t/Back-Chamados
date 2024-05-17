package br.com.Initialiizr.Informatica116.sistem.Service;
import br.com.Initialiizr.Informatica116.sistem.Models.OPTIONS.Card;
import br.com.Initialiizr.Informatica116.sistem.DTO.OPTIONS_DTO.CardDTO;
import br.com.Initialiizr.Informatica116.sistem.repository.CardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
@Service
public class CardService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CardRepository cardRepository;
    public CardDTO cardRegistro(CardDTO arquivo, MultipartFile file){
        try {
            byte[] bytes = file.getBytes();
            String files = "imagens";
            File path = new File(files);
            String  Filename = file.getOriginalFilename();
            String fil =  files+"/"+Filename;
            if(!path.exists()){
                path.mkdir();
            }
            assert Filename != null;
            Files.write(Paths.get(fil),bytes);
            Card card = modelMapper.map(arquivo,Card.class);
            card.setImg(fil);
          var response =  cardRepository.save(card);
            return modelMapper.map(response,CardDTO.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public ResponseEntity<?> pegarFoto(long id){
        var result = cardRepository.findById(id).orElse(null);
        if(result!=null){
            try {
                byte[] bytes = Files.readAllBytes(Paths.get(result.getImg().toString()));
                return  ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
