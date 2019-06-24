package com.example.mynotes;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerViewNotes;

    Button buttonAdd, buttonDelete, buttonMove;

    NoteAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initUI();


        recyclerViewNotes = findViewById(R.id.recyclerViewNotes);

        recyclerViewNotes.setHasFixedSize(true);

        // use a linear layout manager
        recyclerViewNotes.setLayoutManager(new LinearLayoutManager(this));

        // specify an adapter with the list to show
        mAdapter = new NoteAdapter(getData());
        recyclerViewNotes.setAdapter(mAdapter);


    }


    private void initUI() {

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonMove = findViewById(R.id.buttonMove);

    }



    private List<NotesModel> getData(){

        List<NotesModel> listaNotes = new ArrayList<>();

        for(int i=1; i<=6; i++){

            NotesModel note = new NotesModel();
            note.setTitle("Nota Títtulo " + i);
            note.setSubTitle("nota del alumnos " + i);
            listaNotes.add(note);
        }


        return listaNotes;
    }

    @Override
    public void onClick(View v) {
         Button button = (Button) v;

         switch (button.getId()) {
             case R.id.buttonAdd :

                 Log.e("Botón Add", "button ADD");

                 NotesModel note = new NotesModel();
                 note.setTitle("Nota Títtulo Nueva");
                 note.setSubTitle("subtítulo Nueva ");

                 mAdapter.addNote(note);
                 mAdapter.notifyDataSetChanged();

                 break;
             case R.id.buttonMove :


                 break;
             case R.id.buttonDelete :
                 mAdapter.deleteNote();
                 mAdapter.notifyDataSetChanged();
                 break;
         }
    }
}
