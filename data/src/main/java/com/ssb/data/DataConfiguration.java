package com.ssb.data;

import com.ssb.data.pojo.*;
import com.ssb.data.pojo.Role;
import org.apache.commons.dbcp2.*;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.orm.hibernate5.*;
import org.springframework.transaction.*;
import org.springframework.transaction.annotation.*;

import javax.sql.*;
import java.util.*;

@Configuration
@ComponentScan(basePackages = "com.ssb.data")
@PropertySource(value = {
        "classpath:liquibase.properties",
        "classpath:hibernate.properties"
})
@EnableTransactionManagement
public class DataConfiguration {
    @Bean
    public Properties hibernateProperties(
            @Value("${hibernate.show_sql}") String showSql,
            @Value("true") String debug,
            @Value("${hibernate.dialect}") String dialect
    ) {
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.show_sql", showSql);
        hibernateProperties.put("debug", debug);
        hibernateProperties.put("hibernate.dialect", dialect);
        return hibernateProperties;
    }

    @Bean
    public DataSource dataSource(
            @Value("${url}") String url,
            @Value("${driver}") String driverClassName,
            @Value("user") String userName,
            @Value("${password}") String password,
            @Value("true") boolean removeAbandonedOnBorrow,
            @Value("10") int initialSize,
            @Value("25") int maxTotal) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setRemoveAbandonedOnBorrow(removeAbandonedOnBorrow);
        dataSource.setInitialSize(initialSize);
        dataSource.setMaxTotal(maxTotal);
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource,
                                                  @Qualifier("hibernateProperties") Properties hibernateProperties) {
        LocalSessionFactoryBean sessionFactory =
                new LocalSessionFactoryBean();

        sessionFactory.setHibernateProperties(hibernateProperties);
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setAnnotatedClasses(
                Card.class,
                CardDetails.class,
                News.class,
                ExchangeRate.class,
                CallbackRequest.class,
                Role.class,
                User.class,
                UserAccount.class,
                Deposits.class,
                UserDeposits.class
        );
        return sessionFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
}
