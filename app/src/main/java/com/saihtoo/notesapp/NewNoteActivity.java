package com.saihtoo.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.saihtoo.notesapp.data.DatabaseHelper;
import com.saihtoo.notesapp.model.Note;

public class NewNoteActivity extends AppCompatActivity
{
    Button saveBtn;
    EditText descriptionText;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        saveBtn = findViewById(R.id.newSaveBtn);
        descriptionText = findViewById(R.id.textEditText);
        db = new DatabaseHelper(this);
    }

    public void saveClick(View view) {
        String description = descriptionText.getText().toString();
        long result = db.addNote(new Note(description));
        if (result > 0) Toast("Note added!");
        else Toast("Error: Note could not be added!");
    }

    public void Toast(String text) {
        Toast.makeText(NewNoteActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}