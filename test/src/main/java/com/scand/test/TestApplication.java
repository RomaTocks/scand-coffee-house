package com.scand.test;

import com.scand.test.models.CoffeeType;
import com.scand.test.services.implementations.CoffeeTypeServiceImpl;
import com.scand.test.services.implementations.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
		CrudServiceImpl<CoffeeType> service = new CrudServiceImpl<>();
		service.test();
	}

}
