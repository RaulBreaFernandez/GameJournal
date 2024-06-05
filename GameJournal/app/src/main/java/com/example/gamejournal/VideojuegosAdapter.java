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

// Adaptador para la lista de videojuegos, utilizado en un RecyclerView.
public class VideojuegosAdapter extends RecyclerView.Adapter<VideojuegosAdapter.ViewHolder> {

    private Context context; // Contexto de la aplicación.
    private List<Videojuego> videojuegoList; // Lista de objetos Videojuego.
    private ImageView fotoVideojuego; // ImageView para mostrar la foto del videojuego.
    private TextView tituloVideojuego; // TextView para mostrar el título del videojuego.
    private OnItemClickListener listener; // Interfaz para manejar clics en los elementos.

    // Interfaz para manejar clics en los elementos de la lista.
    public interface OnItemClickListener {
        void onItemClick(Videojuego videojuego);
    }
    // Constructor del adaptador.
    public VideojuegosAdapter(List<Videojuego> videojuegoList, Context context, OnItemClickListener listener) {
        this.videojuegoList = videojuegoList;
        this.context = context;
        this.listener = listener;
    }
    // Método para crear el ViewHolder.
    @NonNull
    @Override
    public VideojuegosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext(); // Obtiene el contexto del padre.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_videojuego, parent, false); // Infla el layout del ViewHolder.
        return new ViewHolder(view); // Retorna una nueva instancia del ViewHolder.
    }
    // Método para vincular el ViewHolder con los datos.
    @Override
    public void onBindViewHolder(@NonNull VideojuegosAdapter.ViewHolder holder, int position) {
        Videojuego videojuego = videojuegoList.get(position); // Obtiene el videojuego en la posición actual.
        holder.bind(videojuego); // Vincula los datos del videojuego al ViewHolder.
        // Configura el listener para el clic en el elemento.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideojuegosAdapter.onVideojuegoClick(context, videojuego); // Llama al método onVideojuegoClick con el contexto y el videojuego.
            }
        });
    }
    // Retorna la cantidad de elementos en la lista.
    @Override
    public int getItemCount() {
        return videojuegoList.size();
    }
    // ViewHolder para los elementos del RecyclerView.
    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tituloVideojuego = itemView.findViewById(R.id.tituloVideojuego); // Asocia el TextView con el layout.
            fotoVideojuego = itemView.findViewById(R.id.imageViewFotoVideojuego); // Asocia el ImageView con el layout.
        }

        // Método para vincular los datos del videojuego al ViewHolder.
        public void bind(Videojuego videojuego) {
            Glide.with(context).load(videojuego.getUrlPoster()).into(fotoVideojuego); // Carga la imagen del videojuego usando Glide.
            tituloVideojuego.setText(videojuego.getTitulo()); // Establece el título del videojuego en el TextView.
        }
    }
    // Método estático para manejar el clic en un videojuego.
    public static void onVideojuegoClick(Context context, Videojuego videojuego) {
        Intent intent = new Intent(context, VideojuegoDetailActivity.class); // Crea un Intent para iniciar VideojuegoDetailActivity.
        intent.putExtra("videojuego", videojuego); // Agrega el objeto Videojuego como extra al Intent.
        context.startActivity(intent); // Inicia la actividad.
    }
}
