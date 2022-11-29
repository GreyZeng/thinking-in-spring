package git.snippet.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// 健康检查：http://localhost:8080/actuator/health
@SpringBootApplication
@RestController
public class ThinkingInSpringApplication {

  public static void main(String[] args) {
    SpringApplication.run(ThinkingInSpringApplication.class, args);
  }

  @RequestMapping("/hello")
  public String hello() {
    return "Hello Spring";
  }
}
