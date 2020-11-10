package myspring.database.mappers;

import myspring.database.entities.Url;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UrlMapper implements RowMapper<Url> {
    public Url mapRow(ResultSet rs, int rowNum) throws SQLException {
        Url url = new Url();
        url.setId(rs.getInt("id"));
        url.setLongUrl(rs.getString("long_url"));

        return url;
    }
}
