package com.jwt.token.sample.loginApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableAspectJAutoProxy
@SpringBootApplication
@ComponentScan("com.jwt.token.sample.loginApp")
@EntityScan("com.jwt.token.sample.loginApp.domain")
@EnableJpaRepositories(basePackages = "com.jwt.token.sample.loginApp.repository")
@EnableJms
@EnableScheduling
@EnableWebMvc
public class LoginAppApplication {//extends SpringBootServletInitializer {

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(LoginAppApplication.class);
//    }

    public static void main(String[] args) {
        SpringApplication.run(LoginAppApplication.class);
    }
}
