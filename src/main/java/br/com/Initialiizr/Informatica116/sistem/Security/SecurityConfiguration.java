package br.com.Initialiizr.Informatica116.sistem.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {
    @Autowired
    FilterValidation filterValidation;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(e->e.disable())
                .sessionManagement(e->e.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(e->e
                                .requestMatchers(HttpMethod.GET,"*").permitAll()
                        .requestMatchers( "/v3/api-docs/**","/swagger-ui.html","/swagger-ui/**").permitAll()
                                .requestMatchers(HttpMethod.POST,"/login").permitAll()
                                .requestMatchers(HttpMethod.GET,"/foto/usuario/*").permitAll()
                                .requestMatchers(HttpMethod.GET,"/sistemBotao/*").permitAll()
                                .requestMatchers(HttpMethod.PUT,"/alterar/cod/*").permitAll()
                                .requestMatchers(HttpMethod.GET,"/Logos/*").permitAll()
                        .requestMatchers(HttpMethod.POST,"/varificacao/code").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/alterar/passwd").permitAll()
                                .requestMatchers(HttpMethod.GET,"/user/*").permitAll()
                        .requestMatchers("/refreshToken").permitAll()
                        .requestMatchers(HttpMethod.GET,"/imagens/*").permitAll()
                        .requestMatchers(HttpMethod.POST,"/registrar").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(e->e.disable())
                .addFilterBefore(filterValidation, UsernamePasswordAuthenticationFilter.class)
        ;
        return httpSecurity.build();
    };
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return  configuration.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
