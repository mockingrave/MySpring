package myspring.database.JPA.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class NoteId implements Serializable {
    private String header;
    private String author;

    protected NoteId() {
    }

    public NoteId(String header, String author) {
        this.header = header;
        this.author = author;
    }

    void setHeader(String header) {
        this.header = header;
    }

    void setAuthor(String author) {
        this.author = author;
    }

    String getHeader() {
        return header;
    }

    String getAuthor() {
        return author;
    }
}
