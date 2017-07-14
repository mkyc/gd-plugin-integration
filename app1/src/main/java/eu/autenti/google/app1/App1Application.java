package eu.autenti.google.app1;

import eu.autenti.google.app1.entities.DocumentEntity;
import eu.autenti.google.app1.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@SpringBootApplication
@EnableOAuth2Sso
public class App1Application extends WebSecurityConfigurerAdapter implements CommandLineRunner {


	@Autowired
	private DocumentRepository documentRepository;

	public static void main(String[] args) {
		SpringApplication.run(App1Application.class, args);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.antMatcher("/**")
				.authorizeRequests()
				.antMatchers("/", "/documents")
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

	@Override
	public void run(String... strings) throws Exception {
		documentRepository.deleteAll();

		for(int i=0; i<3; i++) {
			documentRepository.save(new DocumentEntity("Title" + i));
		}
	}
}
