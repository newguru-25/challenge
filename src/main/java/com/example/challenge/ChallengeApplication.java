package com.example.challenge;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactor.core.publisher.Mono;

@EnableWebFlux
@SpringBootApplication
public class ChallengeApplication {

  public static void main(String[] args) {
    SpringApplication.run(ChallengeApplication.class, args);
  }

  @Bean
  ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {

    ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
    initializer.setConnectionFactory(connectionFactory);
    initializer.setDatabasePopulator(
        new ResourceDatabasePopulator(
            new ClassPathResource("schema.sql")
//            new ClassPathResource("data.sql")
        ));

    return initializer;
  }


}
