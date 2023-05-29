package me.dio.sacola;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Sacola para Aplicação Delivery",
				version = "1.0.0",
				description = "Projeto para desenvolvimento de uma sacola para aplicativo delivery, apenas para questão de aprendizado"


		)
)
public class SacolaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SacolaApiApplication.class, args);
	}

}
