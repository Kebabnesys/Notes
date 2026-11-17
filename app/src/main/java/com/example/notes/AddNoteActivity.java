package com.example.notes;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;


import com.example.notes.sourcemanagers.SharedPrefsManager;

public class AddNoteActivity extends AppCompatActivity {
    EditText Note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        Note = findViewById(R.id.Note);
    }

    public void onBtnSaveClose(View view) {
        String addNote = this.Note.getText().toString();
        SharedPrefsManager.writeNotesToSharedPrefs(this, addNote);
        finish();
    }

}

