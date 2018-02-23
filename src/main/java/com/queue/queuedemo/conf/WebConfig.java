package com.queue.queuedemo.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.queue.queuedemo.controllers.QueueController;



@Configuration
@ComponentScan(basePackageClasses = QueueController.class)
public class WebConfig extends WebMvcConfigurationSupport {


	@Override
	public void configureViewResolvers(final ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/jsp/", ".jsp");
	}
	

}
