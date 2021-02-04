package com.davidperezmillan.sandbox.rest.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
	
	@Bean
	public ServletRegistrationBean<WebServlet> h2servletRegistration() {
		WebServlet ws = new WebServlet();
		ServletRegistrationBean<WebServlet> registration = new ServletRegistrationBean<WebServlet>(ws);
		registration.addUrlMappings("/console/*");
		return registration;
	}
}
