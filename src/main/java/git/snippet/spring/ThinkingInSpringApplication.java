package git.snippet.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

// 健康检查：http://localhost:8080/actuator/health
// 查看上下文中有哪些 bean：http://localhost:8080/actuator/beans
@SpringBootApplication
// @RestController
@Slf4j
public class ThinkingInSpringApplication implements CommandLineRunner {
  private final DataSource dataSource;
  private final JdbcTemplate jdbcTemplate;

  public ThinkingInSpringApplication(DataSource dataSource, JdbcTemplate jdbcTemplate) {
    this.dataSource = dataSource;
    this.jdbcTemplate = jdbcTemplate;
  }

  public static void main(String[] args) {
    SpringApplication.run(ThinkingInSpringApplication.class, args);
  }

  //  @RequestMapping("/hello")
  //  public String hello() {
  //    return "Hello Spring";
  //  }

  @Override
  public void run(String... args) throws Exception {
    showConnection();
    showData();
  }

  private void showData() {
    jdbcTemplate.queryForList("SELECT * FROM USER_INFO").forEach(row -> log.info(row.toString()));
  }

  private void showConnection() throws SQLException {
    log.info(dataSource.toString());
    Connection conn = dataSource.getConnection();
    log.info(conn.toString());
    conn.close();
  }
}
