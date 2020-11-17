package myspring.database.JDBC.mappers;

import myspring.database.JDBC.entities.Url;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UrlMapper implements RowMapper<Url> {
    public Url mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Url(rs.getInt("id"),
                rs.getString("long_url"));
    }
}
