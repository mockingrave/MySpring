package myspring.database.JDBC.templates;

import myspring.database.JDBC.entities.Url;
import myspring.database.JDBC.mappers.UrlMapper;
import myspring.database.JDBC.repositories.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class UrlJDBCTemplate implements UrlRepository {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public void insert(Url url) {
        String SQL = "insert into public.url_table (long_url) values (?)";
        jdbcTemplateObject.update(SQL, url.getLongUrl());
    }

    @Override
    public Url getUrlByLongUrl(String longUrl) {
        String SQL = "select * from public.url_table where long_url = ?";

        return jdbcTemplateObject.queryForObject
                (SQL, new Object[]{longUrl}, new UrlMapper());
    }

    @Override
    public Url getUrlById(long id) {
        String SQL = "select * from public.url_table where id = ?";

        return jdbcTemplateObject.queryForObject
                (SQL, new Object[]{id}, new UrlMapper());
    }

    @Override
    public List<Url> listUrls() {
        String SQL = "select * from public.url_table";
        return jdbcTemplateObject.query(SQL, new UrlMapper());
    }

    @Override
    public void delete(long id) {
        String SQL = "delete from public.url_table where id = ?";
        jdbcTemplateObject.update(SQL, id);
    }

    @Override
    public void update(long id, String longUrl) {
        String SQL = "update public.url_table set long_url = ? where id = ?";
        jdbcTemplateObject.update(SQL, longUrl, id);
    }

}
