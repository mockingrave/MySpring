package myspring.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@ComponentScan("myspring.database.JDBC")
@EnableJdbcRepositories("myspring.database.JDBC.repositories")
public class SpringConfigJDBC {

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSourceConfig = new BasicDataSource();
        dataSourceConfig.setDriverClassName("org.postgresql.Driver");

        dataSourceConfig.setUrl("jdbc:postgresql://localhost:5432/MySpringDB");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("123");

        try {
            dataSourceConfig.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dataSourceConfig;
    }
}
