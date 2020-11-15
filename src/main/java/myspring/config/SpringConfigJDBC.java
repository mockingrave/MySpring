package myspring.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan("myspring.database.JDBC")
@EnableJdbcRepositories("myspring.database.JDBC.repositories")
public class SpringConfigJDBC {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSourceConfig = new BasicDataSource();

        dataSourceConfig.setDriverClassName(env.getProperty("db.driver"));

        dataSourceConfig.setUrl(env.getProperty("db.url"));
        dataSourceConfig.setUsername(env.getProperty("db.username"));
        dataSourceConfig.setPassword(env.getProperty("db.password"));

        try {
            dataSourceConfig.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dataSourceConfig;
    }


}
