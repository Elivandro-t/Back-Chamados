package br.com.Initialiizr.Informatica116.sistem.Service;

import br.com.Initialiizr.Informatica116.sistem.Controler.ControlerEmail;
import br.com.Initialiizr.Informatica116.sistem.DTO.*;
import br.com.Initialiizr.Informatica116.sistem.Models.*;
import br.com.Initialiizr.Informatica116.sistem.Security.ConvertJson;
import br.com.Initialiizr.Informatica116.sistem.Security.TokenService;
import br.com.Initialiizr.Informatica116.sistem.repository.UserRepository;
import br.com.Initialiizr.Informatica116.sistem.validators.MSG;
import br.com.Initialiizr.Informatica116.sistem.validators.ValidatorEmail;
import br.com.Initialiizr.Informatica116.sistem.validators.ValidatorPassword;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {
    @Autowired
    private ControlerEmail emailSend;
    @Autowired
    private ValidatorEmail validatorEmail;
    @Autowired
    private ConvertJson convertJson;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    ValidatorPassword password;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    TokenService tokenservice;
    @Autowired
   private  AuthenticationManager authenticationManager;
    @Autowired
    private RefeshTokenService refeshTokenService;
    public UserDTO registro(UserDTO userDTO){
        validatorEmail.validator(userDTO.getEmail());
        var user = userRepository.pegandoUsuarioExistente(userDTO.getEmail());
        if(user==null){
            throw new RuntimeException("email e nulo");
        }
        if(user.isPresent()){
            throw new RuntimeException("usuario ja registrado");
        }
        List<Perfil> perfis = new ArrayList<>();
        password.validator(userDTO.getPassword());
        var usuario = modelMapper.map(userDTO,User.class);
        Perfil perfil = new Perfil();
        perfil.setName("user");
        perfil.setUser(usuario);
        perfil.setAtivo(true);
        perfis.add(perfil);
        usuario.setItens(perfis);
        usuario.criptografar(userDTO.getPassword());
        var registrado = userRepository.save(usuario);
        return modelMapper.map(registrado,UserDTO.class);
    }
    public MsgToken Login(LoginDTo loginDTo){
        var token = new UsernamePasswordAuthenticationToken(loginDTo.email(),loginDTo.password());
        var user = authenticationManager.authenticate(token);
        User user1 = (User) user.getPrincipal();
        RefreshToken refreshToken = refeshTokenService.registrarToken(user1.getId());
        var tokenString = tokenservice.geratoken((User) user.getPrincipal(),user.getAuthorities());
        return new MsgToken(tokenString,refreshToken.getRefreshtoken());
    }
    @Transactional
    public MSG image(MultipartFile image, long id) throws IOException {
        byte[] bytes = image.getBytes();
        String imagensUsuario = "Logos";
        File file = new File(imagensUsuario);
        String names =imagensUsuario+"/"+image.getOriginalFilename();
        String imagem ="http://localhost:8080/"+imagensUsuario+"/"+image.getOriginalFilename();
        if(!file.exists()){
            file.mkdir();
        }
        Files.write(Paths.get(names),bytes);
        System.out.println("meu id "+id + "minha imagem "+imagem);
        var usuario = userRepository.findById(id);
        if(usuario.isPresent()){
            User user = usuario.get();
            user.setImagem(imagem);
            userRepository.save(user);
            System.out.println("minha imagem "+imagem);
            System.out.println("enviado com sucesso");
            return  new MSG("enviado com sucesso");
        }
        throw new RuntimeException("erro ao enviar imagem");
    }

    public MSG perfil(String email,String perfil) {
        User user = userRepository.findByEmail(email);
        PerfilDTo perfilDTo = convertJson.convertJson(perfil,PerfilDTo.class);
        Perfil perfil1 = modelMapper.map(perfilDTo,Perfil.class);
        for (Perfil perfils:user.getItens()){
            if(perfils.getName().equals(perfil1.getName())){
                return new MSG("perfil já adicionado");
            }
        }
        if(email!=null){
           user.getItens().add(perfil1);
           perfil1.setUser(user);
           userRepository.save(user);
           return  new MSG("perfil adicionado");
        }
        return null;
    }
    public DetalheUsuario buscarUserId(long userId){
        User user = userRepository.getReferenceById(userId);
        if(user!=null){
            return  modelMapper.map(user,DetalheUsuario.class);
        }
        throw new RuntimeException("sem usuario encontrado");
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
    public ResponseEntity alterPassword(AlterPassword alterPassword){
        password.AlterarPassword(alterPassword);
        password.validator(alterPassword.newPassword());
        Optional<User> dadosUsaurio = userRepository.findById(alterPassword.id());
        if(dadosUsaurio.isPresent()){
            User user = dadosUsaurio.get();
            user.criptografar(alterPassword.newPassword());
            userRepository.save(user);
            return ResponseEntity.ok(new MensagemPd("senha alterada com sucesso!"));
        }
        return null;

    }
    // salvando codigo no banco e enviando para o email de usuario
    public ResponseEntity alterCode(String email) throws MessagingException {
        User result = userRepository.findByEmail(email);
        if(result!=null){
            String cod = gerarCode();
            emailSend.SendEmil(email,cod);
            result.setCodigo(cod);
            userRepository.save(result);
            return ResponseEntity.ok(new Mensagem("codigo de verificação enviado ao email!"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensagem("email não encontrado"));
    }
    private  String gerarCode(){
        return RandomStringUtils.randomAlphabetic(6).toUpperCase();
    }
    public ResponseEntity verifyCodigo(UserVerify data){
      User user = userRepository.findByEmail(data.email());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Mensagem("Usuário não encontrado."));
        } if (!user.getCodigo().equals(data.codigo())) {
            user.incrementVerificationAttempts();
            if (user.getExp() >= 3) {
                user.resetVerificationAttempts();
            }
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensagem("Código de verificação inválido."));
        }
        user.resetVerificationAttempts();

        return ResponseEntity.ok(new Mensagem("Código de verificação válido."));
    }
    // alterando senha do endpoint
    public ResponseEntity AlterByCode(AlterPassword alterPassword){
        password.validator(alterPassword.newPassword());
        User user = userRepository.findByEmail(alterPassword.email());
        if(user!=null){
            user.criptografar(alterPassword.newPassword());
            user.setCodigo(null);
            userRepository.save(user);
            return ResponseEntity.ok(new MensagemPd("senha alterada com sucesso!"));
        }
        return null;
    }
}
