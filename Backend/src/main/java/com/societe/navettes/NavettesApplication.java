package com.societe.navettes;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication
public class NavettesApplication {

	public static void main(String[] args) {
		SpringApplication.run(NavettesApplication.class, args);
		
/*Properties props = new Properties();
		
		props.put("spring.datasource.url", "jdbc:mysql://localhost:3306/navettes");
		props.put("spring.datasource.username", "root");
		props.put("spring.datasource.password", "mysql2024");
		props.put("spring.datasource.driver-class-name","com.mysql.cj.jdbc.Driver");
		props.put("spring.jpa.show-sql", "true");
		props.put("spring.jpa.hibernate.ddl-auto", "update");
		

		
		new SpringApplicationBuilder(NavettesApplication.class)
		.properties(props).run(args);*/
	}
	
	

}
