package project.ufrn.pw.api_rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestApplication.class, args);
	}
}

/*
Usuario 1--N Pedido
Pedido  N--N Produto
xxxxx   1--1 xxxxx
*/