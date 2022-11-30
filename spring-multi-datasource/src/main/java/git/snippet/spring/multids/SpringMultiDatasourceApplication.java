package git.snippet.spring.multids;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@Slf4j
public class SpringMultiDatasourceApplication implements CommandLineRunner {
  private final JdbcTemplate fooTemplate;
  private final JdbcTemplate barTemplate;
  private final JdbcTemplate defaultTemplate;

  public SpringMultiDatasourceApplication(
      @Qualifier("fooJdbcTemplate") JdbcTemplate fooTemplate,
      @Qualifier("barJdbcTemplate") JdbcTemplate barTemplate,
      JdbcTemplate defaultTemplate) {
    this.fooTemplate = fooTemplate;
    this.barTemplate = barTemplate;
    this.defaultTemplate = defaultTemplate;
  }

  public static void main(String[] args) {
    SpringApplication.run(SpringMultiDatasourceApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    fooTemplate.queryForList("SELECT * FROM USER_INFO").forEach(row -> log.info(row.toString()));
    log.info("----");
    barTemplate.queryForList("SELECT * FROM USER_INFO").forEach(row -> log.info(row.toString()));
    log.info("----");
    defaultTemplate
        .queryForList("SELECT * FROM USER_INFO")
        .forEach(row -> log.info(row.toString()));
  }
}
