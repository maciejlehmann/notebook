package pl.lehmann.notebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.lehmann.notebook.model.Note;
import pl.lehmann.notebook.service.NoteService;

import java.util.List;

@CrossOrigin
@RequestMapping
@RestController
public class NoteController {

    private NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/notes")
    public List<Note> getAllNotes() {
        List<Note> list = noteService.getAllNotes();
        return list;
    }

    @PostMapping("/notes")
    public void addNote(@RequestBody Note note) {
        noteService.addNote(note);
    }

    @PutMapping("/notes")
    public void modNote(@RequestBody Note note) {
        noteService.modNote(note);
    }

    @DeleteMapping("/notes/{id}")
    public void deleteNoteById(@PathVariable Long id) {
        noteService.deleteNoteById(id);
    }


}
