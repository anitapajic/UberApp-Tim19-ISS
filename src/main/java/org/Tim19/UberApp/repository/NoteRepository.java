package org.Tim19.UberApp.repository;

import org.Tim19.UberApp.model.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Integer> {

    public Page<Note> findAll(Pageable pageable);

    public Optional<List<Note>> findAllByUserId(Integer id);

    public Note save(Note note);
}
