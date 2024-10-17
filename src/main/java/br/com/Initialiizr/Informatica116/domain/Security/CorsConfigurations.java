package br.com.Initialiizr.Informatica116.domain.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class CorsConfigurations {

    @Bean
    public WebMvcConfigurer corsConfigure(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("https://suporteinformatic.com.br,http://suporteinformatic.com.br,http://192.168.88.70,http://localhost:4200,https://projeto-chamado-angular-k0b0wpvr8-elivandrots-projects.vercel.app")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true)
                ;
            }
        };
    }
}
