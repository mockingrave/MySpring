package myspring.database.JDBC.repositories;

import myspring.database.JDBC.entities.Url;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public interface UrlRepository {

    void setDataSource(DataSource ds);

    void insert(Url url);

    Url getUrlById(long id);

    Url getUrlByLongUrl(String longUrl);

    List<Url> listUrls();

    void delete(long id);

    void update(long id, String longUrl);
}
