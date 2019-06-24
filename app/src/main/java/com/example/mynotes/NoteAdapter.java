package com.example.mynotes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyNoteRow> {

    private List<NotesModel> notesList;

    public NoteAdapter(List<NotesModel> notesList){
        this.notesList = notesList;
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
