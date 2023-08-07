package org.elbouchouki.hectify.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "org.elbouchouki.hectify.core",
        "org.elbouchouki.hectify.rest"
})
public class RestGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestGatewayApplication.class, args);
    }
}
