package com.next.c1.i18n.builder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Index {
	static public void main(String[] argc) throws Exception {
		ConfigurableApplicationContext appContext = SpringApplication.run(Index.class, argc);
		EnumCreator enu = appContext.getBean(EnumCreator.class);
		enu.execute();
	}
}
