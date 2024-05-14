package io.proj3ct.ApplicationBotStudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("io.proj3ct.ApplicationBotStudy.repository")
public class ApplicationBotStudyApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApplicationBotStudyApplication.class, args);
	}
}
