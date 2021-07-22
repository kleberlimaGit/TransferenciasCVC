package br.com.CVCCorp.transferencias.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig   extends WebSecurityConfigurerAdapter {
	@Autowired
	private Environment env;

	private static final String[] PUBLIC_MATCHERS = { "/**" };
	
	// Configuração para o spring security liberar o banco h2 para ser acessado
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// H2
		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}
		
		http.cors().and().csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll();
	}
	// Configuração para o spring security liberar o acesso a documentação do swagger
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", 
				"/configuration/**", "/swagger-ui.html", "/webjars/**");
	}
	
	
}
