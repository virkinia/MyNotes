package com.example.mynotes;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;



public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyNoteRow>  {

    ApplicationPreferences mAppPreference;



    private ArrayList<NotesModel> notesList;


    public NoteAdapter(ArrayList<NotesModel> notesList){
        this.notesList = notesList;
    }

    public void addNote(NotesModel note, int index) {
        this.notesList.add(index, note);
        notifyItemInserted(0);

        ApplicationPreferences.saveNotes(this.notesList);
    }

    public void deleteNote() {

        this.notesList.remove(0);
        notifyItemRemoved(0);
        ApplicationPreferences.saveNotes(this.notesList);

    }



    @Override
    public MyNoteRow onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_row, parent, false);
        MyNoteRow viewHolder = new MyNoteRow(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyNoteRow viewHolder, int i) {
        viewHolder.title.setText(notesList.get(i).getTitle());
        viewHolder.subtitle.setText(notesList.get(i).getSubTitle());
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }




    static class MyNoteRow extends RecyclerView.ViewHolder {
        private TextView title, subtitle;


        MyNoteRow(View v) {
            super(v);
            title = v.findViewById(R.id.title);
            subtitle = v.findViewById(R.id.subtitle);
        }


    }
}
