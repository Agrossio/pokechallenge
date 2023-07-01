package com.jemersoft.pokechallenge.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringFoxSwaggerConfig {

//    https://springdoc.org/#migrating-from-springfox
//    swagger con https: https://github.com/springdoc/springdoc-openapi/issues/118
@Value("${server.servlet.context-path}") String contextPath;
    @Bean
    public OpenAPI api(){
        return new OpenAPI()
//                .addServersItem(new Server().url(contextPath))
                .info(new Info()
                        .title("PokeChallenge Springboot API")
                        .version("V1")
                        .description("""
                                Challenge técnico para posición de Software Engineer en XX SA. Se busca evaluar:
                                        - Integrar una API externa.
                                        - Exponer un API usando Java Spring Boot.
                                        - Implementar Swagger o consumir API desde front-end
                                        - Desplegar en la nube.
                                        - Manejar errores.
                                        - Mantener un código ordenado y de calidad.
                                """)
                        .contact(new Contact().name("Matias Abossio").url("https://www.matiabossio.com.ar/").email("abossio@gmail.com"))
                        .termsOfService("https://swagger.io/terms/")
                        .license(new License().name("GitHub Repository").url("https://github.com/Agrossio/pokechallenge"))
                        );
    }
}
