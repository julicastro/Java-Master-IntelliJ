package com.alkemy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserDetailsServiceImp userDetailsService;

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("index", "/", "/auth/**", "/public/**","/css/**","/js/**","/h2-console/**").permitAll().anyRequest().authenticated()
        .and()
            .formLogin().loginPage("/auth/login").defaultSuccessUrl("/private/index",true).failureUrl("/auth/login")
            .loginProcessingUrl("/auth/login-post").permitAll()
        .and()
            .logout().logoutUrl("/logout").logoutSuccessUrl("/public/index");
    }
    


}
