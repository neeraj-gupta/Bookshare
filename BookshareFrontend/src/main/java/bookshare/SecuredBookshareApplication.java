package bookshare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import bookshare.Controllers.CsrfHeaderFilter;

@SpringBootApplication
@EnableZuulProxy
@EnableRedisHttpSession
public class SecuredBookshareApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecuredBookshareApplication.class, args);
    }
    
    @Configuration
    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    
    	//override configure method to customize authentication and authorization as needed
    	@Override
    	protected void configure(HttpSecurity http) throws Exception
    	{
    		http.httpBasic()
    		.and()
    		.authorizeRequests()
    		.antMatchers("/index.html", "/home.html", "/login.html", "/").permitAll()
    		.anyRequest().authenticated()
    		//add our customized csrf filter here
    		.and()
    		.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
    		//also tel spring security to expect token in the format angular sends it back
    		.csrf().csrfTokenRepository(csrfTokenRepository())
    		.and()
    		.logout();
    	}
    	
    	private CsrfTokenRepository csrfTokenRepository() {
    		  HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
    		  repository.setHeaderName("X-XSRF-TOKEN");
    		  return repository;
    		}
    	
    	protected void configure(AuthenticationManagerBuilder auth)
    	{
    	}
    	
    }//SecurityConfiguration class
}
