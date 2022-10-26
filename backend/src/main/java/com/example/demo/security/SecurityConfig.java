/*
 * package com.example.demo.security;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.config.annotation.authentication.builders.
 * AuthenticationManagerBuilder; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter; import
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import
 * org.springframework.security.crypto.password.PasswordEncoder; import
 * org.springframework.security.web.AuthenticationEntryPoint;
 * 
 * @Configuration
 * 
 * @EnableWebSecurity public class SecurityConfig extends
 * WebSecurityConfigurerAdapter {
 * 
 * @Autowired private AuthenticationEntryPoint authEntryPoint;
 * 
 * 
 * 
 * @Autowired public void init(AuthenticationManagerBuilder auth) throws
 * Exception { auth.userDetailsService(userDetailsService()); }
 * 
 * 
 * @Autowired public void configureGlobal(AuthenticationManagerBuilder auth)
 * throws Exception { String password = passwordEncoder().encode("hackit");
 * auth.inMemoryAuthentication() .withUser("hackit") .password(password)
 * .authorities("ROLE_USER"); }
 * 
 * 
 * 
 * @Override protected void configure(HttpSecurity httpSecurity) throws
 * Exception { httpSecurity.csrf().disable()
 * .authorizeRequests().antMatchers("/health").permitAll().anyRequest().
 * authenticated() .and().httpBasic().authenticationEntryPoint(authEntryPoint);
 * }
 * 
 * @Bean public PasswordEncoder passwordEncoder() { return new
 * BCryptPasswordEncoder(); }
 * 
 * }
 */