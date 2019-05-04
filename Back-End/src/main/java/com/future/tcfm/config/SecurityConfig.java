
package com.future.tcfm.config;

import com.future.tcfm.config.security.JwtAuthenticationEntryPoint;
import com.future.tcfm.config.security.JwtAuthenticationProvider;
import com.future.tcfm.config.security.JwtAuthenticationTokenFilter;
import com.future.tcfm.config.security.JwtSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private JwtAuthenticationProvider authenticationProvider;
    @Autowired
    private JwtAuthenticationEntryPoint entryPoint;

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Collections.singletonList(authenticationProvider));
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilter() {
        JwtAuthenticationTokenFilter filter = new JwtAuthenticationTokenFilter();
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(new JwtSuccessHandler());
        return filter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests().antMatchers("**/api/**").authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(entryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.headers().cacheControl();

    }
}
 /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests().anyRequest().authenticated()
                .and().httpBasic()
                .and().sessionManagement().disable();
    }*/

/*

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