package com.realdolmen.EuropeanHub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class BasicAuthConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                inMemoryAuthentication()
                .withUser("Ethias")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .and().withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
                .and()
                .withUser("AG Insurance")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .and()
                .withUser("Baloise NV")
                .password(passwordEncoder().encode("password"))
                .roles("USER");
    }

    
    @Bean
   public PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
   }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(HttpMethod.GET, "/insurers").permitAll()
                .antMatchers(HttpMethod.POST, "/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers(HttpMethod.POST, "/insurers").authenticated()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
        http.logout()
                .clearAuthentication(true)
                .invalidateHttpSession(true);

    }
}
