package com.next.c1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.next.c1.domain.DoORDR;
import com.next.jpatis.spring.JpatisConfig;
import com.next.jpatis.spring.JpatisRepositoryImpl;

@SpringBootApplication
@Import(JpatisConfig.class)
@EntityScan(basePackageClasses=DoORDR.class)
@EnableJpaRepositories(repositoryBaseClass = JpatisRepositoryImpl.class)
public class WebApp {

	public static void main(String[] args) {
		SpringApplication.run(WebApp.class, args);

	}

}