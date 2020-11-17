package myspring.mvc.services;

import myspring.database.JPA.entities.Note;
import myspring.database.JPA.entities.NoteId;
import myspring.database.JPA.repositories.NoteRepository;
import myspring.mvc.DTO.NoteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public boolean addNote(NoteDTO noteDTO) {
        Note note = new Note(noteDTO.getHeader(), noteDTO.getAuthor(), noteDTO.getBody());
        if (noteRepository.getNoteById(new NoteId(noteDTO.getHeader(), noteDTO.getAuthor())) == null) {
            noteRepository.save(note);
            return true;
        }
        return false;
    }

    public NoteDTO getNote(String header, String author) {
        Note note = noteRepository.getNoteById(new NoteId(header, author));
        return new NoteDTO(note.getHeader(), note.getAuthor(), note.getBody());
    }

    public List<NoteDTO> getNotes(String author) {
        List<NoteDTO> noteDTOList = new ArrayList<>();
        List<Note> noteList = noteRepository.getNotesById_Author(author);
        for (Note n : noteList) {
            noteDTOList.add(new NoteDTO(n.getHeader(), n.getAuthor(), n.getBody()));
        }
        return noteDTOList;
    }

    public boolean updateHeader(String currentHeader, String author, String newHeader) {
        noteRepository.updateHeader(currentHeader, author, newHeader);
        return true;
    }

    public boolean updateBody(String currentHeader, String author, String body) {
        noteRepository.updateBody(currentHeader, author, body);
        return true;
    }

    public boolean deleteNote(String header, String author) {
        noteRepository.deleteById(new NoteId(header, author));
        return true;
    }


}

