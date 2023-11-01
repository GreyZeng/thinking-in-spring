package git.thinking.in.spring.inherit.demo;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ActuatorSecurityConfigurer extends WebSecurityConfigurerAdapter {
    // 提供匿名访问
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.requestMatcher(EndpointRequest.toAnyEndpoint()).authorizeRequests((requests) -> requests.anyRequest().anonymous());
//        http.httpBasic();
//    }
    // 需要认证才能访问
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatcher(EndpointRequest.toAnyEndpoint().excluding("health")).authorizeRequests((requests) -> requests.anyRequest().authenticated());
        http.httpBasic();
    }
}