package com.kodilla.kodilla.diplomaBackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableScheduling
@Configuration
@EnableWebSecurity
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {

    private static final String ADMIN_ROLE = "ADMIN";
    private static final String USER_ROLE = "USER";

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password(encoder().encode("adminPass")).roles(ADMIN_ROLE, USER_ROLE)
                .and()
                .withUser("user").password(encoder().encode("userPass")).roles(USER_ROLE);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/v1/rent/book").hasRole(USER_ROLE)
                .antMatchers("/v1/rent/cancel").hasRole(USER_ROLE)
                .antMatchers("/v1/rent/history").hasRole(USER_ROLE)
                .antMatchers("/v1/cars").hasRole(USER_ROLE)
                .antMatchers("/v1/categories").hasRole(USER_ROLE)
                .antMatchers("/v1/weather/**").hasRole(USER_ROLE)
                .antMatchers("/v1/**").hasRole(ADMIN_ROLE)
                .anyRequest().authenticated()
                //.antMatchers("/v1/user/login").permitAll() ---> TO BE DONE IN THE FUTURE
                .and()
                .csrf().disable()
                .formLogin().disable();
    }
}
