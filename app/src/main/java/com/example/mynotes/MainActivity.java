package com.example.mynotes;


import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerViewNotes;

    Button buttonAdd, buttonDelete, buttonMove;
    EditText etTitle, etSubtitle;


    NoteAdapter mAdapter;
    ApplicationPreferences mAppPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Asociamos los elementos de la vista al código
        this.initUI();


        // Datos del shared
        ArrayList<NotesModel> noteLists = mAppPreference.readNotes();

        mAdapter = (noteLists == null) ? new NoteAdapter(getData()) : new NoteAdapter(noteLists);
        recyclerViewNotes.setAdapter(mAdapter);

        // Añade el touch a mi mAdapter
        ItemTouchHelper helper = new ItemTouchHelper(new MyTouchHelperCallback(mAdapter));
        helper.attachToRecyclerView(recyclerViewNotes);


        if(mAppPreference.readOnBoarding() == false) {
            this.onBoarding();
        }


    }


    private void initUI() {

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonMove = findViewById(R.id.buttonMove);

        etTitle = findViewById(R.id.etTitle);
        etSubtitle = findViewById(R.id.etSubtitle);

        // Iniciamos el sharedPreferences - para poder acceder a nuestros datos en local
        mAppPreference.init(getApplicationContext());
        recyclerViewNotes = findViewById(R.id.recyclerViewNotes);
        // Para que no cambie de tamaño cada vez que se añadan objetos
        recyclerViewNotes.setHasFixedSize(true);
        // Configuración comportate como una lista - puede comportarse como una tabla, o como celdas
        recyclerViewNotes.setLayoutManager(new LinearLayoutManager(this));

    }



    private ArrayList<NotesModel> getData(){

        ArrayList<NotesModel> listaNotes = new ArrayList<>();

        for(int i=1; i<=6; i++){

            NotesModel note = new NotesModel();
            note.setTitle("Nota Títtulo " + i);
            note.setSubTitle("nota del alumnos " + i);
            listaNotes.add(note);
        }


        return listaNotes;
    }

    private void onBoarding() {

        new AlertDialog.Builder(this)
                .setTitle("Bienvenido")
                .setMessage("Pulsa Add para añaidr una nueva nota y Delete para borrar")


                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton("No mostrar más", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                        mAppPreference.saveOnBoarding(true);
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                        mAppPreference.saveOnBoarding(false);
                    }
                })
                .show();

    }
    @Override
    public void onClick(View v) {
         Button button = (Button) v;

         switch (button.getId()) {
             case R.id.buttonAdd :

                 Log.e("Botón Add", "button ADD");

                 NotesModel note = new NotesModel();
                 note.setTitle(etTitle.getText().toString());
                 note.setSubTitle(etSubtitle.getText().toString());

                 mAdapter.addNote(note, 0);
                 ///mAdapter.notifyDataSetChanged();


                 break;
             case R.id.buttonMove :


                 break;
             case R.id.buttonDelete :
                 mAdapter.deleteNote();
                 // mAdapter.notifyDataSetChanged();

                 break;
         }
    }



}
