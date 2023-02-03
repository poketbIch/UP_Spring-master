package com.example.RPD.Securing;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Bean
    public BCryptPasswordEncoder getPasswordEncoder()
    {
        return new BCryptPasswordEncoder(8);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().
                antMatchers("/Authorization/Login","/Authorization/Registration","/Policeman/CrimeCaseView").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/Authorization/Login").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .csrf().disable().cors().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().
                dataSource(dataSource)
                .passwordEncoder(getPasswordEncoder())
                .usersByUsernameQuery("SELECT username, password, active FROM account WHERE username=?")
                .authoritiesByUsernameQuery("SELECT u.username, ur.role FROM account u INNER JOIN user_role ur ON u.id=ur.user_id WHERE username=?");
    }
}