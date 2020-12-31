package com.phish.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableScheduling
public class PhishSearchApplication {

  @Autowired
  private Environment env;

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
	  @Override
	  public void addCorsMappings(CorsRegistry registry) {
		String viewUrl = env.getProperty("view.domain");
	    registry.addMapping("/**")
			    .allowedOrigins(viewUrl)
	            .allowCredentials(true);
	  }
	};
  }

  public static void main(String[] args) {
  	SpringApplication.run(PhishSearchApplication.class, args);
  }

}
