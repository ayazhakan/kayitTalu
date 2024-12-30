package com.guvenlikKayit.TaluKayit;

import com.guvenlikKayit.TaluKayit.ui.LoginView;
import com.vaadin.flow.spring.security.VaadinWebSecurityConfigurerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SpringBootApplication
public class TaluKayitApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TaluKayitApplication.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TaluKayitApplication.class);
	}
	@Bean
	public UserDetailsService userDetailsService() {
		return new InMemoryUserDetailsManager(
				User.withUsername("guvenlik")
						.password("{noop}talu")
						.roles("ADMIN")
						.build()
		);
	}
	@Configuration
	public static class SecurityConfig extends VaadinWebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			super.configure(http);
			setLoginView(http, LoginView.class);
		}
	}
}
