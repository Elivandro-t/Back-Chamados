package br.com.Initialiizr.Informatica116.sistem.Security;

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
                        .allowedOrigins("https://suporteinformatic.com.br/")
                        .allowedOrigins("http://suporteinformatic-com-br.umbler.net ")
                        .allowedOrigins("kamino02.umbler.net")
                        .allowedOrigins("http://localhost:4200")
                        .allowedOrigins("http://192.168.88.70")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");
            }
        };
    }
}
