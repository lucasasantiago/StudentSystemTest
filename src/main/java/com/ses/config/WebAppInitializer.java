package com.ses.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/*
=============================================================
This configuration class replace the web.xml  
=============================================================
*/
public class WebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) throws ServletException {

		/*
		===============================================================================================================
		The next line is instead of: 
		
		<init-param>
			<param-name>contextClass</param-name>
			<param-value>org.springframework.web.context.support.AnnotationConfigWebApplicationContext</param-value> 
		</init-param>
		===============================================================================================================
		*/
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		
		/*
		===============================================================================================================
		The next line is instead of:
		
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>com.ses.config.WebConfig</param-value>
		</init-param>
		===============================================================================================================
		 */
		rootContext.register(WebConfig.class);

		/*
		===============================================================================================================
		Manage the lifecycle of the root application context
		===============================================================================================================
		*/
		container.addListener(new ContextLoaderListener(rootContext));
		
		/*
		===============================================================================================================
		Register and map the dispatcher servlet
		
		The next line is instead of:
		
		<servlet-name>springDispatcherServlet</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		===============================================================================================================
		*/
		ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcherServlet", new DispatcherServlet(rootContext));
		
		/*
		===============================================================================================================
		The next line is instead of:
		
		<load-on-startup>1</load-on-startup>
		===============================================================================================================
		*/
		dispatcher.setLoadOnStartup(1);
		
		/*
		===============================================================================================================
		The next line is instead of:
		
		<servlet-mapping>
			<servlet-name>springDispatcherServlet</servlet-name>
			<url-pattern>/</url-pattern>
		</servlet-mapping>
		===============================================================================================================
		*/
		dispatcher.addMapping("/");
	}

}
