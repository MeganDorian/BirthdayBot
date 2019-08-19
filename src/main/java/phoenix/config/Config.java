package phoenix.config;

import org.flywaydb.core.Flyway;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import phoenix.telegram.HappyBirthdayBot;
import phoenix.security.UserDetailsServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;
import java.util.Random;

@Configuration
@EnableWebMvc
@ComponentScan("phoenix")
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class Config implements WebMvcConfigurer {

    @Autowired
    Environment properties;

    @Bean
    public Flyway flyWay() {
        Flyway flyway = Flyway.configure().dataSource(
                properties.getProperty("hibernate.connection.url"),
                properties.getProperty("hibernate.connection.username"),
                properties.getProperty("hibernate.connection.password")).
                schemas("bdays").locations("classpath:migration").load();
        flyway.clean();
        flyway.migrate();
        return flyway;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/static/css/");
    }

    @Bean
    public Properties properties () {
        Properties configProperties = new Properties();
        configProperties.setProperty("hibernate.connection.username", properties.getProperty("hibernate.connection.username"));
        configProperties.setProperty("hibernate.connection.password", properties.getProperty("hibernate.connection.password"));
        configProperties.setProperty("hibernate.connection.url", properties.getProperty("hibernate.connection.url"));
        return configProperties;
    }

    @Bean
    public DataSource dataSource(){
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.postgresql.Driver.class);
        dataSource.setUsername(properties.getProperty("hibernate.connection.username"));
        dataSource.setPassword(properties.getProperty("hibernate.connection.password"));
        dataSource.setUrl(properties.getProperty("hibernate.connection.url"));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean factoryBean(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factory =
                new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource);
        factory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        factory.setJpaProperties(properties ());
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                factoryBean(dataSource()).getObject() );
        return transactionManager;
    }

    @Bean
    @DependsOn("flyWay")
    public EntityManager entityManager (EntityManagerFactory entityManagerFactory){
        return entityManagerFactory.createEntityManager();
    }

    @Bean
    public Random random() {
        return new Random();
    }

    @Bean
    public TelegramBotsApi telegramBotsApi (HappyBirthdayBot happyBirthdayBot) {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(happyBirthdayBot);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
        return telegramBotsApi;
    }

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver ();

        viewResolver.setPrefix("/static/pages/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }

    @Bean
    public UserDetailsService userDetails () {
        return new UserDetailsServiceImpl();
    }
}
