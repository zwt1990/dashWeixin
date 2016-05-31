package cjc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import cjc.mapper.sys.CustomUserDetailsService;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private CustomUserDetailsService customUserDetailsService;

  
  
  @Override
  protected void configure(HttpSecurity http) throws Exception {
	//允许所有用户访问和”/login” 
	  http
      .authorizeRequests()
      .antMatchers("/login")
      .permitAll();
    http
        .authorizeRequests()
        .antMatchers("/login")
        .permitAll();
    http
    .authorizeRequests()
    .antMatchers("/index")
    .permitAll();
    http
        .authorizeRequests()
        .antMatchers("/sys/registration")
        .permitAll();
    http
        .authorizeRequests().anyRequest().authenticated();
    http
        .formLogin().failureUrl("/sys/login?error")
        .defaultSuccessUrl("/")
        .loginPage("/sys/login")
        .permitAll()
        .and()
        .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl(
        "/login")
        .permitAll();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(customUserDetailsService);
    auth.eraseCredentials(false);
    
  }
  
}

