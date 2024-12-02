package org.sesac.service_admin.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.sesac.service_admin.domain.product.Product;
import org.sesac.service_admin.repos.product.ProductRepository;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackageClasses = ProductRepository.class,
        entityManagerFactoryRef = "productEntityManager",
        transactionManagerRef = "productTransactionManager"
)
public class ProductDataSourceConfig {

    @Bean
    @ConfigurationProperties("projects.product.hibernate.property")
    public HashMap<String, Object> productHibernateProperties() {
        return new HashMap<>();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean productEntityManager() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(productDataSource());
        em.setPackagesToScan(Product.class.getPackageName());
        em.setPersistenceUnitName("productEntityManager");

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        final HashMap<String, Object> properties = productHibernateProperties();
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public PlatformTransactionManager productTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(productEntityManager().getObject());
        return transactionManager;
    }

    @Bean
    @ConfigurationProperties(prefix = "projects.product.datasource.hikari")
    public HikariConfig productHikariConfig() {
        return new HikariConfig();
    }

    @Bean
    public DataSource productDataSource() {
        var config = productHikariConfig();
        return new HikariDataSource(config);
    }
}