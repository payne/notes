package org.mattpayne.spring.visit.notes.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        boolean isLocalHost = true;
        if (isLocalHost) { // TODO: Read this from application.yml -- use spring profiles!
            // https://stackoverflow.com/questions/43794721/spring-boot-h2-console-throws-403-with-spring-security-1-5-2
            http.authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/h2-console/**").permitAll()
                    .antMatchers("/**").permitAll();
            // https://stackoverflow.com/questions/47221582/h2-in-memory-database-console-not-opening
            http.csrf().disable();
            http.headers().frameOptions().disable();
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


