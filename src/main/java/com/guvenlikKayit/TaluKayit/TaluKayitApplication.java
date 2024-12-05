package com.guvenlikKayit.TaluKayit;

import com.guvenlikKayit.TaluKayit.ui.LoginView;
import com.vaadin.flow.spring.security.VaadinWebSecurityConfigurerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SpringBootApplication
public class TaluKayitApplication extends VaadinWebSecurityConfigurerAdapter{

	public static void main(String[] args) {
		SpringApplication.run(TaluKayitApplication.class, args);
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);
		setLoginView(http, LoginView.class);
	}



	@Override
	@Bean
	public UserDetailsService userDetailsServiceBean() throws Exception{
		return new InMemoryUserDetailsManager(
				User.withUsername("guvenlik")
						.password("{noop}talu")
						.roles("ADMIN")
						.build()
		);
	}

}
