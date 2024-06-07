package br.com.Initialiizr.Informatica116.sistem.Security;

import br.com.Initialiizr.Informatica116.sistem.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
@Configuration
public class FilterValidation extends OncePerRequestFilter {
    @Autowired
    private UserRepository repository;
    //co
    @Autowired
    TokenService tokenservice;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
     String token = tokenValidate(request);
     if(token!=null){
         var authservice = tokenservice.validaToken(token);
         var usuario = repository.findByEmail(authservice);
         System.out.println("minhas roles "+usuario.getAuthorities().toString());
        var auth = new UsernamePasswordAuthenticationToken(authservice,null,usuario.getAuthorities());
         SecurityContextHolder.getContext().setAuthentication(auth);
     }
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods",
                "ACL, CANCELUPLOAD, CHECKIN, CHECKOUT, COPY, DELETE, GET, HEAD, LOCK, MKCALENDAR, MKCOL, MOVE, OPTIONS, POST, PROPFIND, PROPPATCH, PUT, REPORT, SEARCH, UNCHECKOUT, UNLOCK, UPDATE, VERSION-CONTROL");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers",
                "Origin, X-Requested-With, Content-Type, Accept, Key, Authorization");
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            filterChain.doFilter(request,response);
        }


    }

    private String tokenValidate(HttpServletRequest request) {
        var auth = request.getHeader("Authorization");
        if(auth!=null){
            return auth.replace("Bearer","").trim();
        }
        return null;
    }

}
