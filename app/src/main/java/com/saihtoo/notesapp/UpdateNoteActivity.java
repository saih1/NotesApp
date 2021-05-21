package com.saihtoo.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.saihtoo.notesapp.data.DatabaseHelper;
import com.saihtoo.notesapp.model.Note;

public class UpdateNoteActivity extends AppCompatActivity
{
    public static final String NOTE_ID = "note_id";
    public static final String DESCRIPTION = "description";

    EditText uText;
    Button updateBtn, deleteBtn;
    DatabaseHelper db;

    //getting data from ViewNotesActivity.class
    int received_id;
    String received_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);
        uText = findViewById(R.id.uTextEditText);
        updateBtn = findViewById(R.id.updateBtn);
        deleteBtn = findViewById(R.id.deleteBtn);
        db = new DatabaseHelper(this);
        received_id = getIntent().getIntExtra(NOTE_ID, 0);
        received_text = getIntent().getStringExtra(DESCRIPTION);
        uText.setText(received_text);
    }

    public void deleteClick(View v) {
        db.deleteNote(received_id);
        if (!db.getNote(received_text))
            Toast("DELETED!");
        else
            Toast("Unable to delete!");
    }

    public void updateClick(View v) {
        String description =  uText.getText().toString();
        int id = received_id;
        int updateRow = db.updateNote(new Note(id, description));
        if (updateRow > 0)
            Toast("Updated!");
        else
            Toast("ERROR: Unsuccessful!");
    }

    public void Toast(String text) {
        Toast.makeText(UpdateNoteActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}