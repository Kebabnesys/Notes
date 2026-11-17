package com.example.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import com.example.notes.sourcemanagers.SharedPrefsManager;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> listNoteItems = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ListView noteslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        this.noteslist = findViewById(R.id.noteslist);
        this.adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, this.listNoteItems);
        this.noteslist.setAdapter(adapter);

        Button addNote = (Button) findViewById(R.id.newNote);
        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddNoteActivity.class);
                view.getContext().startActivity(intent);}
        });
    }
}