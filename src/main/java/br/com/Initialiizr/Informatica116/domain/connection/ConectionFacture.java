package br.com.Initialiizr.Informatica116.domain.connection;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class ConectionFacture {

    @Value("${password}")
    private String password;
    @Value("${usuario}")
    private String usuario;
    @Bean
    public DataSource connection(){
           HikariDataSource dataSource = new HikariDataSource();
           dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
           dataSource.setUsername(usuario);
           dataSource.setPassword(password);
           dataSource.setDriverClassName("org.postgresql.Driver");
           return dataSource;
    }


    public JpaVendorAdapter jpaVendoAdapter(){
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(true);
        jpaVendorAdapter.setShowSql(true);
        return jpaVendorAdapter;
    }
   @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource){
        LocalContainerEntityManagerFactoryBean local = new LocalContainerEntityManagerFactoryBean();
        local.setJpaVendorAdapter(jpaVendoAdapter());
        local.setDataSource(dataSource);
        local.setPackagesToScan("br.com.Initaliizr.informatica116");
        local.setJpaProperties(properties());
        return local;
    }
    public Properties properties(){
        Properties properties = new Properties();
        properties.setProperty("spring.jpa.hibernate.ddl-auto","update");
        return properties;
    }


}
