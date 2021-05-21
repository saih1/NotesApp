package com.saihtoo.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.saihtoo.notesapp.data.DatabaseHelper;
import com.saihtoo.notesapp.model.Note;

import java.util.ArrayList;
import java.util.List;

public class ViewNotesActivity extends AppCompatActivity
{
    public static final String NOTE_ID = "note_id";
    public static final String DESCRIPTION = "description";

    ListView noteListView;
    DatabaseHelper db;
    ArrayList<String> noteArrayList;
    ArrayAdapter<String> noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notes);
        noteListView = findViewById(R.id.noteListView);
        db = new DatabaseHelper(this);
        noteArrayList = new ArrayList<>();
        //Populate
        List<Note> noteList = db.getAllNotes();
        for (Note n : noteList) {
            noteArrayList.add(n.getDescription());
        }
        noteAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, noteArrayList);
        noteListView.setAdapter(noteAdapter);

        //Item Click Listener for ListView
        noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ViewNotesActivity.this, UpdateNoteActivity.class);
                intent.
                        putExtra(NOTE_ID, noteList.get(position).getNote_id()).
                        putExtra(DESCRIPTION, noteList.get(position).getDescription());
                startActivityForResult(intent, 1);
                finish();
            }
        });
    }
}