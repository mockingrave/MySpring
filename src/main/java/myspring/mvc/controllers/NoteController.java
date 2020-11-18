package myspring.mvc.controllers;

import myspring.mvc.DTO.InfoDTO;
import myspring.mvc.DTO.NoteDTO;
import myspring.mvc.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;


    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/{noteHeader}")
    public NoteDTO oneNote(@PathVariable String noteHeader, Principal principal) {
        return noteService.getNote(noteHeader, principal.getName());
    }

    @GetMapping("/getAll")
    public List<NoteDTO> allNotes(Principal principal) {
        return noteService.getNotes(principal.getName());
    }

    @PostMapping("/new")
    public InfoDTO addNote(  @RequestBody NoteDTO request, Principal principal) {
        NoteDTO noteDTO = new NoteDTO(request.getHeader(), principal.getName(), request.getBody());
        if (noteService.addNote(noteDTO))
            return new InfoDTO("success", "note was created");
        return new InfoDTO();
    }

    @PatchMapping("/{noteHeader}")
    public InfoDTO editHeader(@PathVariable String noteHeader, @RequestBody NoteDTO request,  Principal principal) {

        boolean updStatus = false;

        if (request.getBody() != null) {
            noteService.updateBody(noteHeader, principal.getName(), request.getBody());
            updStatus = true;
        }
        if (request.getHeader() != null) {
            noteService.updateHeader(noteHeader, principal.getName(), request.getHeader());
            updStatus = true;
        }

        if (updStatus)
            return new InfoDTO("success", "note was updated");
        return new InfoDTO();
    }

    @DeleteMapping("/{noteHeader}")
    public InfoDTO deleteNote(@PathVariable String noteHeader,  Principal principal) {
        if (noteService.deleteNote(noteHeader, principal.getName()))
            return new InfoDTO("success", "note was deleted");
        return new InfoDTO();
    }

}
