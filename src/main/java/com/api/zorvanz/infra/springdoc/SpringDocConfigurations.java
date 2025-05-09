package com.api.zorvanz.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {
    @Bean
    public OpenAPI customOpenAPI () {
        return new OpenAPI ()
                .components ( new Components ()
                        .addSecuritySchemes ( "bearer-key",
                                new SecurityScheme ()
                                        .type ( SecurityScheme.Type.HTTP )
                                        .scheme ( "bearer" )
                                        .bearerFormat ( "JWT" ) ) )
                .addSecurityItem ( new SecurityRequirement ().addList ( "bearer-key" ) ) // 👈 Esto aplica el esquema
                .info ( new Info ()
                        .title ( "API Zorvanz" )
                        .description ( "API Rest del ecommerce Zorvanz" )
                        .contact ( new Contact ()
                                .name ( "Equipo Backend" )
                                .email ( "backend@zorvanz.co" ) )
                        .license ( new License ()
                                .name ( "Apache 2.0" )
                                .url ( "http://zorvanz/api/licencia" ) ) );
    }

    public void message () {
        System.out.println ( "bearer is working" );
    }
}
