package pl.lehmann.notebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.lehmann.notebook.model.Note;
import pl.lehmann.notebook.model.NoteDto;
import pl.lehmann.notebook.repository.NoteRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private NoteRepo noteRepo;

    @Autowired
    public NoteService(NoteRepo noteRepo) {
        this.noteRepo = noteRepo;
    }

    public List<Note> getAllNotes() {
        List<Note> result = (List<Note>) noteRepo.findAll();

        if (result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Note>();
        }
    }

    public ResponseEntity<Note> deleteNoteById(Long id) {
        Optional<Note> first = noteRepo.findAll().stream().filter(note -> note.getId() == id).findFirst();
        if (first.isPresent()) {
            noteRepo.deleteById(id);
            return new ResponseEntity<>(first.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public void addNote(Note note) {
        noteRepo.save(note);
    }

    public ResponseEntity<Object> modNote(Note newNote) {
        Optional<Note> first = noteRepo.findAll().stream().filter(note -> note.getId() == newNote.getId()).findFirst();
        if (first.isPresent()) {
            noteRepo.delete(first.get());
            noteRepo.save(newNote);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
