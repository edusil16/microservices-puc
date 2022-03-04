package br.com.boaentrega;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableRabbit
@EnableDiscoveryClient
@SpringBootApplication
public class GestaoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoServiceApplication.class, args);
	}

}
