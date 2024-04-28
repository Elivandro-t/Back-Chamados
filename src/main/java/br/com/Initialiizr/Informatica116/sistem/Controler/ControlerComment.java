package br.com.Initialiizr.Informatica116.sistem.Controler;

import br.com.Initialiizr.Informatica116.sistem.DTO.CommentsDTO;
import br.com.Initialiizr.Informatica116.sistem.Service.CommetService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@CrossOrigin(origins = "*")
public class ControlerComment {
    @Autowired
    private CommetService commetService;
    @PostMapping
    @Transactional
    public CommentsDTO registrar(@RequestBody CommentsDTO commentsDTO){
        return commetService.comments(commentsDTO);
    }
    @GetMapping("/lista/{chamadoId}")
    public ResponseEntity<CommentsDTO> lista(@PathVariable long chamadoId){
        CommentsDTO commentsDTO = commetService.lista(chamadoId);
        return ResponseEntity.ok(commentsDTO);
    }
}



