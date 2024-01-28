package org.shlimtech.typeeighttcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "org.shlimtech")
@ComponentScan(basePackages = "org.shlimtech")
@EntityScan(basePackages = "org.shlimtech")
@SpringBootApplication
public class TypeEightTCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(TypeEightTCoreApplication.class, args);
    }

}
