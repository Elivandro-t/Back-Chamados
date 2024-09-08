package br.com.Initialiizr.Informatica116.sistem.Controler;

import br.com.Initialiizr.Informatica116.sistem.DTO.COMENTARIOS_DTO.CommentsDTO;
import br.com.Initialiizr.Informatica116.sistem.Service.CommetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping
public class ControlerComment {
    @Autowired
    private CommetService commetService;
    @RequestMapping(method = RequestMethod.POST,value = "comment")
    public CommentsDTO registrar(@RequestParam(value = "data",required = false) String commentsDTO,@RequestParam(value = "file",required = false) MultipartFile[] file) throws IOException {
        return commetService.comments(commentsDTO,file);
    }
    @GetMapping("comment/lista/{chamadoId}")
    public ResponseEntity<CommentsDTO> lista(@PathVariable long chamadoId){
        CommentsDTO commentsDTO = commetService.lista(chamadoId);
        return ResponseEntity.ok(commentsDTO);
    }
}



