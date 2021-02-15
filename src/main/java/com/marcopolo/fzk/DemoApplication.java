package com.marcopolo.fzk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	protected UserDetailsService userDetailsService() {
		return userName -> {
			User user = new User("admin", "admin", AuthorityUtils.NO_AUTHORITIES);
			return user;
		};
	}
}
