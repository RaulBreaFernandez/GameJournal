package com.example.gamejournal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class VideojuegosAdapter extends RecyclerView.Adapter<VideojuegosAdapter.ViewHolder> {

    private Context context;
    private List<Videojuego> videojuegoList;
    private ImageView fotoVideojuego;
    private TextView tituloVideojuego;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Videojuego videojuego);
    }

    public VideojuegosAdapter(List<Videojuego> videojuegoList, Context context, OnItemClickListener listener) {
        this.videojuegoList = videojuegoList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VideojuegosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_videojuego, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideojuegosAdapter.ViewHolder holder, int position) {
        Videojuego videojuego = videojuegoList.get(position);
        holder.bind(videojuego);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideojuegosAdapter.onVideojuegoClick(context, videojuego);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videojuegoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tituloVideojuego = itemView.findViewById(R.id.tituloVideojuego);
            fotoVideojuego = itemView.findViewById(R.id.imageViewFotoVideojuego);
        }

        public void bind(Videojuego videojuego) {
            Glide.with(context).load(videojuego.getUrlPoster()).into(fotoVideojuego);
            tituloVideojuego.setText(videojuego.getTitulo());
        }
    }

    public static void onVideojuegoClick(Context context, Videojuego videojuego) {
        Intent intent = new Intent(context, VideojuegoDetailActivity.class);
        intent.putExtra("videojuego", videojuego);
        context.startActivity(intent);
    }

    public void filterList(List<Videojuego> filteredList) {
        videojuegoList = filteredList;
    }

}
