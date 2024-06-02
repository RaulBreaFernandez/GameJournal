package com.example.gamejournal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VideojuegosGeneroAdapter extends RecyclerView.Adapter<VideojuegosGeneroAdapter.ViewHolder> {

    private List<String> generoList;
    private Context context;
    private OnGeneroClickListener onGeneroClickListener;

    public VideojuegosGeneroAdapter(List<String> generoList, Context context, OnGeneroClickListener listener) {
        this.generoList = generoList;
        this.context = context;
        this.onGeneroClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_genero_videojuego, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String genero = generoList.get(position);
        holder.bind(genero);
    }

    @Override
    public int getItemCount() {
        return generoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView genero;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            genero = itemView.findViewById(R.id.generoVideojuego);
            itemView.setOnClickListener(this);
        }

        public void bind(String generoText) {
            genero.setText(generoText);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                String selectedGenero = generoList.get(position);
                onGeneroClickListener.onGeneroClick(selectedGenero);
            }
        }
    }

    public interface OnGeneroClickListener {
        void onGeneroClick(String genero);
    }
}

