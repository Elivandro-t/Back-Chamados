package br.com.Initialiizr.Informatica116.sistem.Security;

import br.com.Initialiizr.Informatica116.sistem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("dados"+username);
        var user = userRepository.findByEmail(username);
        if(user!=null) {
            return user;
        }
        throw new RuntimeException("e-mail não encontrado!");
    }
}
