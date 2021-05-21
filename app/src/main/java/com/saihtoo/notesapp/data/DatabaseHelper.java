package com.saihtoo.notesapp.data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.saihtoo.notesapp.model.Note;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "note_dp";
    public static final String TABLE_NAME = "notes";
    public static final String NOTE_ID = "note_id";
    public static final String DESCRIPTION = "description";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + NOTE_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + DESCRIPTION + " TEXT)";
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_USER_TABLE = "DROP TABLE IF EXISTS' '";
        db.execSQL(DROP_USER_TABLE, new String[]{TABLE_NAME});
        onCreate(db);
    }

    public long addNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DESCRIPTION, note.getDescription());
        long newRowId = db.insert(TABLE_NAME, null, values);
        db.close();
        return newRowId;
    }

    public boolean getNote(String description) {
        SQLiteDatabase db = this.getReadableDatabase();
        int numberOfRows;
        Cursor cursor = db.query(TABLE_NAME, new String[]{NOTE_ID},
                DESCRIPTION + "=?", new String[]{description},
                null, null, null);
        numberOfRows = cursor.getCount();
        db.close();
        return numberOfRows > 0;
    }

    public List<Note> getAllNotes() {
        List<Note> noteList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = " SELECT * FROM " + TABLE_NAME;
        @SuppressLint("Recycle")
        Cursor cursor = db.rawQuery(Query, null);
        while (cursor.moveToNext()) {
            Note n = new Note();
            n.setNote_id(cursor.getInt(0));
            n.setDescription(cursor.getString(1));
            noteList.add(n);
        }
        return noteList;
    }

    public int updateNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(DESCRIPTION, note.getDescription());
        return db.update(TABLE_NAME, v, NOTE_ID + "=?", new String[]{String.valueOf(note.getNote_id())});
    }

    public void deleteNote(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, NOTE_ID + "=?", new String[]{String.valueOf(id)});
    }
}
