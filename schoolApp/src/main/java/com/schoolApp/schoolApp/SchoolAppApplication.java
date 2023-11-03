package com.schoolApp.schoolApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.schoolApp.schoolApp.HibernateFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.schoolApp.schoolApp.model")
@EnableJpaRepositories("com.schoolApp.schoolApp.repository")
@SpringBootApplication
public class SchoolAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolAppApplication.class, args);
		HibernateFactory hibernateFactory = new HibernateFactory();
		hibernateFactory.getSessionFactory();
	}

}
