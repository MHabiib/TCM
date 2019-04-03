/*
package com.future.tcfm.config.security;

import com.future.tcfm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

    @Configuration
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    @EnableWebSecurity

    public class SecurityConfig extends WebSecurityConfigurerAdapter {
        @Autowired
        UserRepository userRepository;


        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/admin/**").hasRole("admin")
                    .antMatchers("/user/**").hasRole("manager")
                    .anyRequest().authenticated()
                    .antMatchers("/resources/**").permitAll().anyRequest().permitAll()
                    .and()
                    .formLogin()
                    .loginPage("/login")
//                .failureUrl("/login.html?error=true")
                    .permitAll()
                    .and()
                    .logout()
                    .and()
                    .exceptionHandling().accessDeniedHandler(null);////////////////////////////////
            http.httpBasic();
            http.csrf().disable();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            return encoder;
        }


        @Autowired
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            auth
                    .inMemoryAuthentication()
                    .withUser("habib").password("habib123").roles("admin").and()
                    .withUser("robin").password("robin456").roles("manager");

//            userDetailsService(userDetailService).passwordEncoder(passwordEncoder()).and()
        }
        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**");
        }

    }
*/
