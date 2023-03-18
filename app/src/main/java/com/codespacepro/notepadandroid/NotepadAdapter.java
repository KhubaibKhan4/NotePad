package com.codespacepro.notepadandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotepadAdapter extends RecyclerView.Adapter<NotepadAdapter.ViewHolder> {
    Context context;
    List<Notepad> notepadList = new ArrayList<>();

    public NotepadAdapter(Context context, List<Notepad> notepadList) {
        this.context = context;
        this.notepadList = notepadList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notepad notepad = notepadList.get(position);

        holder.Email.setText(notepad.getEmail());
        holder.UserName.setText(notepad.getUsername());
        holder.FullName.setText(notepad.getFullname());

    }

    @Override
    public int getItemCount() {
        return notepadList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView FullName, UserName, Email;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            FullName = itemView.findViewById(R.id.fullname);
            UserName = itemView.findViewById(R.id.username);
            Email = itemView.findViewById(R.id.email);
        }
    }
}
