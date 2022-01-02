package com.integratedfinance.exchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = {"com.integratedfinance.exchange.*"})
@EnableSwagger2 //Enable swagger 2.0 spec
public class IntegratedfinanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntegratedfinanceApplication.class, args);
    }

}
