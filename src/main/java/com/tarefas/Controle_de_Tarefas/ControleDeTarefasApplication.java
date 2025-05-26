package com.tarefas.Controle_de_Tarefas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(title = "Controle de Tarefas API", version = "1.0", description = "API para gerenciamento de tarefas e usuários"))
@SpringBootApplication
public class ControleDeTarefasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControleDeTarefasApplication.class, args);
	}

}
