package br.com.boaentrega;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CadastroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastroServiceApplication.class, args);
	}

}
