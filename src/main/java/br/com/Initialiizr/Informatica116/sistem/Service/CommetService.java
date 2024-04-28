package br.com.Initialiizr.Informatica116.sistem.Service;

import br.com.Initialiizr.Informatica116.sistem.DTO.CommentsDTO;
import br.com.Initialiizr.Informatica116.sistem.DTO.ListaComentdDTO;
import br.com.Initialiizr.Informatica116.sistem.Models.Comments;
import br.com.Initialiizr.Informatica116.sistem.Models.ListaComments;
import br.com.Initialiizr.Informatica116.sistem.repository.CommentsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommetService {
    @Autowired
    private ModelMapper  modelMapper;
    @Autowired
    private CommentsRepository commentsRepository;
    public CommentsDTO comments(CommentsDTO commentsDTO){

        Comments comment  = modelMapper.map(commentsDTO,Comments.class);
          comment.getItens().forEach(e->e.setComment(comment));
           var comments = commentsRepository.getReferenceByChamadoid(comment.getChamadoid());

           if(comments!=null){
               comments.getItens().addAll(comment.getItens());
               comments.getItens().forEach(e->e.setComment(comments));
               var ol = commentsRepository.save(comments);
               return modelMapper.map(ol, CommentsDTO.class);
           }
           else{
               var name = commentsRepository.save(comment);
               return modelMapper.map(name,CommentsDTO.class);
           }
    }
    public  CommentsDTO lista(long chamadoId){
        Comments comments = commentsRepository.findOneByChamadoId(chamadoId);
        return modelMapper.map(comments,CommentsDTO.class);
    }
}
