package com.silvercoinbank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired UserDetailsService userDetailsService;
	@Autowired BCryptPasswordEncoder bCryptEncoder;
	@Autowired AccessDeniedHandler accessDeniedHandler;
	
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptEncoder);
		
		//create username and password without bCryptEncoder. For testing using default login or custom login.
//		auth.inMemoryAuthentication().withUser("hien").password("{noop}hien").roles("admin"); 
		
		//create username & pw. For testing without UserDetailsService
//		auth.inMemoryAuthentication().withUser("hien").password(bCryptEncoder.encode("hien")).roles("admin"); 
	}
	
	
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/bank", "/userForm", "/createUser").permitAll()
		.anyRequest().authenticated()
		.and().formLogin()
		.loginPage("/login").permitAll()  // custom login page that we create
		.and().exceptionHandling().accessDeniedHandler(accessDeniedHandler).accessDeniedHandler(accessDeniedHandler);
		http.httpBasic().and().csrf().disable();
	}
	
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/css/*", "/images/*");
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptEncoder() {
		BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
		return bCryptEncoder;
	}
}
