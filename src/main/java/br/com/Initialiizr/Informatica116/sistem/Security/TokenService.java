package br.com.Initialiizr.Informatica116.sistem.Security;

import br.com.Initialiizr.Informatica116.sistem.DTO.AUTH_DAO.FuncoesDto;
import br.com.Initialiizr.Informatica116.sistem.Models.AUTH_USER.Funcoes;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TokenService {
    @Value("${security}")
    private String namber;
    public String geratoken(User user, Collection<? extends GrantedAuthority> authorities){
        try {
            List<String> perfil = user.getRoles().stream().map(Perfil::getName).toList();// Ajuste conforme o método ou atributo correto de Funcoes
            List<Map<String, Object>> map = user.getFuncoes().stream().filter(Funcoes::isAtivo).map(mapbay->{
                Map<String, Object>  model = new HashMap<>();
                model.put("id",mapbay.getSistemas().getId());
                model.put("name",mapbay.getSistemas().getName());
                model.put("titulo",mapbay.getSistemas().getTitulo());
                model.put("imagem",mapbay.getSistemas().getImagem());
                return model;

            }).toList();
            var algorithm = Algorithm.HMAC256(namber);
            return JWT.create()
                    .withIssuer("17100150")
                    .withSubject(user.getEmail())
                    .withClaim("name",user.getName())
                    .withClaim("lastname",user.getLastname())
                    .withClaim("id",user.getId())
                    .withClaim("filial",user.getFilial())
                    .withClaim("perfil",perfil)
                    .withClaim("contato",user.getContato())
                    .withClaim("itens",map)
                    .withClaim("imagem",user.getImagem())
                    .withExpiresAt(DataExpired())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw  new RuntimeException("erro ao gerar token"+exception);
        }
    }
//
    private Instant DataExpired() {
        return LocalDateTime.now().plusHours(2)
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
