package com.mozcan.readingIsGood.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/book/create").hasAnyRole("ADMIN")
                        .requestMatchers("/book/update").hasAnyRole("ADMIN")
                        .requestMatchers("/book/{*}").hasAnyRole("ADMIN","USER")
                        .requestMatchers("/customer").hasAnyRole(  "ADMIN")
                        .requestMatchers("/order/create").hasAnyRole("USER","ADMIN")
                        .requestMatchers("/order/{*}").hasAnyRole("ADMIN")
                        .requestMatchers("/statics").hasAnyRole(  "ADMIN")
                        .requestMatchers("/h2-ui").permitAll()
                        .requestMatchers("/swagger-ui/index.html").permitAll()
                        .anyRequest().permitAll()
                )
                .httpBasic()
                .and()
                .csrf().disable().cors().disable()
                .headers().frameOptions().disable();

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        List<UserDetails> userDetailsList = new ArrayList<>();
        userDetailsList.add(User.withUsername("user").password(encoder().encode("userPass"))
                .roles("USER").build());
        userDetailsList.add(User.withUsername("admin").password(encoder().encode("adminPass"))
                .roles("ADMIN", "USER").build());

        return new InMemoryUserDetailsManager(userDetailsList);
    }


    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
