package org.Tim19.UberApp.service;

import org.Tim19.UberApp.dto.NoteDTO;
import org.Tim19.UberApp.model.Note;
import org.Tim19.UberApp.model.User;
import org.Tim19.UberApp.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    @Autowired
    NoteRepository noteRepository;
    @Autowired
    UserService userService;

    public List<Note> findAllByUserId(Integer id){return noteRepository.findAllByUserId(id);}

    public NoteDTO save(NoteDTO noteDTO){
        User user = userService.findOneById(noteDTO.getUserId());

        Note note = new Note();
        note.setText(noteDTO.getMessage());
        note.setUser(user);
        note = noteRepository.save(note);
        return new NoteDTO(note);
    }
}
