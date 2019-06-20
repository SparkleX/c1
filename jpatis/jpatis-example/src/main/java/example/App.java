package example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.next.jpatis.spring.JpatisConfig;
import com.next.jpatis.spring.JpatisRepositoryImpl;

@SpringBootApplication
@Import(JpatisConfig.class)
@EnableJpaRepositories(repositoryBaseClass = JpatisRepositoryImpl.class)
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
