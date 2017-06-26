package com.ses.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableJpaRepositories(basePackages={"com.ses.dao"}) //to activate the DAO repositories
@EnableTransactionManagement
@Configuration
public class ApplicationContext {
	
	/*
	===============================================================================================================
	Building a method to get the MySQL DataSource from application.properties file  
	===============================================================================================================
	*/
	
	@Autowired
	private Environment environment;
	
	@Bean
	public DataSource dataSource(){
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getProperty("jdbc.driverClass"));
		dataSource.setUrl(environment.getProperty("jdbc.url"));
		dataSource.setUsername(environment.getProperty("jdbc.username"));
		dataSource.setPassword(environment.getProperty("jdbc.password"));
		
		return dataSource;
	}
	
	/*
	===============================================================================================================
	Configuring JPA, JTA & Hibernate   
	===============================================================================================================
	*/
	
	/*
	IMPORTANT: 
		The next VARIABLE and METHOD ...  MUST TO BE NAMED ===> "transactionManager" 
		because JPA search that name by default
	*/
	
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
		return jpaTransactionManager;
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter(){
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setDatabase(Database.ORACLE);
		//jpaVendorAdapter.setDatabase(Database.MYSQL);
		jpaVendorAdapter.setShowSql(true);
		return jpaVendorAdapter;
	}
	
	/*
	IMPORTANT: 
		The next VARIABLE and METHOD ...  MUST TO BE NAMED ===> "entityManagerFactory" 
		because JPA search that name by default
	ELSE: 
		Name it whatever, but change this line above:
		@EnableJpaRepositories(basePackages={"com.ses.dao"})
		TO:
		@EnableJpaRepositories(basePackages={"com.ses.dao"}, entityManagerFactoryRef="yourCustomName")
	*/	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource());
		entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter());
		
		entityManagerFactory.setPackagesToScan("com.ses.domain");
		
		Properties jpaProperties = new Properties();
		jpaProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		//jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		jpaProperties.setProperty("hibernate.show_sql", "true");
		jpaProperties.setProperty("hibernate.format_sql", "true");
		
		
		entityManagerFactory.setJpaProperties(jpaProperties);
		
		return entityManagerFactory;
	}	
	
	
	/*
	===============================================================================================================
	My Owns Beans  
	===============================================================================================================
	*/
	/*
	THERE ARE 2 OPTIONS TO ADD MY OWN BEANS INTO DE SPRING CONTAINER:
	
		OPTION 1 - USING AN ANNOTATION OVER THE CLASS DIRECTLY:
		-------------------------------------------------------
			@Repository -->	Entities
			@Service 	--> Services
			
			Example:			
			
			@Service
			public class CareerServiceImpl implements CareerService {
			
		OPTION 2 - HERE, IN APPLICATIONCONTEXT:
		-------------------------------------------------------
			@Bean
			public CareerService careerService(){
				return new CareerServiceImpl();
			}
	*/
}
