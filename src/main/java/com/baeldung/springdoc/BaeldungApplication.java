package com.baeldung.springdoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;


@SecurityScheme(type = SecuritySchemeType.HTTP, paramName = "myHeader1", in = SecuritySchemeIn.HEADER ,scheme = "basic",name = "LOGIN",description = "Usuario y contraseña para logear")
@OpenAPIDefinition(		
	     servers = {
	    		 @Server(url="www.google.cl",description = "Servidor Agregado"),	     		   
	    		 @Server(url="www.swagger.io",description="Este es otro servidor agregado")
	    		 },
	     info= @Info(title="Titulo nuevo de la API", description="Esta es la nueva descripcion",contact = @Contact(email = "omar.gutierrezm@utem.cl",name = "Omar Gutierrez",url = "www.google.cl"),version = "1.1",termsOfService = "www.google.cl",license = @License(name = "MIT",url = "www.mit.com" )),
	     security= @SecurityRequirement(name = "LOGIN",scopes =" ")
	    		 
	     
	     )
		
@SpringBootApplication
public class BaeldungApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(BaeldungApplication.class, args);
	}
	
	

}
