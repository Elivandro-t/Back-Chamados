//package br.com.Initialiizr.Informatica116.domain.connection;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//
//import javax.sql.DataSource;
//import java.util.Properties;
//
//@Configuration
//public class Conectiondb {
////// metodo de configuracao de banco de dados
//    @Bean
//    public DataSource dataSource(){
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.postgresql.Driver");
//        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
//        dataSource.setUsername("postgres");
//        dataSource.setPassword("tgfhvbnm");
//        return dataSource;
//    }
//   // O JpaVendorAdapter é uma interface usada pelo
//   // Spring Data JPA para configurar detalhes específicos do
//   // provedor JPA que você está utilizando. No seu caso, o provedor
//   // JPA é o Hibernate (um dos implementadores mais comuns de JPA).
//   // O adaptador permite que o Spring abstraia diferenças entre os provedores
//   // JPA e configure opções específicas para o Hibernate.
//   private JpaVendorAdapter jpaVendorAdapter(){
//        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
//        jpaVendorAdapter.setGenerateDdl(true);
//        jpaVendorAdapter.setShowSql(true);
//        return jpaVendorAdapter;
//    }
//   @Bean
//   public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
//        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
//        entityManagerFactoryBean.setDataSource(dataSource());
//        entityManagerFactoryBean.setPackagesToScan("br.com.Initialiizr.Informatica116.domain");
//        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
//        entityManagerFactoryBean.setJpaProperties(properties());
//        entityManagerFactoryBean.setJpaProperties(properties());
//        return entityManagerFactoryBean;
//   }
//   private Properties properties(){
//        Properties properties = new Properties();
//        properties.setProperty("spring.jpa.hibernate.ddl-auto","update");
//        properties.setProperty("server.port","8080");
//        return properties;
//
//   }
//
//}
