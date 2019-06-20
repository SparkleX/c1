package com.next.jpatis.test;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.next.jpatis.spring.JpatisConfig;
import com.next.jpatis.spring.JpatisRepositoryImpl;

@SpringBootApplication
@EntityScan
@EnableJpaRepositories(repositoryBaseClass = JpatisRepositoryImpl.class)
@Import(JpatisConfig.class)
public class TestConfig {

}
