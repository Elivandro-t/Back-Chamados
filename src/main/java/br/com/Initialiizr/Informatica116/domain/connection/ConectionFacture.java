//package br.com.Initialiizr.Informatica116.domain.connection;
//
//
//import com.zaxxer.hikari.HikariDataSource;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.util.Properties;
//
//@Configuration
//@EnableTransactionManagement
//public class ConectionFacture {
//
//    @Value("${password}")
//    private String password;
//    @Value("${usuario}")
//    private String usuario;
//    @Value("${database}")
//    private String  db;
//    @Bean
//    public DataSource Database(){
//           HikariDataSource dataSource = new HikariDataSource();
//           dataSource.setJdbcUrl(db);
//           dataSource.setUsername(usuario);
//           dataSource.setPassword(password);
//           dataSource.setDriverClassName("org.postgresql.Driver");
//           return dataSource;
//    }
//
//   @Bean
//    public JpaVendorAdapter jpaVendoAdapter(){
//        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
//        jpaVendorAdapter.setGenerateDdl(true);
//        jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");
//        return jpaVendorAdapter;
//    }
//   @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource){
//        LocalContainerEntityManagerFactoryBean local = new LocalContainerEntityManagerFactoryBean();
//        local.setJpaVendorAdapter(jpaVendoAdapter());
//        local.setDataSource(dataSource);
//        local.setPackagesToScan("br.com.Initialiizr.Informatica116");
//        local.setJpaProperties(properties());
//        return local;
//    }
//
//    public Properties properties() {
//        Properties properties = new Properties();
//        properties.put("hibernate.hbm2ddl.auto", "update"); // Ajuste conforme necessário
//        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
//        properties.put("hibernate.format_sql", "true");
//        properties.put("hibernate.use_sql_comments", "true");
//        properties.put("hibernate.enable_lazy_load_no_trans", "true"); // Resolve problemas de carregamento lazy
//        return properties;
//    }
//
//
//}
