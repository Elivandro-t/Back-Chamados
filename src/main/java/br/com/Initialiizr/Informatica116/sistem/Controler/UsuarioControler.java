package br.com.Initialiizr.Informatica116.sistem.Controler;

import br.com.Initialiizr.Informatica116.sistem.DTO.AUTH_DAO.*;
import br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO.Delete;
import br.com.Initialiizr.Informatica116.sistem.DTO.MESAGENS.Mensagem;
import br.com.Initialiizr.Informatica116.sistem.Models.LoginDTo;
import br.com.Initialiizr.Informatica116.sistem.Models.AUTH_USER.RefreshToken;
import br.com.Initialiizr.Informatica116.sistem.Models.AUTH_USER.UserVerify;
import br.com.Initialiizr.Informatica116.sistem.Security.TokenService;
import br.com.Initialiizr.Informatica116.sistem.Service.RefeshTokenService;
import br.com.Initialiizr.Informatica116.sistem.Service.UserService;
import br.com.Initialiizr.Informatica116.sistem.validators.MSG;
import jakarta.mail.MessagingException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping
@CrossOrigin(origins = "*")
public class UsuarioControler {
    @Autowired
    UserService userService;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    TokenService tokenService;
    @Autowired
    private RefeshTokenService refeshTokenService;
    @PostMapping("registrar")
    @Transactional
    public ResponseEntity<Mensagem> registrar(@RequestBody UserDTO userDTO){
        var response = userService.registro(userDTO);
        return ResponseEntity.ok().body(new Mensagem("cadastrado com sucesso"));
    }
    @GetMapping("/user/{email}")
    public UserComment findOneByEmal(@PathVariable  String email){
        return userService.pegarUsuarioEmail(email);
    }
    @PostMapping("login")
    @Transactional
    public ResponseEntity login(@RequestBody LoginDTo userDTO){
        var response = userService.Login(userDTO);
        return ResponseEntity.ok(response);
    }
    @PutMapping("foto/usuario/{id}")
    @Transactional

    public ResponseEntity<MSG> image(@RequestParam("file") MultipartFile image, @PathVariable("id") long id) throws IOException {
        var response = userService.image(image,id);
        return ResponseEntity.ok(response);
    }
    @PutMapping("perfil/usuario/{email}")
    @Transactional
    public ResponseEntity<MSG> image(@PathVariable String email,@RequestBody String perfil){
        var response = userService.perfil(email,perfil);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/user/detalhes/{id}")
    public ResponseEntity<DetalheUsuario> detalheUsuario(@PathVariable long id){
        var user = userService.buscarUserId(id);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/Logos/{img}")
    public ResponseEntity<Resource> pegarImg(@PathVariable String img){
        var response = userService.ListaImagensId(img);
        return  response;
    }
    @PostMapping("/refreshToken")
    public  ResponseEntity<?> refreshtoken(@RequestBody RefreshtokenDTO request){
         return refeshTokenService.findByToken(request.getRefreshtoken())
                 .map(refeshTokenService::verifyExpiration)
                 .map(RefreshToken::getUser)
                 .map(user->{
                     String token = tokenService.geratoken(user, user.getAuthorities());
                     return ResponseEntity.ok(new RefreshtokenDTO(token,request.getRefreshtoken()));
                 })
                 .orElseThrow(()->new CredentialsExpiredException("erro ao validar token"));
    }
    @PutMapping("/alterar/password")
    @Transactional
    public ResponseEntity alterPassword(@RequestBody AlterPassword password){
        return userService.alterPassword(password);
    }
    @PutMapping("/alterar/cod/{email}")
    @Transactional
    public ResponseEntity alterCod(@PathVariable String email ) throws MessagingException {
        return userService.alterCode(email);
    }

    @PostMapping("/varificacao/code")
    @Transactional
    public ResponseEntity varificarCodigo(@RequestBody UserVerify userVerify){
        return userService.verifyCodigo(userVerify);
    }
    @PutMapping("/alterar/passwd")
    @Transactional
    public ResponseEntity alter(@RequestBody AlterPassword alterPassword ){

        return userService.AlterByCode(alterPassword);
    }
    @DeleteMapping("/delete/{id}")
    public  MSG perfilDTo(@RequestBody Delete email, @PathVariable  long id){
        return userService.removerPerfil(email,id);
    }
}

