package myspring.mvc.DTO;

public class IpLangBodyDTO {
    private String ip;
    private String language;
    private String body;

    protected IpLangBodyDTO() {
    }

    public IpLangBodyDTO(String ip, String language, String body) {
        this.ip = ip;
        this.language = language;
        this.body = body;
    }

    public String getIp() {
        return ip;
    }

    public String getLanguage() {
        return language;
    }

    public String getBody() {
        return body;
    }

}
