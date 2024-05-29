package br.com.Initialiizr.Informatica116.sistem.Service;

import br.com.Initialiizr.Informatica116.sistem.Models.AUTH_USER.RefreshToken;
import br.com.Initialiizr.Informatica116.sistem.Models.AUTH_USER.User;
import br.com.Initialiizr.Informatica116.sistem.repository.RefreshToeknRepository;
import br.com.Initialiizr.Informatica116.sistem.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
@Service
public class RefeshTokenService {
    @Autowired
    private RefreshToeknRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Transactional
    public RefreshToken registrarToken(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        RefreshToken existingToken = repository.findByUser(user);

        if (existingToken != null) {
            return existingToken;
        }
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setExpirationtime(new Date(System.currentTimeMillis() + 1000 * 60 * 60));
        refreshToken.setRefreshtoken(UUID.randomUUID().toString());

        return repository.save(refreshToken);
    }
    public String generateTokenFromUsername(String username) {
        return Jwts.builder().setSubject(username).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 2000 * 60*60)).signWith(SignatureAlgorithm.HS256, "123456")
                .compact();
    }
    public Optional<RefreshToken> findByToken(String token){
        return repository.findByRefreshtoken(token);
    }
    public RefreshToken verifyExpirationtoken(RefreshToken token){
        if(token.getExpirationtime().compareTo(Date.from(Instant.now()))<0){
            repository.delete(token);
        };
        return token;
    }
    public RefreshToken verifyExpiration(RefreshToken token){
        if(token.getExpirationtime().compareTo(Date.from(Instant.now()))<0){
            repository.delete(token);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        };
        return token;
    }
    @Transactional
    public void deletetoken(long id){
        var usuario = userRepository.findById(id).get();
         repository.deleteRefreshtokenByUser(usuario);

    }
}
