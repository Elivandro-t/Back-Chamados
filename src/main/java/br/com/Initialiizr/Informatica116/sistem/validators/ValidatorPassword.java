package br.com.Initialiizr.Informatica116.sistem.validators;
import br.com.Initialiizr.Informatica116.sistem.DTO.AUTH_DAO.AlterPassword;
import br.com.Initialiizr.Informatica116.sistem.Models.AUTH_USER.User;
import br.com.Initialiizr.Informatica116.sistem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class ValidatorPassword {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    //validando senha menor que 8 caracter
    private UserRepository usuarioRepository;
    public void validator(String password){
        if(insegurePassowd(password)){
            throw new RuntimeException("senha nao e recomendavel");
        }
    }
    private static boolean insegurePassowd(String str){

        return str.length()<8||retornaNuber(str)||!containsCharacter(str);
    }
    private static boolean retornaNuber(String str){
        return str.matches("\\d+");
    }
    private static boolean containsCharacter(String str) {
        return str.matches(".*[a-zA-Z].*");
    }
    // vericacao de senha ao alterar
    public User AlterarPassword(AlterPassword alterPassword) {
        var usuario = usuarioRepository.getReferenceById(alterPassword.id());
        if(!passwordEncoder.matches(alterPassword.password(),usuario.getPassword())){
            throw  new RuntimeException("senha atual invalida!");
        } if(!passwordEncoder.matches(alterPassword.newPassword(),usuario.getPassword())){
            return usuario;
        }
        throw  new RuntimeException("senha nao podem ser iguais");
    }
    public User  Alterar(AlterPassword alterPassword) {
        var usuario = usuarioRepository.findByEmail(alterPassword.email());
        if(usuario==null){
            throw new RuntimeException("usuario invalido");
        }
        if(!passwordEncoder.matches(alterPassword.password(),usuario.getPassword())){
            throw  new RuntimeException("senha invalida");
        } if(!passwordEncoder.matches(alterPassword.newPassword(),usuario.getPassword())){
            return usuario;
        }
        throw  new RuntimeException("senha nao podem ser iguais");
    }
}
