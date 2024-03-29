package com.store.OnlineShop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;

//@SpringBootApplication(scanBasePackages="com.store.OnlineShop")
//@ComponentScan({"com.store.OnlineShop.mapper"})
//@EntityScan("com.store.OnlineShop.model.entity")
@EnableJpaRepositories("com.store.OnlineShop.model.repository")
@SpringBootApplication
@CrossOrigin(origins = "*", allowedHeaders = "*")
@ComponentScan(basePackages = "com.store.OnlineShop")//controller", "Context"})
public class OnlineShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineShopApplication.class, args);
	}

}
