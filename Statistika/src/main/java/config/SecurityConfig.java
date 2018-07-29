package config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception{
//		authenticationMgr.inMemoryAuthentication()
//		.withUser("devuser").password("{noop}dev").authorities("ROLE_USER")
//		.and()
//		.withUser("adminuser").password("{noop}admin").authorities("ROLE_USER","ROLE_ADMIN");
		
		authenticationMgr.jdbcAuthentication().dataSource(dataSource).passwordEncoder(new BCryptPasswordEncoder())
		.usersByUsernameQuery(
			"select username,password, enabled from users where username=?")
		.authoritiesByUsernameQuery(
			"select username, authority from authorities where username=?");
	}
	
	//Authorization
	@Override
	protected void configure(HttpSecurity http) throws Exception{
//		http
//		.authorizeRequests()
//		.antMatchers("/","/notprotected*").permitAll()
//		.antMatchers("/protectedByUserRole*").hasRole("USER")
//		.antMatchers("/protectedByAdminRole*").hasRole("ADMIN").anyRequest().authenticated()
//		.and().logout().and()
//		.httpBasic();
		
		 http.csrf().disable();
		 http
			.authorizeRequests()
			.antMatchers("/","/notprotected*").permitAll()
			.antMatchers("/protectedByUserRole*").hasRole("USER")
			.antMatchers("/protectedByAdminRole*").hasRole("ADMIN").anyRequest().authenticated()
			.and().logout().and()
			.httpBasic();
	}
	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder(11);
//	}

}
