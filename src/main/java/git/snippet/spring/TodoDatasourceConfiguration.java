package git.snippet.spring;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

public class TodoDatasourceConfiguration {
  @Bean
  @ConfigurationProperties("bar.datasource")
  public DataSourceProperties barDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  @ConfigurationProperties("foo.datasource")
  public DataSourceProperties fooDataSourceProperties() {
    return new DataSourceProperties();
  }
}
