package br.com.Initialiizr.Informatica116.sistem.Service;
import br.com.Initialiizr.Informatica116.sistem.Controler.ControlerEmail;
import br.com.Initialiizr.Informatica116.sistem.Controler.UsuarioControler;
import br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO.IssueDTO;
import br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO.ImagensDTO;
import br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO.StatusOneDTO;
import br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO.UpdateChamado;
import br.com.Initialiizr.Informatica116.sistem.Models.*;
import br.com.Initialiizr.Informatica116.sistem.Models.AUTH_USER.Perfil;
import br.com.Initialiizr.Informatica116.sistem.Models.CHAMADO_HARDWARE.Chamado;
import br.com.Initialiizr.Informatica116.sistem.Models.CHAMADO_HARDWARE.Issue;
import br.com.Initialiizr.Informatica116.sistem.Models.CHAMADO_HARDWARE.Imagens;
import br.com.Initialiizr.Informatica116.sistem.Security.ConvertJson;
import br.com.Initialiizr.Informatica116.sistem.repository.IssueResposoty;
import br.com.Initialiizr.Informatica116.sistem.repository.UserRepository;
import br.com.Initialiizr.Informatica116.sistem.validators.ChamadoInterface;
import br.com.Initialiizr.Informatica116.sistem.validators.MSG;
import br.com.Initialiizr.Informatica116.sistem.validators.ValidationsTec;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ChamadoService implements ChamadoInterface {
    @Autowired
    private ControlerEmail controlerEmail;
    @Autowired
    private UsuarioControler usuarioControler;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IssueResposoty hardwareRepository;
    @Autowired
    private ConvertJson convertJson;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    ServiceHardware serviceHardware;
    @Autowired
    ValidationsTec validationsTec;
    @Value("${endpoint}")
    private String endpoint;
    //servico de registo de chamado
    @Override
    public IssueDTO registrar(String DTO, MultipartFile[] files) {
//        try{;
//            List<Imagens> itens = new ArrayList<>();
//            IssueDTO issueDTO = convertJson.convertJson(DTO, IssueDTO.class);
//            Issue chamado = modelMapper.map(issueDTO, Issue.class);
//            chamado.getItens().forEach(e->{
//                e.setStatus(Status.AGUARDANDO_TECNICO);
//                e.setIssue(chamado);
//                e.Datas(LocalDateTime.now());
//                e.DataCreate(LocalDateTime.now());
//                e.setAtivo(true);
//                e.setCardId("CARD-"+chamado.gerarCode());
//            });
//            chamado.setServico("hardware");
//            if(files!=null){
//                for(MultipartFile file:files){
//                    if(file!=null){
//                        String nameFile =endpoint+"imagens"+"/"+ file.getOriginalFilename();
//                        byte[] bytes = file.getBytes();
//                        ImagensDTO imagensDTO = new ImagensDTO(nameFile);
//                        Imagens imagens = modelMapper.map(imagensDTO,Imagens.class);
//                        System.out.println("minhas imagens "+nameFile);
//                        for (Chamado chamdoe:chamado.getItens()){
//                            imagens.setChamado(chamdoe);
//                            chamdoe.setImagens(itens);
//                            itens.add(imagens);
//                        }
//                        String pathName = "Img";
//                        File path = new File(pathName);
//                        String name =pathName+"/"+ file.getOriginalFilename();
//                        if(!path.exists()){
//                            path.mkdir();
//                        }
//                        Files.write(Paths.get(name), bytes);
//                    }
//                }
//            }
//            var s = hardwareRepository.findOneByUsuarioid(issueDTO.getUsuarioid());
//
//            var chamadoItens = hardwareRepository.findOneById(issueDTO.getId());
//            if(chamadoItens!=null) {
//                chamadoItens.getItens().addAll(chamado.getItens());
//                hardwareRepository.save(chamadoItens);
//                return modelMapper.map(chamado, IssueDTO.class);
//            } else {
//                Issue issueSalvo = hardwareRepository.save(chamado);
//                return modelMapper.map(issueSalvo, IssueDTO.class);
//            } }catch (IOException e){
//            throw  new RuntimeException(e);
//        }
        try{
            List<Imagens> itens = new ArrayList<>();
            IssueDTO issueDTO = convertJson.convertJson(DTO, IssueDTO.class);
            Issue chamado = modelMapper.map(issueDTO, Issue.class);
            chamado.getItens().forEach(e->{
                e.setStatus(Status.AGUARDANDO_TECNICO);
                e.setIssue(chamado);
                e.Datas(LocalDateTime.now());
                e.DataCreate(LocalDateTime.now());
                e.setAtivo(true);
                e.setCardId("CARD-"+chamado.gerarCode());
            });
            if(files!=null){
                for(MultipartFile file:files){
                    if(file!=null){
                        String nameFile =endpoint+"imagens"+"/"+ file.getOriginalFilename();
                        byte[] bytes = file.getBytes();
                        ImagensDTO imagensDTO = new ImagensDTO(nameFile);
                        Imagens imagens = modelMapper.map(imagensDTO,Imagens.class);
                        System.out.println("minhas imagens "+nameFile);
                      for (Chamado chamdoe:chamado.getItens()){
                          imagens.setChamado(chamdoe);
                          chamdoe.setImagens(itens);
                          itens.add(imagens);
                      }
                        String pathName = "Logos";
                        File path = new File(pathName);
                        String name =pathName+"/"+ file.getOriginalFilename();
                        if(!path.exists()){
                            path.mkdir();
                        }
                        Files.write(Paths.get(name), bytes);
                    }
                }
            }
            var chamadoItens = hardwareRepository.findOneById(issueDTO.getId());
            if(chamadoItens!=null) {
                chamadoItens.getItens().addAll(chamado.getItens());
                hardwareRepository.save(chamadoItens);
                return modelMapper.map(chamado, IssueDTO.class);
            } else {
                Issue issueSalvo = hardwareRepository.save(chamado);
                var hf = modelMapper.map(issueSalvo, IssueDTO.class);
                return modelMapper.map(issueSalvo, IssueDTO.class);
            } }catch (IOException e){
            throw  new RuntimeException(e);
        }
    }
    // pegand
    public IssueDTO ChamadoId(long id){
        Issue chamadoid = hardwareRepository.findOneByIds(id);
        if(chamadoid==null){throw new RuntimeException("nada encontrado");}
        IssueDTO chamado = modelMapper.map(chamadoid, IssueDTO.class);
        return  chamado;
    }
    // pegando chamado chamado por card
    @Override
    public IssueDTO Card(String card, long id) {
        Issue chamadoid = hardwareRepository.findOneByCard(card,id);
        if(chamadoid!=null){
            IssueDTO chamado = modelMapper.map(chamadoid, IssueDTO.class);
            return  chamado;
        }
        throw new RuntimeException("nada encontrado no banco");
    }
    public Page<IssueDTO> Listar(Pageable page, String setor,
                                 String dataAntes, String dataDepois,
                                 int filial,boolean ativo) {
        if(dataAntes!=null&&dataDepois!=null){
            return hardwareRepository.findAllDataAntesAndDataDepoisByAtivoTrue(page,dataAntes,dataDepois,filial)
                    .map(e->modelMapper.map(e, IssueDTO.class));
        }
        if(setor!=null){
            return hardwareRepository.findAllBySetorContainingIgnoreCase(page,setor,filial)
                    .map(e->modelMapper.map(e, IssueDTO.class));
        }

        var dados = hardwareRepository.findAllByAtivo(page,filial,ativo)
                .map(e->modelMapper.map(e, IssueDTO.class));

        return  dados;
//        }ss));
    }
    public ResponseEntity<Resource> ListaImagensId(String name){
        Path path = Paths.get("Logos").resolve(name);
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
    // pegando chamado por e exibindo para cada usuario!
    public Page<IssueDTO> pegarChamadoId(long id, Pageable pageable,String dataAntes,String dataDepois,String descricao){
        List<Issue> lista = hardwareRepository.findAllByUsuarioidById(id,pageable );
        if (dataAntes!=null&&dataDepois!=null){
            return hardwareRepository.findAllDataByUserAtivoTrue(pageable,id,dataAntes,dataDepois)
                    .map(e->modelMapper.map(e, IssueDTO.class));
        }
//        else if (descricao!=null) {
//            return hardwareRepository.findIssuesWithItemsByUserIdAndSearchTerm(pageable,id,descricao) .map(e->modelMapper.map(e, IssueDTO.class));
//
//        }
        List<IssueDTO> lis = new ArrayList<>();
        for (Issue d:lista){
            var map = modelMapper.map(d, IssueDTO.class);
           lis.add(map);
        }
        return new PageImpl<>( lis);
    }
    public Page<IssueDTO> pegarChamadoIdTecnic(long id, Pageable pageable){
        List<Issue> lista = hardwareRepository.findAllByUsuarioidByIdTesc(id,pageable );
        List<IssueDTO> lis = new ArrayList<>();
        for (Issue d:lista){
            var map = modelMapper.map(d, IssueDTO.class);
            lis.add(map);
        }
        return new PageImpl<>( lis);
    }
    public ResponseEntity update(long id,String data){
        UpdateChamado updateChamado = convertJson.convertJson(data,UpdateChamado.class);
        Issue lista = hardwareRepository.findOneByUsuarioidByIdAtivoTrue(id,updateChamado.getId());
        validationsTec.existeTecnico(lista,updateChamado.getTecnicoid());
        lista.atualiza(updateChamado);
        lista.getItens().forEach(
                e->{
                    e.setStatus(Status.EM_ANDAMENTO);
                    e.setDone(true);
                }
        );
         modelMapper.map(lista, IssueDTO.class);
        return ResponseEntity.ok(new MSG("tecnico adicionado ao chamado!"));
    }
    // enviando ao usuario quando o chamado for feito
    public ResponseEntity validaChamado(long id,String cardChamado,long UsuarioLogado){
        var user =userRepository.getReferenceById(UsuarioLogado);
        System.out.println("meu id de usuario "+UsuarioLogado);
        Issue issue = hardwareRepository.findOneByIdChamado(id,cardChamado);
        if(issue ==null){
            throw new RuntimeException("nada encontrado");
        }
        validationsTec.Valid(issue,UsuarioLogado);
        validationsTec.Status(issue);

        issue.getItens().forEach(e->e.setStatus(Status.AGUARDANDO_VALIDACAO));
        issue.getItens().forEach(e->e.setAceito(true));
        issue.getItens().forEach(e->e.setAceito(true));
        return ResponseEntity.ok(new MSG("status atualizado"));
    }
    // precisa ser feito validacao para o chamado nao ser aberto
    public ResponseEntity validaChamadoUSer(long id, String cardChamado,long UsuarioLogado){
        Issue issue = hardwareRepository.findOneByIdChamado(id,cardChamado);
        System.out.println("meu id de usuario "+UsuarioLogado);
        Instant hora = Instant.now();
        System.out.println(id);
        if(issue ==null){
            throw new RuntimeException("nada encontrado");
        }
        // validacão de tecnico
        validationsTec.StatusvalidFechado(issue);
        issue.getItens().forEach(e->e.setStatus(Status.FECHADO));
        issue.getItens().forEach(e->e.setAtivo(false));
        issue.getItens().forEach(e->e.setAceito(false));
        issue.getItens().forEach(e->e.setClient_feito(true));
        issue.getItens().forEach(e->e.DataFeito(LocalDateTime.now()));
        hardwareRepository.save(issue);
        return  ResponseEntity.ok().body(new MSG("status fechado"));
    }

    public ResponseEntity validaChamadoReaberto(long id, String cardChamado, long UsuarioLogado) {
        Issue issue = hardwareRepository.findOneByIdChamado(id, cardChamado);
        System.out.println("meu id de usuario " + UsuarioLogado);
        if (issue == null) {
            throw new RuntimeException("nada encontrado");
        }
        validationsTec.reaberto(issue, UsuarioLogado);
        // validacão de tecnico
        validationsTec.StatusvalidFechado(issue);
        issue.getItens().forEach(e -> e.setStatus(Status.RE_ABERTO));
        issue.getItens().forEach(e -> e.setAceito(false));
        hardwareRepository.save(issue);
        return ResponseEntity.ok().body(new MSG("chamado reaberto"));
    }

    public ResponseEntity StatusJira(long id,String cardChamado,long UsuarioLogado){
        Issue issue = hardwareRepository.findOneByIdChamado(id,cardChamado);
        if(issue ==null){
            throw new RuntimeException("nada encontrado");
        }// validacão de tecnico
        validationsTec.Valid(issue,UsuarioLogado);
        validationsTec.StatusJira(issue);
        issue.getItens().forEach(e->e.setStatus(Status.AGUARDANDO_JIRA));
        hardwareRepository.save(issue);
        return  ResponseEntity.ok().body(new MSG("status atualizado para aguardando jira"));
    }

    public ResponseEntity StatusAtorizacao(long id,String cardChamado,long UsuarioLogado){
        Issue issue = hardwareRepository.findOneByIdChamado(id,cardChamado);
        if(issue ==null){
            throw new RuntimeException("nada encontrado");
        }// validacão de tecnico
        validationsTec.Valid(issue,UsuarioLogado);
        validationsTec.Aprovador(issue);
        issue.getItens().forEach(e->e.setStatus(Status.AGUARDANDO_APROVACAO));
        hardwareRepository.save(issue);
        return  ResponseEntity.ok().body(new MSG("status atualizado para aguardando aprovação"));
    }
    public List<Issue> pegaStor(){
        var dados = hardwareRepository.findAll();

        return  dados;
    }

    public StatusOneDTO OneStatus(long id, String card) {
        Issue chamadoid = hardwareRepository.findOneByCard(card,id);
        if(chamadoid!=null){
            StatusOneDTO chamado = modelMapper.map(chamadoid, StatusOneDTO.class);
            return  chamado;
        }
        throw new RuntimeException("nada encontrado no banco");

    }
}
