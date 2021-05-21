package com.saihtoo.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    Button createNote, viewNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNote = findViewById(R.id.createNoteBtn);
        viewNote = findViewById(R.id.viewNotesBtn);
        createNote.setOnClickListener(this);
        viewNote.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.createNoteBtn:
                Intent createIntent = new Intent(MainActivity.this, NewNoteActivity.class);
                startActivity(createIntent);
                break;
            case R.id.viewNotesBtn:
                Intent viewIntent = new Intent(MainActivity.this, ViewNotesActivity.class);
                startActivity(viewIntent);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }
}