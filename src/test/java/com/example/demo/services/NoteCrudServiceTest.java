package com.example.demo.services;

import com.example.demo.entities.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NoteCrudServiceTest {

    private NoteCrudService noteCrudService;

    @BeforeEach
    public void beforeEach() {
        noteCrudService = new NoteCrudService();
    }

    @Test
    void listAll() {
        Note note1 = new Note();
        note1.setTitle("Note 1");
        note1.setContent("Content 1");

        Note note2 = new Note();
        note2.setTitle("Note 2");
        note2.setContent("Content 2");

        noteCrudService.add(note1);
        noteCrudService.add(note2);

        List<Note> allNotes = noteCrudService.listAll();

        assertEquals(2, allNotes.size());
        assertTrue(allNotes.contains(note1));
        assertTrue(allNotes.contains(note2));
    }

    @Test
    void testAddAndGetById() {
        Note note = new Note();
        note.setTitle("Test Title");
        note.setContent("Test Content");

        Note addedNote = noteCrudService.add(note);

        assertNotNull(addedNote.getId());
        assertEquals(note.getTitle(), addedNote.getTitle());
        assertEquals(note.getContent(), addedNote.getContent());

        Note retrievedNote = noteCrudService.getById(addedNote.getId());
        assertEquals(addedNote, retrievedNote);
    }

    @Test
    void deleteById() {
        Note note = new Note();
        note.setTitle("To be deleted");
        note.setContent("Will be deleted");

        Note addedNote = noteCrudService.add(note);

        noteCrudService.deleteById(addedNote.getId());

        assertThrows(IllegalArgumentException.class, () -> noteCrudService.getById(addedNote.getId()));
    }

    @Test
    void update() {
        Note note = new Note();
        note.setTitle("Original Title");
        note.setContent("Original Content");

        Note addedNote = noteCrudService.add(note);

        addedNote.setTitle("Updated Title");
        addedNote.setContent("Updated Content");

        noteCrudService.update(addedNote);

        Note updatedNote = noteCrudService.getById(addedNote.getId());
        assertEquals("Updated Title", updatedNote.getTitle());
        assertEquals("Updated Content", updatedNote.getContent());
    }
}