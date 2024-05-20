package com.example.gamejournal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class VideojuegosGeneroAdapter extends RecyclerView.Adapter<VideojuegosGeneroAdapter.ViewHolder> {

    private Context context;
    private List<String> generoList;
    private TextView genero;

    public VideojuegosGeneroAdapter(List<String> generoList, Context context) {
        this.generoList = generoList;
        this.context = context;
    }

    @NonNull
    @Override
    public VideojuegosGeneroAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_genero_videojuego, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideojuegosGeneroAdapter.ViewHolder holder, int position) {
        String genero = generoList.get(position);
        holder.bind(genero);
    }

    @Override
    public int getItemCount() {
        return generoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            genero = itemView.findViewById(R.id.generoVideojuego);
        }

        public void bind(String generoText) {
            genero.setText(generoText);
        }
    }
}
