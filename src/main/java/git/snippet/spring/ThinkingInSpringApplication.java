package git.snippet.spring;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

// 健康检查：http://localhost:8080/actuator/health
// 查看上下文中有哪些 bean：http://localhost:8080/actuator/beans
// @RestController
@SpringBootApplication(
    exclude = {
      DataSourceAutoConfiguration.class,
      DataSourceTransactionManagerAutoConfiguration.class,
      JdbcTemplateAutoConfiguration.class
    })
@Slf4j
public class ThinkingInSpringApplication implements CommandLineRunner {
  //  private final DataSource dataSource;
  //  private final JdbcTemplate jdbcTemplate;
  //
  //  public ThinkingInSpringApplication(DataSource dataSource, JdbcTemplate jdbcTemplate) {
  //    this.dataSource = dataSource;
  //    this.jdbcTemplate = jdbcTemplate;
  //  }
  private final JdbcTemplate fooTemplate;

  private final JdbcTemplate barTemplate;

  public ThinkingInSpringApplication(
      @Qualifier("jdbcTemplateBar") JdbcTemplate barTemplate,
      @Qualifier("jdbcTemplateFoo") JdbcTemplate fooTemplate) {
    this.barTemplate = barTemplate;
    this.fooTemplate = fooTemplate;
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
    // showConnection();
    showData();
  }

  @Bean
  public JdbcTemplate jdbcTemplateBar(@Qualifier("barDataSource") DataSource dsOne) {
    return new JdbcTemplate(dsOne);
  }

  @Bean
  public JdbcTemplate jdbcTemplateFoo(@Qualifier("fooDataSource") DataSource dsTwo) {
    return new JdbcTemplate(dsTwo);
  }

//  @Bean
//  @ConfigurationProperties("foo.datasource")
//  public DataSourceProperties fooDataSourceProperties() {
//    return new DataSourceProperties();
//  }

//  @Bean
//  public DataSource fooDataSource() {
//    DataSourceProperties dataSourceProperties = fooDataSourceProperties();
//    log.info("foo datasource: {}", dataSourceProperties.getUrl());
//    return dataSourceProperties.initializeDataSourceBuilder().build();
//  }

  @Bean
  public DataSourceInitializer dataSourceInitializerFoo(
      @Qualifier("fooDataSource") DataSource datasource) {
    ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
    resourceDatabasePopulator.addScript(new ClassPathResource("schema.sql"));
    resourceDatabasePopulator.addScript(new ClassPathResource("foo_data.sql"));

    DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
    dataSourceInitializer.setDataSource(datasource);
    dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
    return dataSourceInitializer;
  }

  @Bean
  public DataSourceInitializer dataSourceInitializerBar(
      @Qualifier("barDataSource") DataSource datasource) {
    ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
    resourceDatabasePopulator.addScript(new ClassPathResource("schema.sql"));
    resourceDatabasePopulator.addScript(new ClassPathResource("bar_data.sql"));

    DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
    dataSourceInitializer.setDataSource(datasource);
    dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
    return dataSourceInitializer;
  }

  @Bean
  @Resource
  public PlatformTransactionManager fooTxManager(DataSource fooDataSource) {
    return new DataSourceTransactionManager(fooDataSource);
  }
  //
  //  @Bean
  //  @ConfigurationProperties("bar.datasource")
  //  public DataSourceProperties barDataSourceProperties() {
  //    return new DataSourceProperties();
  //  }
  //
  //  @Bean
  //  public DataSource barDataSource() {
  //    DataSourceProperties dataSourceProperties = barDataSourceProperties();
  //    log.info("bar datasource: {}", dataSourceProperties.getUrl());
  //    return dataSourceProperties.initializeDataSourceBuilder().build();
  //  }

  @Bean
  @Resource
  public PlatformTransactionManager barTxManager(DataSource barDataSource) {
    return new DataSourceTransactionManager(barDataSource);
  }

  private void showData() {
    fooTemplate.queryForList("SELECT * FROM USER_INFO").forEach(row -> log.info(row.toString()));
    log.info("----");
    barTemplate.queryForList("SELECT * FROM USER_INFO").forEach(row -> log.info(row.toString()));
  }
  //
  //  private void showConnection() throws SQLException {
  //    log.info(dataSource.toString());
  //    Connection conn = dataSource.getConnection();
  //    log.info(conn.toString());
  //    conn.close();
  //  }
}
