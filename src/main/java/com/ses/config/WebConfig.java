package com.ses.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/*
=============================================================
This configuration class replace the 
application-context.xml or dispatcher-servlet.xml 
=============================================================
*/
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"com.ses"})
@Import({ApplicationContext.class})
@PropertySource("classpath:application.properties")
public class WebConfig extends WebMvcConfigurerAdapter{
	
	/*
	===============================================================================================================
	Returning a view when there is no any explicit controller:
	"/" -> index.jsp
	===============================================================================================================
	*/
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
	}
	
	/*
	===============================================================================================================
	Handling resources such as: css/js/images 
	===============================================================================================================
	*/
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	/*
	===============================================================================================================
	Resolving application.properties
	===============================================================================================================
	*/
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigure(){
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	/*
	===============================================================================================================
	Resolving the views
	===============================================================================================================
	*/
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
}
