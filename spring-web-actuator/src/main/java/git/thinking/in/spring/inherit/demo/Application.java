package git.thinking.in.spring.inherit.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.info.SimpleInfoContributor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public SimpleInfoContributor simpleInfoContributor() {
        return new SimpleInfoContributor("simple", "HelloWorld!");
    }

}
