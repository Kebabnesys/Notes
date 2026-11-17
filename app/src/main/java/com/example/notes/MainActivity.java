package com.example.notes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.example.notes.sourcemanagers.SharedPrefsManager;
import com.example.notes.utils.Constants;

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
        noteslist = findViewById(R.id.noteslist);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listNoteItems);
        noteslist.setAdapter(adapter);

        Button addNote = (Button) findViewById(R.id.newNote);
        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddNoteActivity.class);
                view.getContext().startActivity(intent);}
        });

        noteslist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Confirm delete");
                builder.setMessage("Are you sure you want to delete it?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listNoteItems.remove(position);
                                adapter.notifyDataSetChanged();
                                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                                SharedPreferences sharedPref = getSharedPreferences(Constants.SHARED_PREF_SOURCE_NOTES_FILE, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPref.edit();
                                Set<String> updated = new HashSet<>(listNoteItems);
                                editor.putStringSet(Constants.SHARED_PREF_SOURCE_NOTES_ARRAY_KEY, updated);
                                editor.apply();
                            }
                        });
                    builder.setNegativeButton("Cancel", null);
                    builder.show();
                return true;
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();

        Set<String> savedSet = SharedPrefsManager.readNotesFromSharedPrefs(this);
        if (savedSet != null) {
            listNoteItems.clear();
            listNoteItems.addAll(savedSet);
            adapter.notifyDataSetChanged();
        }
    }

}