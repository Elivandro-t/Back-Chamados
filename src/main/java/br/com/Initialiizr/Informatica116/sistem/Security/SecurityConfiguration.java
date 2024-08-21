package br.com.Initialiizr.Informatica116.sistem.Security;

//import br.com.Initialiizr.Informatica116.sistem.Service.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

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
                        .requestMatchers(HttpMethod.POST,"/whats").permitAll()
                        .requestMatchers(HttpMethod.POST,"/varificacao/code").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/alterar/passwd").permitAll()
                                .requestMatchers(HttpMethod.GET,"/user/*").permitAll()
                        .requestMatchers(HttpMethod.POST,"/refreshToken").permitAll()
                        .requestMatchers(HttpMethod.GET,"/imagens/*").permitAll()
                        .requestMatchers(HttpMethod.GET,"/lista/setor").permitAll()
                        .requestMatchers(HttpMethod.GET,"/send/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/registrar").permitAll()
                        .requestMatchers(HttpMethod.GET,"/relatorio").permitAll()
                                .anyRequest().authenticated()
                )
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

//    @Bean
//    public BotService botService() throws TelegramApiException {
//         var key = "7436839194:AAF8TeUL-xtQS_gkaLJvSW_03pJ5LPVPn8c";
//         var key2 = "7194413702:AAEJ6mmnGBV9sapSSycxxyBdjRkR2gMHa88";
//
//        BotService botService = new BotService("agile_ti_bot",key2);
//        var teleBots =new TelegramBotsApi(DefaultBotSession.class);
//        teleBots.registerBot(botService);
//        return botService;
//    }
}
