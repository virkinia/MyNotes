package com.example.mynotes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerViewNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerViewNotes = findViewById(R.id.recyclerViewNotes);

        recyclerViewNotes.setHasFixedSize(true);

        // use a linear layout manager
        recyclerViewNotes.setLayoutManager(new LinearLayoutManager(this));

        // specify an adapter with the list to show
        NoteAdapter mAdapter = new NoteAdapter(getData());
        recyclerViewNotes.setAdapter(mAdapter);
    }

    private List<NotesModel> getData(){

        List<NotesModel> listaNotes = new ArrayList<>();

        for(int i=1; i<=20; i++){

            NotesModel note = new NotesModel();
            note.setTitle("Nota Títtulo " + i);
            note.setSubTitle("nota del alumnos " + i);
            listaNotes.add(note);
        }


        return listaNotes;
    }

}
