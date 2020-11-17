package myspring.mvc.controllers;

import myspring.mvc.DTO.InfoDTO;
import myspring.mvc.DTO.NoteDTO;
import myspring.mvc.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note/{username}")
public class NoteController {

    private final NoteService noteService;


    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/{noteHeader}")
    public NoteDTO oneNote(@PathVariable String noteHeader, @PathVariable String username) {
        return noteService.getNote(noteHeader, username);
    }

    @GetMapping("/getAll")
    public List<NoteDTO> allNotes(@PathVariable String username) {
        return noteService.getNotes(username);
    }

    @PostMapping("/new")
    public InfoDTO addNote(@PathVariable String username, @RequestBody NoteDTO request) {
        NoteDTO noteDTO = new NoteDTO(request.getHeader(), username, request.getBody());
        if (noteService.addNote(noteDTO))
            return new InfoDTO("success", "note was created");
        return new InfoDTO();
    }

    @PatchMapping("/{noteHeader}")
    public InfoDTO editHeader(@PathVariable String noteHeader, @PathVariable String username, @RequestBody NoteDTO request) {

        boolean updStatus = false;

        if (request.getBody() != null) {
            noteService.updateBody(noteHeader, username, request.getBody());
            updStatus = true;
        }
        if (request.getHeader() != null) {
            noteService.updateHeader(noteHeader, username, request.getHeader());
            updStatus = true;
        }

        if (updStatus)
            return new InfoDTO("success", "note was updated");
        return new InfoDTO();
    }

    @DeleteMapping("/{noteHeader}")
    public InfoDTO deleteNote(@PathVariable String noteHeader, @PathVariable String username) {
        if (noteService.deleteNote(noteHeader, username))
            return new InfoDTO("success", "note was deleted");
        return new InfoDTO();
    }

}
