package com.jemersoft.pokechallenge.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.oas.annotations.EnableOpenApi;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
//@EnableOpenApi
public class SpringFoxSwaggerConfig {
    @Bean
    public OpenAPI api(){
        return new OpenAPI()
                .info(new Info()
                        .title("PokeChallenge Springboot API")
                        .version("V1")
                        .description("Challenge técnico para Software Engineer de Jemersoft.")
                        .contact(new Contact().name("Matias Abossio").url("https://www.matiabossio.com.ar/").email("abossio@gmail.com"))
                        .termsOfService("https://swagger.io/terms/")
                        .license(new License().name("GitHub Repository").url("https://github.com/Agrossio/pokechallenge"))
                        );
    }

//    private ApiInfo appInfo() {
//        return new ApiInfo(
//                "PokeChallenge Springboot API",
//                "Challenge tecnico para Software Engineer de Jemersoft. La idea es evaluar: /n" +
//                "- Integrar una API externa. /n" +
//                "- Exponer un API usando Java Spring Boot. /n" +
//                "- Implementar Swagger o consumir API desde front-end. /n" +
//                "- Desplegar en la nube. /n" +
//                "- Manejar errores. /n" +
//                "- Mantener un código ordenado y de calidad. /n",
//                "V1",
//                "",
//                new Contact("Matias Abossio", "https://www.matiabossio.com.ar/", "abossio@gmail.com"),
//                "GitHub Repository",
//                "https://github.com/Agrossio/pokechallenge",
//                Collections.emptyList()
//        );
//    }
}
