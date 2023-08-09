package org.elbouchouki.hectify.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(
        basePackages = {
                "org.elbouchouki.hectify.core.users",
                "org.elbouchouki.hectify.rest"
        }
)
@EnableJpaRepositories(
        basePackages = {
                "org.elbouchouki.hectify.core.users"
        }
)
@EntityScan(
        basePackages = {
                "org.elbouchouki.hectify.core.users"
        }
)
public class ApplicationEntry {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationEntry.class, args);
    }
}
