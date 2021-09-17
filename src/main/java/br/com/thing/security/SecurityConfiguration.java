package br.com.thing.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import br.com.thing.service.ServicePaths;

@Configuration
@EnableAutoConfiguration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	public static final String AUTH_USER = "ROLE_USER";

	public static final String AUTH_ADMIN = "ROLE_ADMIN";
	
	public static final String AUTH_SHADOW = "ROLE_SHADOW";

	@Autowired
	private UserDetailsService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userService).passwordEncoder(this.passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.httpBasic().and().authorizeRequests()
//		http.csrf().disable().httpBasic().and().authorizeRequests()
//				//.antMatchers("/ws/**").permitAll()

				.antMatchers("/assets/angularJs/**", "/assets/fonts/**", "/assets/img/**", "/assets/css/**",
						"/assets/js/plugin/webfont/**", "/assets/js/core/**", "/assets/js/**",
						"/assets/js/plugin/jquery-ui-1.12.1.custom/**", "/assets/ico/**", "/src/**").permitAll()

				.antMatchers("/css/**", "/js/**").permitAll()
				.antMatchers("/").permitAll()
				.antMatchers("index.html").permitAll()
				// Global Authority to OPTIONS (permit all).
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				.antMatchers(ServicePaths.PUBLIC_ROOT_PATH + ServicePaths.ALL).permitAll()

				// profile Authorities
				.antMatchers(HttpMethod.GET, ServicePaths.PROFILE_PATH + ServicePaths.ALL)
				.hasAnyAuthority(AUTH_ADMIN, AUTH_USER)
				.antMatchers(HttpMethod.POST, ServicePaths.PROFILE_PATH + ServicePaths.ALL)
				.hasAnyAuthority(AUTH_ADMIN, AUTH_USER)
				.antMatchers(HttpMethod.PUT, ServicePaths.PROFILE_PATH + ServicePaths.ALL)
				.hasAnyAuthority(AUTH_ADMIN, AUTH_USER)
				.antMatchers(HttpMethod.DELETE, ServicePaths.PROFILE_PATH + ServicePaths.ALL)
				.hasAnyAuthority(AUTH_ADMIN, AUTH_USER)

				// user/**
				.antMatchers(HttpMethod.GET, ServicePaths.USER_PATH).hasAnyAuthority(AUTH_ADMIN, AUTH_USER)
				.antMatchers(HttpMethod.GET, ServicePaths.USER_PATH+"findById/*").hasAnyAuthority(AUTH_ADMIN, AUTH_USER)
				.antMatchers(HttpMethod.PUT, ServicePaths.USER_PATH).permitAll()
				.antMatchers(HttpMethod.DELETE, ServicePaths.USER_PATH).permitAll()
				.antMatchers(HttpMethod.POST, ServicePaths.USER_PATH).permitAll()

				.anyRequest().fullyAuthenticated().and()
				// Logout configuration.
				.logout().logoutRequestMatcher(new AntPathRequestMatcher(ServicePaths.LOGOUT_PATH))				
				.logoutSuccessHandler(new HeaderHandler()).and()
				// CSRF configuration.
				.csrf().csrfTokenRepository(csrfTokenRepository()).and()
				// .csrf().disable()
				.addFilterAfter(csrfHeaderFilter(), CsrfFilter.class);
	}

	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}

	private Filter csrfHeaderFilter() {
		return new OncePerRequestFilter() {

			@Override
			protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
					FilterChain filterChain) throws ServletException, IOException {
				CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());

				if (csrf != null) {
					Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
					String token = csrf.getToken();

					if (cookie == null || token != null && !token.equals(cookie.getValue())) {
						cookie = new Cookie("XSRF-TOKEN", token);
						cookie.setPath("/");
						response.addCookie(cookie);
					}
				}

				filterChain.doFilter(request, response);
			}

		};
	}

}
