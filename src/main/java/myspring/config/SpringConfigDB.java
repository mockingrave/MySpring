package myspring.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;

@Configuration
@ComponentScan("myspring.database")
@EnableJdbcRepositories("myspring.repositories")//МЕНЯЛА
public class SpringConfigDB {

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
