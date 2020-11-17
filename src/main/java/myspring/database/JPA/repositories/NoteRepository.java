package myspring.database.JPA.repositories;

import myspring.database.JPA.entities.Note;
import myspring.database.JPA.entities.NoteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, NoteId> {

    Note getNoteById(NoteId noteId);

    List<Note> getNotesById_Author(String author);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Note n set n.body = :body where n.id.author = :author and n.id.header = :header")
    void updateBody(@Param("header") String header,
                    @Param("author") String author,
                    @Param("body") String body);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Note n set n.id.header = :newHeader where n.id.author = :author and n.id.header = :currentHeader")
    void updateHeader(@Param("currentHeader") String currentHeader,
                      @Param("author") String author,
                      @Param("newHeader") String newHeader);
}
