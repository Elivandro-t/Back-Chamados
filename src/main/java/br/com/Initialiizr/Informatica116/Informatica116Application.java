package br.com.Initialiizr.Informatica116;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableAsync
@EnableJpaRepositories
@EntityScan
@EnableScheduling
@EnableCaching
@SpringBootApplication
//@EnableDiscoveryClient
public class Informatica116Application {
	public static void main(String[] args) {
		SpringApplication.run(Informatica116Application.class, args);
	}
}
