package myspring.mvc.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NoteDTO {
    private String header;
    private String author;
    private String body;

    protected NoteDTO() {
    }

    public NoteDTO(String header, String author, String body) {
        this.header = header;
        this.author = author;
        this.body = body;
    }

    public String getHeader() {
        return header;
    }

    public String getAuthor() {
        return author;
    }

    public String getBody() {
        return body;
    }
}
