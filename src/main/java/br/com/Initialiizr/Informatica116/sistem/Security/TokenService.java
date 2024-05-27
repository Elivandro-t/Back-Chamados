package br.com.Initialiizr.Informatica116.sistem.Security;

import br.com.Initialiizr.Informatica116.sistem.Models.AUTH_USER.Perfil;
import br.com.Initialiizr.Informatica116.sistem.Models.AUTH_USER.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.List;

@Service
public class TokenService {
    @Value("${security}")
    private String namber;
    public String geratoken(User user, Collection<? extends GrantedAuthority> authorities){
        try {
            List<String> perfil = user.getItens().stream().map(Perfil::getName).toList();
            var algorithm = Algorithm.HMAC256(namber);
            return JWT.create()
                    .withIssuer("17100150")
                    .withSubject(user.getEmail())
                    .withClaim("name",user.getName())
                    .withClaim("id",user.getId())
                    .withClaim("filial",user.getFilial())
                    .withClaim("perfil",perfil)
                    .withClaim("imagem",user.getImagem())
                    .withExpiresAt(DataExpired())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw  new RuntimeException("erro ao gerar token"+exception);
        }
    }

    private Instant DataExpired() {
        return LocalDateTime.now().plusMinutes(2)
                .toInstant(ZoneOffset.of("-03:00"));

    }


    public  String validaToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(namber);
            return JWT.require(algorithm)
                    .withIssuer("17100150")
                    .build()
                    .verify(token).getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException(exception);
            // Invalid signature/claims
        }
    }
}
