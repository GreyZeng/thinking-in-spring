package git.snippet.spring.multids.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties(prefix = "foo.datasource")
@Slf4j
public class FooDataSourceConfig {

  @Bean
  public PlatformTransactionManager fooTxManager(DataSource fooDataSource) {
    return new DataSourceTransactionManager(fooDataSource);
  }

  @Bean
  public DataSourceProperties fooDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  @Primary
  public DataSource fooDataSource() {
    DataSourceProperties dataSourceProperties = fooDataSourceProperties();
    // schema init
    DatabasePopulator databasePopulator =
        new ResourceDatabasePopulator(
            new ClassPathResource("db/schema.sql"), new ClassPathResource("db/foo-data.sql"));
    DataSource ds = dataSourceProperties.initializeDataSourceBuilder().build();
    DatabasePopulatorUtils.execute(databasePopulator, ds);
    log.info("foo datasource: {}", dataSourceProperties.getUrl());
    return ds;
  }

  @Bean
  public JdbcTemplate fooJdbcTemplate(@Qualifier("fooDataSource") DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }
}
