package br.com.Initialiizr.Informatica116.domain.Controler;

import br.com.Initialiizr.Informatica116.domain.DTO.AUTH_DAO.*;
import br.com.Initialiizr.Informatica116.domain.DTO.HardwareDTO.Delete;
import br.com.Initialiizr.Informatica116.domain.DTO.MESAGENS.Mensagem;
import br.com.Initialiizr.Informatica116.domain.Models.LoginDTo;
import br.com.Initialiizr.Informatica116.domain.Models.AUTH_USER.RefreshToken;
import br.com.Initialiizr.Informatica116.domain.Models.AUTH_USER.UserVerify;
import br.com.Initialiizr.Informatica116.domain.Security.TokenService;
import br.com.Initialiizr.Informatica116.domain.Service.RefeshTokenService;
import br.com.Initialiizr.Informatica116.domain.Service.UserService;

import br.com.Initialiizr.Informatica116.domain.validators.MSG;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping
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
    public MsgRegistre registrar(@RequestBody @Valid UserDTO userDTO){
        var result = userService.registro(userDTO);
        return result;

    }
    @GetMapping("/user/{email}")
    public UserComment findOneByEmal(@PathVariable String email){
        return userService.pegarUsuarioEmail(email);
    }
    @PostMapping("login")
    @Transactional
    public ResponseEntity login(@RequestBody LoginDTo userDTO){
       try{
           var response = userService.Login(userDTO);
           return ResponseEntity.ok(response);
       }catch (ValidationException e){
           return ResponseEntity.badRequest().body(new MsgRegistre(e.getMessage()));

       }
    }
    @PutMapping("foto/usuario/{id}")
    @Transactional

    public ResponseEntity image(@RequestParam("file") MultipartFile image, @PathVariable("id") long id) throws IOException {
        try {
            userService.image(image,id);
            return ResponseEntity.noContent().build();
        }catch (ValidationException e){
          return ResponseEntity.badRequest().body("Erro ao enviar imagem");

        }
    }
    @PutMapping("/usuario/update")
    @Transactional
    public ResponseEntity update(@RequestBody UpdateUserDto updateUserDto) {
         userService.updateUSer(updateUserDto);
        return ResponseEntity.ok(new Mensagem("Atualizado com sucesso!"));
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
        try {
         return refeshTokenService.findByToken(request.getRefreshtoken())
                 .map(refeshTokenService::verifyExpiration)
                 .map(RefreshToken::getUser)
                 .map(user->{
                     String token = tokenService.geratoken(user, user.getAuthorities());
                     return ResponseEntity.ok(new RefreshtokenDTO(token,request.getRefreshtoken()));
                 })
                 .orElseThrow(()->new CredentialsExpiredException("Refresh token expirado"));
        } catch (CredentialsExpiredException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Erro("Refresh token expirado"));
        }
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

