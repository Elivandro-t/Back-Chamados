package br.com.Initialiizr.Informatica116.sistem.Security;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class OpenAiSwageer {
    @Bean
    public OpenAPI validation(){
        return  new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("jwt " )
                        )
                )
                .info(new Info()
                        .title("informatica api")
                        .description("sistema de chamado para usuario cd")
                        .contact(new Contact()
                                .name("elivandro lopes")
                                .email("elivandro78@gmail.com")
                        )
                        .license(new License()
                                .name("apache 2.0")
                                .url("http:localhost:8080/api")
                        )
                );

    }
}
