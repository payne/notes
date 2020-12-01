package org.mattpayne.spring.visit.notes.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        boolean isLocalHost = true;
        if (isLocalHost) {
            http.antMatcher("/**").authorizeRequests()
                    .antMatchers("/**").permitAll();
        } else {
            http
                    .antMatcher("/**").authorizeRequests()
                    .antMatchers(new String[]{"/", "/index"}).permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .oauth2Login();
        }
    }
}


