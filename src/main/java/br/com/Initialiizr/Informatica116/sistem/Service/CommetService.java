package br.com.Initialiizr.Informatica116.sistem.Service;

import br.com.Initialiizr.Informatica116.sistem.DTO.COMENTARIOS_DTO.CommentsDTO;
import br.com.Initialiizr.Informatica116.sistem.DTO.COMENTARIOS_DTO.CommentsIMGDTO;
import br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO.ImagensDTO;
import br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO.IssueDTO;
import br.com.Initialiizr.Informatica116.sistem.Models.CHAMADO_HARDWARE.Chamado;
import br.com.Initialiizr.Informatica116.sistem.Models.CHAMADO_HARDWARE.Imagens;
import br.com.Initialiizr.Informatica116.sistem.Models.CHAMADO_HARDWARE.Issue;
import br.com.Initialiizr.Informatica116.sistem.Models.COMENTARIOS.Comments;
import br.com.Initialiizr.Informatica116.sistem.Models.COMENTARIOS.ImagensComments;
import br.com.Initialiizr.Informatica116.sistem.Models.COMENTARIOS.ListaComments;
import br.com.Initialiizr.Informatica116.sistem.Security.ConvertJson;
import br.com.Initialiizr.Informatica116.sistem.repository.CommentsRepository;
import org.json.JSONArray;
import org.json.JSONObject;
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
public class CommetService {
    @Autowired
    private ModelMapper  modelMapper;
    @Autowired
    private CommentsRepository commentsRepository;
    @Autowired
    private ConvertJson convertJson;
    @Value("${endpoint}")
    private String endpoint;
    private static final String UPLOAD_DIR = "/var/lib/data/Logos";
    JSONObject items = new JSONObject();
    JSONObject jsonObject = new JSONObject();

    public CommentsDTO comments(String commentsDTO, MultipartFile[] files) throws IOException {
        CommentsDTO data = convertJson.convertJson(commentsDTO, CommentsDTO.class);

        List<ImagensComments> itens = new ArrayList<>();
        Comments comment  = modelMapper.map(data,Comments.class);
          comment.getItens().forEach(e->{
                  e.setComment(comment);
                  e.Datas(LocalDateTime.now());
          });
        if(files!=null){
            for(MultipartFile file:files){
                if(file!=null){
                    String nameFile =endpoint+"imagens"+"/"+ file.getOriginalFilename();
                    byte[] bytes = file.getBytes();
                    CommentsIMGDTO imagensDTO = new CommentsIMGDTO(nameFile);
                    ImagensComments imagens = modelMapper.map(imagensDTO,ImagensComments.class);
                    for (ListaComments chamdoe:comment.getItens()){
                        imagens.setListaComments(chamdoe);
                        chamdoe.setImagens(itens);
                        itens.add(imagens);
                    }
                    File path = new File(UPLOAD_DIR);
                    String name =UPLOAD_DIR+"/"+ file.getOriginalFilename();
                    if(!path.exists()){
                        path.mkdir();
                    }
                    Files.write(Paths.get(name), bytes);
                }
            }
        }
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

    public void EnvioComentarios(long chamadoID,String msg) throws IOException {
        jsonObject.put("chamadoid", chamadoID);
        items.put("usuario", "Resposta automática\n");
        items.put("email", "suporte.dev18@gmail.com");
        items.put("userImagem", "https://back-chamados.onrender.com/Logos/assistente.jpeg");
        items.put("comments",msg);
        JSONArray itensArray = new JSONArray();
        itensArray.put(items);
        jsonObject.put("itens", itensArray);
        String jsonString = jsonObject.toString(4); // 4 é a indentação para uma visualização mais legível do json exibido
        comments(jsonString, null);
    }

}
