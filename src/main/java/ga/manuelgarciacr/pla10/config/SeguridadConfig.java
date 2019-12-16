package ga.manuelgarciacr.pla10.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SeguridadConfig extends WebSecurityConfigurerAdapter {
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	@Autowired
	private UserDetailsService userService;
	
	@Autowired
    public void WebSecurityConfig(AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/admin/users").hasRole("ADMIN")
			.antMatchers("/").hasRole("EMPLEADO")
			.antMatchers("/gerente/**").hasAnyRole("GERENTE","ADMIN")
		.and()
			.formLogin().loginPage("/formLogin")
			.loginProcessingUrl("/authenticateTheUser")
			.successHandler(authenticationSuccessHandler).permitAll()
			.permitAll()
		.and()
			.logout().permitAll()
		.and()
			.exceptionHandling().accessDeniedPage("/prohibido");
	}
}