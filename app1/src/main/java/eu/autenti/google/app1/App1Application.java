package eu.autenti.google.app1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableOAuth2Sso
@EnableRedisHttpSession
public class App1Application extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(App1Application.class, args);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
				.antMatcher("/**")
				.authorizeRequests()
				.antMatchers("/", "/documents", "/users")
				.permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/")
				.and()
				.csrf()
				.disable();
	}
}
