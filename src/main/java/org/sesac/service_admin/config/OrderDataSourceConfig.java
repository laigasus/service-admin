package org.sesac.service_admin.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.sesac.service_admin.domain.order.Order;
import org.sesac.service_admin.repos.order.OrderRepository;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackageClasses = OrderRepository.class,
        entityManagerFactoryRef = "orderEntityManager",
        transactionManagerRef = "orderTransactionManager"
)
public class OrderDataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties("projects.order.hibernate.property")
    public HashMap<String, Object> orderHibernateProperties() {
        return new HashMap<>();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean orderEntityManager() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(orderDataSource());
        em.setPackagesToScan(Order.class.getPackageName());

        em.setPersistenceUnitName("orderEntityManager");

        final JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        final HashMap<String, Object> properties = orderHibernateProperties();
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    @Primary
    public PlatformTransactionManager orderTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(orderEntityManager().getObject());
        return transactionManager;
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "projects.order.datasource.hikari")
    public HikariConfig orderHikariConfig() {
        return new HikariConfig();
    }

    @Bean
    @Primary
    public DataSource orderDataSource() {
        var config = orderHikariConfig();
        return new HikariDataSource(config);
    }
}