//package br.com.fpc.configuration;
//
//import java.util.Properties;
//
////import java.util.Properties;
////
//import javax.sql.DataSource;
//
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
////
////import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
////import org.hibernate.SessionFactory;
////import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.hibernate5.HibernateTemplate;
//import org.springframework.orm.hibernate5.HibernateTransactionManager;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
////import org.springframework.orm.hibernate5.HibernateTemplate;
////import org.springframework.orm.hibernate5.HibernateTransactionManager;
//
//@Configuration
//public class RepositoryConfig {
//
//	@Value("${jdbc.driverClassName}")
//	private String driverClassName;
//
//	@Value("${jdbc.url}")
//	private String url;
//
//	@Value("${jdbc.username}")
//	private String username;
//
//	@Value("${jdbc.password}")
//	private String password;
//
////	@Value("${hibernate.dialect}")
////	private String hibernateDialect;
//
//	@Value("${hibernate.show_sql}")
//	private String hibernateShowSql;
//
//	@Value("${hibernate.hbm2ddl.auto}")
//	private String hibernateHbm2ddlAuto;
//
//	@Bean()
//	public DataSource getDataSource() {
//		DriverManagerDataSource ds = new DriverManagerDataSource();
//		ds.setDriverClassName(driverClassName);
//		ds.setUrl(url);
//		ds.setUsername(username);
//		ds.setPassword(password);
//		return ds;
//	}
//
//	@Bean
//	@Autowired
//	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
//		HibernateTransactionManager htm = new HibernateTransactionManager();
//		htm.setSessionFactory(sessionFactory);
//		return htm;
//	}
//
//	@Bean
//	@Autowired
//	public HibernateTemplate getHibernateTemplate(SessionFactory sessionFactory) {
//		HibernateTemplate hibernateTemplate = new HibernateTemplate(sessionFactory);
//		return hibernateTemplate;
//	}
//
//	@Bean
//	public LocalSessionFactoryBean getSessionFactory() {
//		LocalSessionFactoryBean asfb = new LocalSessionFactoryBean();
//		asfb.setDataSource(getDataSource());
//		asfb.setHibernateProperties(getHibernateProperties());
//		asfb.setPackagesToScan(new String[] { "br.com.fpc" });
//		return asfb;
//	}
//
//	@Bean
//	public Properties getHibernateProperties() {
//		Properties properties = new Properties();
//		properties.put("hibernate.show_sql", hibernateShowSql);
//		properties.put("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);
//		return properties;
//	}
//
//}

package br.com.fpc.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Classe de configurações relacionadas a banco
 * 
 * @author fernando
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("br.com.fpc.repositories")
@PropertySource("classpath:application.properties")
public class RepositoryConfig {
	@Autowired
	private Environment env;

	/**
	 * @return
	 */
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
		lcemfb.setJpaVendorAdapter(getJpaVendorAdapter());
		lcemfb.setDataSource(getDataSource());
		lcemfb.setPersistenceUnitName("myJpaPersistenceUnit");
		lcemfb.setPackagesToScan("br.com.fpc.entities");
		lcemfb.setJpaProperties(jpaProperties());
		return lcemfb;
	}

	/**
	 * @return
	 */
	@Bean
	public JpaVendorAdapter getJpaVendorAdapter() {
		JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		return adapter;
	}

	/**
	 * @return
	 */
	@Bean()
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("database.driverClassName"));
		dataSource.setUrl(env.getProperty("database.url"));
		dataSource.setUsername(env.getProperty("database.username"));
		dataSource.setPassword(env.getProperty("database.password"));
		return dataSource;
	}

	/**
	 * @return
	 */
	@Bean(name = "transactionManager")
	public PlatformTransactionManager txManager() {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager(
				getEntityManagerFactoryBean().getObject());
		return jpaTransactionManager;
	}

	/**
	 * @return
	 */
	private Properties jpaProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
		properties.put("hibernate.id.new_generator_mappings", env.getProperty("hibernate.id.new_generator_mappings"));
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		return properties;
	}
}
