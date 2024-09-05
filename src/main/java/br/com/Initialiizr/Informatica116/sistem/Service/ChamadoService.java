package br.com.Initialiizr.Informatica116.sistem.Service;
import br.com.Initialiizr.Informatica116.sistem.Controler.ControlerEmail;
import br.com.Initialiizr.Informatica116.sistem.Controler.UsuarioControler;
import br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO.*;
import br.com.Initialiizr.Informatica116.sistem.DTO.MESAGENS.Mensagem;
import br.com.Initialiizr.Informatica116.sistem.Models.*;
import br.com.Initialiizr.Informatica116.sistem.Models.CHAMADO_HARDWARE.Chamado;
import br.com.Initialiizr.Informatica116.sistem.Models.CHAMADO_HARDWARE.Issue;
import br.com.Initialiizr.Informatica116.sistem.Models.CHAMADO_HARDWARE.Imagens;
import br.com.Initialiizr.Informatica116.sistem.Models.OPTIONS.Setor;
import br.com.Initialiizr.Informatica116.sistem.Security.ConvertJson;
import br.com.Initialiizr.Informatica116.sistem.repository.IssueResposoty;
import br.com.Initialiizr.Informatica116.sistem.repository.SetorRepository;
import br.com.Initialiizr.Informatica116.sistem.repository.UserRepository;
import br.com.Initialiizr.Informatica116.sistem.validators.ChamadoInterface;
import br.com.Initialiizr.Informatica116.sistem.validators.ChamadoValidator.ValidationComponent;
import br.com.Initialiizr.Informatica116.sistem.validators.ChamadoValidator.ValidationDiaValid;
import br.com.Initialiizr.Informatica116.sistem.validators.MSG;
import br.com.Initialiizr.Informatica116.sistem.validators.ValidaSetor.ValidaSetor;
import br.com.Initialiizr.Informatica116.sistem.validators.ValidationsTec;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
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
    ValidationsTec validationsTec;
    @Autowired
    private ValidationComponent validationComponent;

    @Autowired
    private CommetService commetService;
    @Autowired
    private ValidationDiaValid validationDiaValid;
    @Value("${endpoint}")
    private String endpoint;
    @Autowired
    BotService botService;
    private static final String UPLOAD_DIR = "/var/lib/data/Logos";
    public ChamadoService(IssueResposoty issueResposoty){
        this.hardwareRepository = issueResposoty;
    }
    @Autowired
    private ValidaSetor validaSetor;
    //servico de registo de chamado
    @Override
    public IssueDTO registrar(String DTO, MultipartFile[] files) {
        try{
            List<Imagens> itens = new ArrayList<>();

            IssueDTO issueDTO = convertJson.convertJson(DTO, IssueDTO.class);
            Issue chamado = modelMapper.map(issueDTO, Issue.class);
            chamado.setData_criacao(LocalDateTime.now());
            validationDiaValid.validation(chamado);
            validaSetor.validaSetor(chamado);
            chamado.getItens().forEach(e->{
                e.setStatus(Status.AGUARDANDO_TECNICO);
                e.setIssue(chamado);
                e.Datas(LocalDateTime.now());
                e.DataCreate(LocalDateTime.now());
                e.setAtivo(true);
                e.setCardId("CARD-"+chamado.gerarCode());
            });
            // validação de imagens
            if(files!=null){
                for(MultipartFile file:files){
                    if(file!=null){
                        String nameFile =endpoint+"imagens"+"/"+ file.getOriginalFilename();
                        byte[] bytes = file.getBytes();
                        ImagensDTO imagensDTO = new ImagensDTO(nameFile);
                        Imagens imagens = modelMapper.map(imagensDTO,Imagens.class);
                      for (Chamado chamdoe:chamado.getItens()){
                          imagens.setChamado(chamdoe);
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

            /// Adicionando um chamado na lista ou crie uma
            var chamadoItens = hardwareRepository.findOneById(issueDTO.getId());
            if(chamadoItens!=null) {
                chamadoItens.getItens().addAll(chamado.getItens());
               hardwareRepository.save(chamadoItens);
               return modelMapper.map(chamado, IssueDTO.class);
            } else {
                Issue issueSalvo = hardwareRepository.save(chamado);
                for (Chamado c:issueSalvo.getItens()){
                    botService.enviarNotificacaoChamado(c.getCardId(),chamado.getUsuario_logado(),chamado.getUsuarioid(),c.getId(),c.getDatacreate(),c.getTitulo());
                    commetService.EnvioComentarios(c.getId(), "\n" +
                            "✅ Sua solicitação foi recebida com sucesso!\n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            "⛔Para solicitações que requerem aprovação do gestor, caso não sejam aprovadas em até 2 dias, o chamado será cancelado.\n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            "\uD83D\uDCAC Caso precise adicionar informações após abrir o chamado, utilize o campo de comentários para entrar em contato com o nosso time de suporte.");
                }
                return modelMapper.map(issueSalvo, IssueDTO.class);
            } }catch (IOException e){
            throw  new RuntimeException(e);
        }
    }
    // pegando chamado ṕor id
    public IssueDTO ChamadoId(long id){
        Issue chamadoid = hardwareRepository.findOneByIds(id);
        if(chamadoid!=null){
            return modelMapper.map(chamadoid, IssueDTO.class);
        }
        throw new RuntimeException("Nada encontrado no banco");
    }
    // pegando chamado por card
    @Override
    public IssueDTO Card(String card, long id) {
        Issue chamadoid = hardwareRepository.findOneByCard(card,id);
        if(chamadoid!=null){
            return modelMapper.map(chamadoid, IssueDTO.class);
        }
        throw new RuntimeException("Nada encontrado no banco");
    }

    // listando todos os chamado e filtrando os dados usando as informacoes escritas no parametro do metodo
    public Page<IssueDTO> Listar(Pageable page, String setor,
                                 String dataAntes, String dataDepois,
                                 int filial,boolean ativo) {

        String busca = setor != null ? setor : "";

        if(dataAntes!=null&&dataDepois!=null){
            return hardwareRepository.findAllDataAntesAndDataDepoisByAtivoTrue(page,dataAntes,dataDepois,filial,ativo)
                    .map(e->modelMapper.map(e, IssueDTO.class));
        }
        else if(setor!=null){
            return hardwareRepository.findAllBySetorContainingIgnoreCaseBusca(page,busca,filial)
                    .map(e->modelMapper.map(e, IssueDTO.class));
        }
        else {
            return hardwareRepository.findAllByAtivo(page, filial, ativo)
                    .map(e -> modelMapper.map(e, IssueDTO.class));
        }
    }

    // buscando todos os chamados por filiais
    public Page<IssueDTO> ListarChamadosFiliais(Pageable page, String setor,
                                 String dataAntes, String dataDepois,
                                 boolean ativo) {
        String busca = setor != null ? setor : ""; // Define a busca como setor se não for nulo
        if(dataAntes!=null&&dataDepois!=null){
            return hardwareRepository.findAllDataAntesAndDataDepoisByAtivoTrueAndFalse(page,dataAntes,dataDepois,ativo)
                    .map(e->modelMapper.map(e, IssueDTO.class));
        }
        else if(setor!=null){
            return hardwareRepository.findAllBySetorContainingIgnoreCaseTrueAndFalse(page,busca,ativo)
                    .map(e->modelMapper.map(e, IssueDTO.class));
        }
        else {
            var dados = hardwareRepository.findAllByAtivoTrueAndFalse(page,ativo)
                    .map(e->modelMapper.map(e, IssueDTO.class));
            return  dados;
        }

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
    // pegando chamado por e exibindo para cada usuario!
    public Page<IssueDTO> pegarChamadoId(long id, Pageable pageable,String dataAntes,String dataDepois,String descricao,boolean ativo){
        String busca = descricao != null ? descricao : ""; //
        if (dataAntes!=null&&dataDepois!=null){
            return hardwareRepository.findAllDataByUserAtivoTrue(pageable,id,dataAntes,dataDepois)
                    .map(e->modelMapper.map(e, IssueDTO.class));
        }
        else if(descricao!=null){
            return hardwareRepository.findAllBySetorContainingIgnoreCaseTrueAndFalseByUsuario(pageable,id,busca,ativo)
                    .map(e->modelMapper.map(e, IssueDTO.class));
        }
        else {
            return hardwareRepository.findAllByAtivoTrueAndFalseByUsuario(pageable,id,ativo)
                    .map(e->modelMapper.map(e, IssueDTO.class));
        }
    }
     /// quando o atendente aceita o chamado o status do chamado muda para em andamento
    public ResponseEntity update(long id,String data) throws IOException {
        UpdateChamado updateChamado = convertJson.convertJson(data,UpdateChamado.class);
        Issue lista = hardwareRepository.findOneByUsuarioidByIdAtivoTrue(id,updateChamado.getId());
        validationsTec.existeTecnico(lista,updateChamado.getTecnicoid());
        lista.atualiza(updateChamado);
        lista.setHora_aceito(LocalDateTime.now());
        lista.getItens().forEach(
                e->{
                    e.setStatus(Status.EM_ANDAMENTO);
                    e.setDone(true);
                    e.setDataTecnicoAceito(LocalDateTime.now());
                }
        );
        for (Chamado c:lista.getItens()){
            commetService.EnvioComentarios(updateChamado.getId(),"O status do seu pedido foi alterado para Em andamento");

        }
        modelMapper.map(lista, IssueDTO.class);
        return ResponseEntity.ok(new MSG("tecnico adicionado ao chamado!"));
    }
    public ResponseEntity ClearTcnico(long id,String cardChamado,long UsuarioLogado) throws IOException {

        Issue issue = hardwareRepository.findOneByIdChamado(id,cardChamado);
        if(issue!=null){
            validationsTec.Valid(issue,UsuarioLogado);
            validationsTec.StatusvalidFechado(issue);
            validationComponent.validation(issue);
            issue.getItens().forEach(e->e.setStatus(Status.AGUARDANDO_TECNICO));
            issue.setHora_aceito(null);
            issue.getItens().forEach(e->{
                e.setAceito(false);
                e.setDone(false);
                e.setClient_feito(false);
                e.setTecnicoid(0);
                e.setTecnico_responsavel(null);
                e.setData_chamdo_feito(null);
            });
            for (Chamado c:issue.getItens()){
                commetService.EnvioComentarios(c.getId(),"O status do seu pedido foi alterado para Aguardando tecnico");

            }
            return ResponseEntity.ok(new Mensagem("Status atualizado"));
        }
        throw new RuntimeException("nada encontrado");
    }

    // enviando ao usuario quando o chamado for feito
    public ResponseEntity validaChamado(long id,String cardChamado,long UsuarioLogado) throws IOException {
        var user =userRepository.getReferenceById(UsuarioLogado);
        System.out.println("meu id de usuario "+UsuarioLogado);
        Issue issue = hardwareRepository.findOneByIdChamado(id,cardChamado);
        if(issue ==null){
            validationsTec.Valid(issue,UsuarioLogado);
            validationsTec.Status(issue);
            issue.getItens().forEach(e->{
                        e.setData_chamdo_feito(LocalDateTime.now());
                        e.setStatus(Status.AGUARDANDO_VALIDACAO);
                        e.setAceito(true);
                    }
            );
            EnviarTextComentario(issue,"Aguardando validação");
            return ResponseEntity.ok(new MSG("status atualizado"));
        }
        throw new RuntimeException("Nada encontrado");
    }
    // precisa ser feito validacao para o chamado nao ser aberto
    public ResponseEntity validaChamadoUSer(long id, String cardChamado,long UsuarioLogado) throws IOException {
        Issue issue = hardwareRepository.findOneByIdChamado(id,cardChamado);
        System.out.println("meu id de usuario "+UsuarioLogado);
        Instant hora = Instant.now();
        System.out.println(id);
        if(issue ==null){
            throw new RuntimeException("nada encontrado");
        }
        // validacão de tecnico
        validationsTec.StatusvalidFechado(issue);
        issue.getItens().forEach(e->{
            e.setStatus(Status.FECHADO);
            e.setAtivo(false);
            e.setAceito(false);
            e.setClient_feito(true);
            e.DataFeito(LocalDateTime.now());
        });
        hardwareRepository.save(issue);
        for (Chamado c:issue.getItens()){
            commetService.EnvioComentarios(c.getId(),"O status do seu pedido foi alterado para Fechado");

        }
        return  ResponseEntity.ok().body(new MSG("status fechado"));
    }
  // fazendo a validacao de o chamado ja está reaber caso não ele abri o adicionando o status aberto
    public ResponseEntity validaChamadoReaberto(long id, String cardChamado, long UsuarioLogado) throws IOException {
        Issue issue = hardwareRepository.findOneByIdChamado(id, cardChamado);
        if (issue != null) {
            validationsTec.reaberto(issue);
            // validacão de tecnico
            issue.getItens().forEach(e -> {
                e.setStatus(Status.RE_ABERTO);
                e.setAceito(false);
                e.setData_chamdo_feito(null);
            });
            hardwareRepository.save(issue);
            for (Chamado c:issue.getItens()){
                commetService.EnvioComentarios(c.getId(),"O status do seu pedido foi alterado para Re aberto");
            }
            return ResponseEntity.ok().body(new MSG("chamado reaberto"));
        }
        throw new RuntimeException("nada encontrado");

    }

    public ResponseEntity StatusJira(long id,String cardChamado,long UsuarioLogado) throws IOException {
        Issue issue = hardwareRepository.findOneByIdChamado(id,cardChamado);
        if(issue ==null){
            throw new RuntimeException("nada encontrado");
        }// validacão de tecnico
        validationsTec.Valid(issue,UsuarioLogado);
        validationsTec.StatusJira(issue);
        issue.getItens().forEach(e->e.setStatus(Status.AGUARDANDO_JIRA));
        hardwareRepository.save(issue);
        for (Chamado c:issue.getItens()){
            commetService.EnvioComentarios(c.getId(),"O status do seu pedido foi alterado para Aguardando jira");

        }
        return  ResponseEntity.ok().body(new MSG("status atualizado para aguardando jira"));
    }

    public ResponseEntity StatusAtorizacao(long id,String cardChamado,long UsuarioLogado) throws IOException {
        Issue issue = hardwareRepository.findOneByIdChamado(id,cardChamado);
        if(issue ==null){
            validationsTec.Valid(issue,UsuarioLogado);
            validationsTec.Aprovador(issue);
            issue.getItens().forEach(e->e.setStatus(Status.AGUARDANDO_APROVACAO));
            for (Chamado c:issue.getItens()){
                commetService.EnvioComentarios(c.getId(),"O status do seu pedido foi alterado para Aguardando jira");

            }
            hardwareRepository.save(issue);
            return  ResponseEntity.ok().body(new MSG("status atualizado para aguardando aprovação"));
        }
        throw new RuntimeException("nada encontrado");

    }
    public List<Issue> pegaStor(){
        return hardwareRepository.findAll();
    }

    public StatusOneDTO OneStatus(long id, String card) {
        Issue chamadoid = hardwareRepository.findOneByCard(card,id);
        if(chamadoid!=null){
            StatusOneDTO chamado = modelMapper.map(chamadoid, StatusOneDTO.class);
            return  chamado;
        }
        throw new RuntimeException("nada encontrado no banco");

    }
// listando api pra monitoramento
    public Page<RelatorioDto> Relatorio(Pageable page, String setor,
                                        String dataAntes, String dataDepois, boolean ativo) {
        String busca = setor != null ? setor : ""; // Define a busca como setor se não for nulo
        if(dataAntes!=null&&dataDepois!=null){
            return hardwareRepository.findAllDataAntesAndDataDepoisByAtivoTrueAndFalse(page,dataAntes,dataDepois,ativo)
                    .map(e->modelMapper.map(e, IssueDTO.class));
        }
        else if(setor!=null){
            return hardwareRepository.findAllBySetorContainingIgnoreCaseTrueAndFalse(page,busca,ativo)
                    .map(e->modelMapper.map(e, IssueDTO.class));
        }
        else {

            var dados = hardwareRepository.findAllByAtivoTrueAndFalse(page,ativo)
                    .map(e->modelMapper.map(e, IssueDTO.class));

            return  dados;
        }
    }
/// enviando mesagem de texto para o usuario
    private void EnviarTextComentario(Issue issue,String status) {
        try {
            for (Chamado c : issue.getItens()) {
                commetService.EnvioComentarios(c.getId(), "Seu chamado foi concluído com sucesso. Por favor, valide sua solicitação para confirmar que tudo está correto. Caso não esteja de acordo ou precise de ajustes, você pode recusar a solicitação.\n" +
                        "\n" +
                        "Se não houver uma resposta ou validação dentro do prazo de 2 dias, o chamado será fechado automaticamente.\n" +
                        "\n" +
                        "Agradecemos pela sua colaboração!\n" +
                        "\n" +
                        "Suporte TI");
                commetService.EnvioComentarios(c.getId(), "O status do seu pedido foi alterado para " + status);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    }
