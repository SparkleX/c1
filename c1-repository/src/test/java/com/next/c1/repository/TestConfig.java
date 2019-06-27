package com.next.c1.repository;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.next.c1.domain.DoORDR;
import com.next.jpatis.spring.JpatisConfig;

@SpringBootApplication
@EntityScan(basePackageClasses=DoORDR.class)
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
@Import(JpatisConfig.class)
public class TestConfig {

}
