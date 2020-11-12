package myspring.mvc.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UrlDTO {

    @JsonProperty("shortUrl")
    private long id;

    private String longUrl;

    protected UrlDTO() {
    }

    public UrlDTO(long id, String longUrl) {
        this.id = id;
        this.longUrl = longUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public long getId() {
        return id;
    }


}
