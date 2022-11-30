package git.snippet.spring.multids.config;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties(prefix = "bar.datasource")
@Slf4j
public class BarDataSourceConfig {

  @Bean
  @Resource
  public PlatformTransactionManager barTxManager(DataSource barDataSource) {
    return new DataSourceTransactionManager(barDataSource);
  }

  @Bean
  public DataSourceProperties barDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  public DataSource barDataSource() {
    DataSourceProperties dataSourceProperties = barDataSourceProperties();
    DatabasePopulator databasePopulator =
        new ResourceDatabasePopulator(
            new ClassPathResource("db/schema.sql"), new ClassPathResource("db/bar-data.sql"));
    DataSource ds = dataSourceProperties.initializeDataSourceBuilder().build();
    DatabasePopulatorUtils.execute(databasePopulator, ds);
    log.info("bar datasource: {}", dataSourceProperties.getUrl());
    return ds;
  }

  @Bean
  public JdbcTemplate barJdbcTemplate(@Qualifier("barDataSource") DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }
}
