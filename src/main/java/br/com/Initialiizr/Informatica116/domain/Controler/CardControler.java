package br.com.Initialiizr.Informatica116.domain.Controler;

import br.com.Initialiizr.Informatica116.domain.DTO.OPTIONS_DTO.CardDTO;
import br.com.Initialiizr.Informatica116.domain.Security.ConvertJson;
import br.com.Initialiizr.Informatica116.domain.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@RestController
@RequestMapping
public class CardControler {
    @Autowired
    private ConvertJson convertJson;
    @Autowired
    private CardService cardService;
    @RequestMapping(method = RequestMethod.POST,value = "/card")
    
    public ResponseEntity res(String data, @RequestParam("file")MultipartFile file){
        CardDTO cardDTO = convertJson.convertJson(data,CardDTO.class);
        var result = cardService.cardRegistro(cardDTO,file);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/foto/{id}")
    public ResponseEntity<?> pegarImagensID(@PathVariable long id){
        var result = cardService.pegarFoto(id);
        return ResponseEntity.ok(result);
    }

}
// dados