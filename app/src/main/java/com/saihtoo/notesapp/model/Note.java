package com.saihtoo.notesapp.model;

public class Note {
    private int note_id;
    private String description;

    public Note(int note_id, String description)
    {
        this.note_id = note_id;
        this.description = description;
    }

    public Note(String description)
    {
        this.description = description;
    }

    public Note(){}

    public int getNote_id() {
        return note_id;
    }

    public void setNote_id(int note_id) {
        this.note_id = note_id;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
