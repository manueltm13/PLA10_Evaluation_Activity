package ga.manuelgarciacr.pla10.config;

import static org.hibernate.cfg.AvailableSettings.C3P0_ACQUIRE_INCREMENT;
import static org.hibernate.cfg.AvailableSettings.C3P0_MAX_SIZE;
import static org.hibernate.cfg.AvailableSettings.C3P0_MAX_STATEMENTS;
import static org.hibernate.cfg.AvailableSettings.C3P0_MIN_SIZE;
import static org.hibernate.cfg.AvailableSettings.C3P0_TIMEOUT;
import static org.hibernate.cfg.AvailableSettings.DRIVER;
import static org.hibernate.cfg.AvailableSettings.HBM2DDL_AUTO;
import static org.hibernate.cfg.AvailableSettings.PASS;
import static org.hibernate.cfg.AvailableSettings.SHOW_SQL;
import static org.hibernate.cfg.AvailableSettings.URL;
import static org.hibernate.cfg.AvailableSettings.USER;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import ga.manuelgarciacr.pla10.model.Authority;
import ga.manuelgarciacr.pla10.model.AuthorityId;
import ga.manuelgarciacr.pla10.model.Center;
import ga.manuelgarciacr.pla10.model.Department;
import ga.manuelgarciacr.pla10.model.User;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="ga.manuelgarciacr.pla10")
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
public class AppConfig implements WebMvcConfigurer{

	@Autowired
	private Environment env;

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

		Properties props = new Properties();

		// Setting JDBC properties
		props.put(DRIVER, env.getProperty("mysql.driver"));
		props.put(URL, env.getProperty("mysql.jdbcUrl"));
		props.put(USER, env.getProperty("mysql.username"));
		props.put(PASS, env.getProperty("mysql.password"));

		// Setting Hibernate properties
		props.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
		props.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));

		// Setting C3P0 properties
		props.put(C3P0_MIN_SIZE, env.getProperty("hibernate.c3p0.min_size"));
		props.put(C3P0_MAX_SIZE, env.getProperty("hibernate.c3p0.max_size"));
		props.put(C3P0_ACQUIRE_INCREMENT, env.getProperty("hibernate.c3p0.acquire_increment"));
		props.put(C3P0_TIMEOUT, env.getProperty("hibernate.c3p0.timeout"));
		props.put(C3P0_MAX_STATEMENTS, env.getProperty("hibernate.c3p0.max_statements"));
		System.out.println(
				"DRIVER: " + props.getProperty(DRIVER) + "\n" +
				"URL: " + props.getProperty(URL) + "\n" + 
				"USER: " + props.getProperty(USER) + "\n" +
				"PASS: " + props.getProperty(PASS) + "\n" +
				"SHOW_SQL: " + props.getProperty(SHOW_SQL) + "\n" +
				"HBM2DDL_AUTO: " + props.getProperty(HBM2DDL_AUTO) + "\n" +
				"C3P0_MIN_SIZE: " + props.getProperty(C3P0_MIN_SIZE) + "\n" +
				"C3P0_MAX_SIZE: " + props.getProperty(C3P0_MAX_SIZE));
		factoryBean.setHibernateProperties(props);
		factoryBean.setAnnotatedClasses(User.class, Authority.class, AuthorityId.class, Center.class, Department.class);

		return factoryBean;
	}

	@Bean
	public HibernateTransactionManager getTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(getSessionFactory().getObject());
		return transactionManager;
	}
	
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
}