package git.snippet.spring.multids;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication(
    exclude = {
      DataSourceAutoConfiguration.class,
      DataSourceTransactionManagerAutoConfiguration.class,
      JdbcTemplateAutoConfiguration.class
    })
@Slf4j
public class SpringMultiDatasourceApplication implements CommandLineRunner {
  private final JdbcTemplate fooTemplate;

  private final JdbcTemplate barTemplate;

  public SpringMultiDatasourceApplication(
      @Qualifier("fooJdbcTemplate") JdbcTemplate fooTemplate,
      @Qualifier("barJdbcTemplate") JdbcTemplate barTemplate) {
    this.fooTemplate = fooTemplate;
    this.barTemplate = barTemplate;
  }

  public static void main(String[] args) {
    SpringApplication.run(SpringMultiDatasourceApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    fooTemplate.queryForList("SELECT * FROM USER_INFO").forEach(row -> log.info(row.toString()));
    log.info("----");
    barTemplate.queryForList("SELECT * FROM USER_INFO").forEach(row -> log.info(row.toString()));
  }
}
