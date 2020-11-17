package myspring.database.JPA.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "notes")
public class Note {

    @EmbeddedId
    private NoteId id;
    private String body;

    protected Note() {
    }

    public Note(String header, String author, String body) {
        this.id = new NoteId(header, author);
        this.body = body;
    }

    public String getHeader() {
        return id.getHeader();
    }

    public String getAuthor() {
        return id.getAuthor();
    }

    public String getBody() {
        return body;
    }
}

