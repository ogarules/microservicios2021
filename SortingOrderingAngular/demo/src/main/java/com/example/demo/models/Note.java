package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String noteText;
    private String noteUser;
    private int value;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public String getNoteUser() {
        return noteUser;
    }

    public void setNoteUser(String noteUser) {
        this.noteUser = noteUser;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Note(Integer id, String noteText, String noteUser, int value) {
        this.id = id;
        this.noteText = noteText;
        this.noteUser = noteUser;
        this.value = value;
    }

    public Note() {
    }
}
