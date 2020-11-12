package myspring.database.JDBC.entities;

public class Url {

    private long id;
    private String longUrl;

    protected Url() {
    }

    public Url(long id, String longUrl) {
        this.id = id;
        this.longUrl = longUrl;
    }

    public long getId() {
        return id;
    }

    public String getLongUrl() {
        return longUrl;
    }

}
