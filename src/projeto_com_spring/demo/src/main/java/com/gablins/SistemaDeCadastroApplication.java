package com.gablins;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.gablins.*")

@EnableJpaRepositories(basePackages = "com.gablins.repositories")
@EntityScan(basePackages = "com.gablins.entities" )
public class SistemaDeCadastroApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaDeCadastroApplication.class, args);
	}

}
